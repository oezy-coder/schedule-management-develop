package com.example.schedulemanagementdevelop.login.service;

import com.example.schedulemanagementdevelop.login.dto.LoginRequest;
import com.example.schedulemanagementdevelop.login.dto.LoginResponse;
import com.example.schedulemanagementdevelop.exception.CustomException;
import com.example.schedulemanagementdevelop.exception.ErrorCode;
import com.example.schedulemanagementdevelop.user.entity.User;
import com.example.schedulemanagementdevelop.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    // 로그인 기능 (세션 기반)
    @Transactional
    public LoginResponse login(LoginRequest request, HttpServletRequest httpRequest) {

        String email = request.getEmail().trim();
        String password = request.getPassword().trim();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }
        httpRequest.getSession(true).setAttribute("userId", user.getId());
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getUserName()
        );
    }
}
