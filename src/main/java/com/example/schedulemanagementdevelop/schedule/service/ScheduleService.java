package com.example.schedulemanagementdevelop.schedule.service;

import com.example.schedulemanagementdevelop.exception.CustomException;
import com.example.schedulemanagementdevelop.exception.ErrorCode;
import com.example.schedulemanagementdevelop.schedule.dto.ScheduleRequest;
import com.example.schedulemanagementdevelop.schedule.dto.ScheduleResponse;
import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import com.example.schedulemanagementdevelop.schedule.repository.ScheduleRepository;
import com.example.schedulemanagementdevelop.user.entity.User;
import com.example.schedulemanagementdevelop.user.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 생성
    @Transactional
    public ScheduleResponse save(Long userId, ScheduleRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Schedule schedule = new Schedule(request.getTitle(), request.getContents(), user);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUser().getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public ScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        return new ScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponse> getAll(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<Schedule> schedules = scheduleRepository.findAllByUser(user);

        return schedules.stream()
                .map(ScheduleResponse::newResponseEntity)
                .toList();
    }

    // 일정 수정
    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleRequest request) {
        if (StringUtils.isEmpty(request.getContents()) && StringUtils.isEmpty(request.getTitle())) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        schedule.update(request.getTitle(), request.getContents());

        return new ScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId) {
        try {
            scheduleRepository.deleteById(scheduleId);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
    }
}
