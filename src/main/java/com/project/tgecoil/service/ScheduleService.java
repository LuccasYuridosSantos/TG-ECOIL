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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

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

    public GroupScheduleResponse findGroupedSchedule(final String userId,
                                                     final Boolean completed) {
        final var filteredSchedules = Optional.ofNullable(repository.findAllByUserId(userId))
                .filter(ObjectUtils::isNotEmpty)
                .map(list -> completed ? list.stream().filter(Schedule::isCompleted).collect(Collectors.toList()) :
                        list)
                .orElse(Collections.emptyList());

        final var groupedSchedules = filteredSchedules.stream()
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
