package com.example.schedulemanagementdevelop.schedule.dto;

import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleResponse(Long id, Long userId, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }

    public static ScheduleResponse newResponseEntity(Schedule schedule) {
        return new ScheduleResponse(schedule);
    }
}
