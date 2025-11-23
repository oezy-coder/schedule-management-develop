package com.example.schedulemanagementdevelop.schedule.controller;

import com.example.schedulemanagementdevelop.schedule.dto.ScheduleRequest;
import com.example.schedulemanagementdevelop.schedule.dto.ScheduleResponse;
import com.example.schedulemanagementdevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/users/{userId}/schedules")
    public ResponseEntity<ScheduleResponse> saveSchedule(
            @PathVariable Long userId,
            @RequestBody ScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(userId, request));
    }

    @GetMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedules(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(userId));
    }

    @PutMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    @DeleteMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
