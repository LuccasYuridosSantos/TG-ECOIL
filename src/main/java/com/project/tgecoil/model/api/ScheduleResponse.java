package com.project.tgecoil.model.api;

import com.project.tgecoil.model.dto.StatusScheduler;

public record ScheduleResponse(String id, String userId, String name, String time, String location, String description, String date, StatusScheduler status, String collectorUserId, ContainerResponse recipient) {
}