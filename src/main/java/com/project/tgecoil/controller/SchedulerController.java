package com.project.tgecoil.controller;

import com.project.tgecoil.model.api.GroupScheduleResponse;
import com.project.tgecoil.model.api.ScheduleRequest;
import com.project.tgecoil.model.api.ScheduleResponse;
import com.project.tgecoil.model.dto.StatusScheduler;
import com.project.tgecoil.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/scheduler",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SchedulerController {

    private final ScheduleService service;

    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody @NotNull final ScheduleRequest request) {
        final var response = service.createSchedule(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/grouped")
    public ResponseEntity<GroupScheduleResponse> createGroupedSchedule(@RequestParam(required = false) final String userId,
                                                                       @RequestParam(required = false,
                                                                               defaultValue = "false")
                                                                       final boolean completed) {
        final var response = service.findGroupedSchedule(userId, completed);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ScheduleResponse>> getSchedulerFilter(@RequestParam(required = false) final StatusScheduler status,
                                                                     @RequestParam(required = false) final String userId,
                                                                     @RequestParam(required = false) final String collectorUserId) {
        final var response = service.getSchedulerFilter(status, userId, collectorUserId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Void> completeSchedule(@PathVariable @NotNull final Long id) {
        service.completeSchedule(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Void> completeSchedule(@PathVariable("id") @NotNull final Long id,
                                                 @PathVariable("status") @NotNull final StatusScheduler status) {
        service.editStatus(id, status);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/accepted/{id}/collector/{userId}")
    public ResponseEntity<Void> acceptedSchedule(@PathVariable("id") @NotNull final Long id,
                                                 @PathVariable("userId") @NotBlank final String userId) {
        service.acceptedScheduler(id, userId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editSchedule(@PathVariable @NotNull final Long id,
                                             @Valid @RequestBody final ScheduleRequest request) {
        service.editSchedule(id, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable @NotNull final Long id) {
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}