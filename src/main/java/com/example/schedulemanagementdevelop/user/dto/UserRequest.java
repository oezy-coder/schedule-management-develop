package com.example.schedulemanagementdevelop.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, max = 20, message = "유저명은 {min}자 이상, {max}자 이하여야 합니다.")
    @Pattern(
            regexp = "^[a-zA-Z가-힣 ]+$",
            message = "한글, 영문, 공백만 허용하며 특수문자와 숫자는 사용할 수 없습니다.")
    private String userName;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9!@#$%^&*()_+\\-\\[\\]{};':\"|,.<>/?]+$",
            message = "비밀번호는 숫자, 영어, 특수문자만 사용할 수 있습니다.")
    private String password;
}
