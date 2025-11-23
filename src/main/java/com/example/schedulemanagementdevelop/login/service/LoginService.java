package com.example.schedulemanagementdevelop.login.service;

import com.example.schedulemanagementdevelop.login.dto.LoginRequest;
import com.example.schedulemanagementdevelop.login.dto.LoginResponse;
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

    @Transactional
    public LoginResponse login(LoginRequest request, HttpServletRequest httpRequest) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("없는 이메일 입니다."));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        httpRequest.getSession(true).setAttribute("userId", user.getId());
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getUserName()
        );
    }
}
