package com.project.tgecoil.mappers;

import com.project.tgecoil.model.api.ScheduleRequest;
import com.project.tgecoil.model.api.ScheduleResponse;
import com.project.tgecoil.model.dto.Schedule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule mapToSchedule(ScheduleRequest scheduleRequest);

    ScheduleResponse mapToScheduleResponse(Schedule schedule);

    List<ScheduleResponse> mapToSchedulesResponse(List<Schedule> schedules);
}
