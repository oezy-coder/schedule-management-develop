package com.example.schedulemanagementdevelop.schedule.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequest {

    @Size(max = 50, message = "제목은 {max}자 이하로 입력해주세요.")
    private String title;

    private String contents;

    @NotEmpty
    @Positive
    private Long userId;
}
