package com.example.schedulemanagementdevelop.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @Size(min = 2, max = 20, message = "유저명은 {min}자 이상, {max}자 이하여야 합니다.")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣 ]+$",
            message = "한글, 영문, 공백만 허용하며 특수문자와 숫자는 사용할 수 없습니다.")
    private String userName;

    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다.")
    @NotBlank
    @Pattern(
            regexp = "^[A-Za-z0-9!@#$%^&*()_+\\-\\[\\]{};':\"|,.<>/?]+$",
            message = "비밀번호는 숫자, 영어, 특수문자만 사용할 수 있습니다.")
    private String password;

    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9!@#$%^&*()_+\\-\\[\\]{};':\"|,.<>/?]+$",
            message = "비밀번호는 숫자, 영어, 특수문자만 사용할 수 있습니다.")
    private String newPassword;
}
