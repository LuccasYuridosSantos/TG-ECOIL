package com.tg.ecoil.controller;

import com.tg.ecoil.model.api.GroupScheduleResponse;
import com.tg.ecoil.model.api.ScheduleRequest;
import com.tg.ecoil.model.api.ScheduleResponse;
import com.tg.ecoil.service.ScheduleService;
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
    public ResponseEntity<GroupScheduleResponse> createGroupedSchedule(@NotBlank @RequestParam final String userId,
                                                                       @RequestParam(required = false,
                                                                               defaultValue = "false")
                                                                       final boolean completed) {
        final var response = service.findGroupedSchedule(userId, completed);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Void> completeSchedule(@PathVariable @NotBlank final String id) {
        service.completeSchedule(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editSchedule(@PathVariable @NotBlank final String id,
                                             @Valid @RequestBody final ScheduleRequest request) {
        service.editSchedule(id, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable @NotBlank final String id) {
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}