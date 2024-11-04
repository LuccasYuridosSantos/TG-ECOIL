package com.tg.ecoil.mappers;

import com.tg.ecoil.model.api.ScheduleRequest;
import com.tg.ecoil.model.api.ScheduleResponse;
import com.tg.ecoil.model.dto.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule mapToSchedule(ScheduleRequest scheduleRequest);

    ScheduleResponse mapToScheduleResponse(Schedule schedule);
}
