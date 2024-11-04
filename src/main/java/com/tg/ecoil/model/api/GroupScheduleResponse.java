package com.tg.ecoil.model.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record GroupScheduleResponse(Map<LocalDate, List<ScheduleResponse>> groupScheduler) {
}
