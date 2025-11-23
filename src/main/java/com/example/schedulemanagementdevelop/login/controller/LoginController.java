package com.example.schedulemanagementdevelop.login.controller;

import com.example.schedulemanagementdevelop.login.dto.LoginRequest;
import com.example.schedulemanagementdevelop.login.dto.LoginResponse;
import com.example.schedulemanagementdevelop.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(request, httpRequest));
    }
}
