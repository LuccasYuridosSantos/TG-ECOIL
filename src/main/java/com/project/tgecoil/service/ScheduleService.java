package com.project.tgecoil.service;

import com.project.tgecoil.exceptions.ResourceNotFoundException;
import com.project.tgecoil.mappers.ScheduleMapper;
import com.project.tgecoil.model.api.GroupScheduleResponse;
import com.project.tgecoil.model.api.ScheduleRequest;
import com.project.tgecoil.model.api.ScheduleResponse;
import com.project.tgecoil.model.dto.Schedule;
import com.project.tgecoil.repository.ScheduleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final ScheduleMapper mapper;


    public ScheduleResponse createSchedule(final ScheduleRequest request) {
        final var schedule = mapper.mapToSchedule(request);
        final var response = repository.save(schedule);
        return mapper.mapToScheduleResponse(response);
    }

    public GroupScheduleResponse findGroupedSchedule(final String userId, final Boolean completed) {
    final var schedules = Optional.ofNullable(repository.findAll())
            .filter(ObjectUtils::isNotEmpty)
            .orElse(Collections.emptyList());

    final var filteredSchedules = completed ? schedules.stream()
            .filter(Schedule::isCompleted)
            .toList() : schedules;

    final var result = isNotBlank(userId) ? filteredSchedules.stream()
            .filter(schedule -> userId.equals(schedule.getUserId()))
            .toList() : filteredSchedules;

    final var groupedSchedules = result.stream()
            .collect(groupingBy(Schedule::getDate, mapping(mapper::mapToScheduleResponse, toList())));

    return new GroupScheduleResponse(groupedSchedules);
}

    public void completeSchedule(final Long id) {
        repository.findById(id).ifPresentOrElse(schedule -> {
            schedule.setCompleted(true);
            repository.save(schedule);
        }, () -> {
            throw new ResourceNotFoundException("Schedule not found with %s" + id);
        });
    }

    public void editSchedule(final Long id,
                             final @Valid ScheduleRequest request) {
        repository.findById(id).ifPresentOrElse(schedule -> {
            final Schedule updatedSchedule = mapper.mapToSchedule(request);
            updatedSchedule.setId(id);
            repository.save(updatedSchedule);
        }, () -> {
            throw new ResourceNotFoundException("Schedule not found with id %s", id);
        });
    }

    public void deleteSchedule(final Long id) {
        repository.deleteById(id);
    }
}
