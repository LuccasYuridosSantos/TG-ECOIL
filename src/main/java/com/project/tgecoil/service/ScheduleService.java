package com.project.tgecoil.service;

import com.project.tgecoil.exceptions.ResourceNotFoundException;
import com.project.tgecoil.mappers.ScheduleMapper;
import com.project.tgecoil.model.api.GroupScheduleResponse;
import com.project.tgecoil.model.api.ScheduleRequest;
import com.project.tgecoil.model.api.ScheduleResponse;
import com.project.tgecoil.model.dto.Schedule;
import com.project.tgecoil.model.dto.StatusScheduler;
import com.project.tgecoil.repository.ContainerRepository;
import com.project.tgecoil.repository.ScheduleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static com.project.tgecoil.model.dto.StatusScheduler.ACEITO;
import static com.project.tgecoil.model.dto.StatusScheduler.AGUARDANDO;
import static com.project.tgecoil.model.dto.StatusScheduler.COMPLETADO;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final ContainerRepository containerRepository;
    private final ScheduleMapper mapper;
    private final ContainerService containerService;


    public ScheduleResponse createSchedule(final ScheduleRequest request) {
        final var schedule = mapper.mapToSchedule(request);
        containerRepository.findById(request.recipientId()).ifPresentOrElse(c -> {
            schedule.setRecipient(c);
        }, () -> {
            throw new ResourceNotFoundException("Container not found with %s" + request.recipientId());
        });
        schedule.setStatus(AGUARDANDO);
        final var response = repository.save(schedule);
        return mapper.mapToScheduleResponse(response);
    }

    public GroupScheduleResponse findGroupedSchedule(final String userId,
                                                     final Boolean completed) {
        final var schedules = Optional.ofNullable(repository.findAll())
                .filter(ObjectUtils::isNotEmpty)
                .orElse(Collections.emptyList());

        final var filteredSchedules = completed ? schedules.stream()
                .filter(s -> COMPLETADO.equals(s.getStatus()))
                .toList() : schedules;

        final var result = isNotBlank(userId) ? filteredSchedules.stream()
                .filter(schedule -> userId.equals(schedule.getUserId()))
                .toList() : filteredSchedules;

        final var groupedSchedules = result.stream()
                .collect(groupingBy(Schedule::getDate, mapping(mapper::mapToScheduleResponse, toList())));

        return new GroupScheduleResponse(groupedSchedules);
    }


    public void editStatus(final Long id,
                           final StatusScheduler status) {
        repository.findById(id).ifPresentOrElse(schedule -> {
            schedule.setStatus(status);
            repository.save(schedule);
        }, () -> {
            throw new ResourceNotFoundException("Schedule not found with %s" + id);
        });
    }

    public void completeSchedule(final Long id) {
        editStatus(id, COMPLETADO);
    }

    public void editSchedule(final Long id,
                             final @Valid ScheduleRequest request) {
        repository.findById(id).ifPresentOrElse(schedule -> {
            final Schedule updatedSchedule = mapper.mapToSchedule(request);
            updatedSchedule.setId(id);
            containerRepository.findById(request.recipientId()).ifPresentOrElse(c -> {
                updatedSchedule.setRecipient(c);
            }, () -> {
                throw new ResourceNotFoundException("Container not found with %s" + id);
            });
            repository.save(updatedSchedule);
        }, () -> {
            throw new ResourceNotFoundException("Schedule not found with id %s", id);
        });
    }

    public void deleteSchedule(final Long id) {
        repository.deleteById(id);
    }

    public void acceptedScheduler(final @NotNull Long id,
                                  final @NotBlank String userId) {
        repository.findById(id).ifPresentOrElse(schedule -> {
            schedule.setStatus(ACEITO);
            schedule.setCollectorUserId(userId);
            repository.save(schedule);
        }, () -> {
            throw new ResourceNotFoundException("Schedule not found with %s" + id);
        });
    }

    public List<ScheduleResponse> getSchedulerFilter(final StatusScheduler status,
                                                     final String userId,
                                                     final String collectorUserId) {
        List<Schedule> schedules = repository.findAll();

        if (isNotEmpty(status)) {
            schedules = schedules.stream()
                    .filter(schedule -> status.equals(schedule.getStatus()))
                    .toList();
        }

        if (isNotBlank(userId)) {
            schedules = schedules.stream()
                    .filter(schedule -> userId.equals(schedule.getUserId()))
                    .toList();
        }

        if (isNotBlank(collectorUserId)) {
            schedules = schedules.stream()
                    .filter(schedule -> collectorUserId.equals(schedule.getCollectorUserId()))
                    .toList();
        }

        return mapper.mapToSchedulesResponse(schedules);
    }
}
