package com.project.tgecoil.model.api;

public record ScheduleResponse(String id, String userId, String name, String time, String location, String description, String date, Boolean completed) {
}
