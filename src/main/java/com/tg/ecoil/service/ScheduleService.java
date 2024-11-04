package com.tg.ecoil.service;

import com.tg.ecoil.mappers.ScheduleMapper;
import com.tg.ecoil.model.api.GroupScheduleResponse;
import com.tg.ecoil.model.api.ScheduleRequest;
import com.tg.ecoil.model.dto.Schedule;
import com.tg.ecoil.repository.ScheduleRepository;
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


    public void createSchedule(final ScheduleRequest request) {
        final Schedule schedule = mapper.mapToSchedule(request);
        repository.save(schedule);
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

    public void completeSchedule(final String id) {
        repository.findById(id)
                .ifPresentOrElse(schedule -> {
                    schedule.setCompleted(true);
                    repository.save(schedule);
                }, () -> {
                    throw new RuntimeException("Schedule not found with id " + id);
                });
    }

    public void editSchedule(final String id,
                             final @Valid ScheduleRequest request) {
        repository.findById(id)
                .ifPresentOrElse(schedule -> {
                    final Schedule updatedSchedule = mapper.mapToSchedule(request);
                    updatedSchedule.setId(id);
                    repository.save(updatedSchedule);
                }, () -> {
                    throw new RuntimeException("Schedule not found with id " + id);
                });
    }

    public void deleteSchedule(final String id) {
        repository.deleteById(id);
    }
}
