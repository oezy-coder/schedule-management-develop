package com.example.schedulemanagementdevelop.login.controller;

import com.example.schedulemanagementdevelop.login.dto.LoginRequest;
import com.example.schedulemanagementdevelop.login.dto.LoginResponse;
import com.example.schedulemanagementdevelop.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인 기능 (세션 생성)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
           @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(request, httpRequest));
    }
}
