package com.example.schedulemanagementdevelop.schedule.controller;

import com.example.schedulemanagementdevelop.schedule.dto.ScheduleRequest;
import com.example.schedulemanagementdevelop.schedule.dto.ScheduleResponse;
import com.example.schedulemanagementdevelop.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponse> saveSchedule(@Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request.getUserId(), request));
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        final ScheduleResponse response = scheduleService.getOne(scheduleId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 전체 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedules(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(userId));
    }

    // 일정 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long scheduleId,
           @Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
