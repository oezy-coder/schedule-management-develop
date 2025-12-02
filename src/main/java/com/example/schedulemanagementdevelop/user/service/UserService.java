package com.example.schedulemanagementdevelop.user.service;

import com.example.schedulemanagementdevelop.exception.CustomException;
import com.example.schedulemanagementdevelop.exception.ErrorCode;
import com.example.schedulemanagementdevelop.user.dto.UpdateUserRequest;
import com.example.schedulemanagementdevelop.user.dto.UserRequest;
import com.example.schedulemanagementdevelop.user.dto.UserResponse;
import com.example.schedulemanagementdevelop.user.entity.User;
import com.example.schedulemanagementdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public UserResponse save(UserRequest request) {
        User user = new User(request.getUserName(), request.getEmail(), request.getPassword());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
        }

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponse getOne(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 전체 유저 조회
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<UserResponse> dtos = new ArrayList<>();
        for (User user : users) {
            UserResponse dto = new UserResponse(
                    user.getId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 유저 정보 수정 (유저명, 비밀번호는 선택적으로 변경 가능)
    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        // 유저명 변경: null이면 변경 없음, 공백이면 예외 발생
        user.modifyUserName(request.getUserName());

        // 비밀번호 변경: null이면 변경 없음, 공백이면 예외 발생
        String newPassword = request.getNewPassword();
        if (newPassword != null) {
            newPassword = newPassword.trim();

            if (newPassword.isBlank()) {
                throw new CustomException(ErrorCode.INVALID_PASSWORD); // 새 비밀번호가 null이 아닌데 빈 문자열일 경우 ErrorCode 응답
            }

            user.modifyPassword(newPassword);
        }

        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 삭제
    @Transactional
    public void delete(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
