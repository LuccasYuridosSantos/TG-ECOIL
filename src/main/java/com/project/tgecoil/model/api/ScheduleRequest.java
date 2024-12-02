package com.project.tgecoil.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ScheduleRequest(@NotBlank(message = "Name is required")
                              String name,

                              @NotBlank(message = "User ID is required")
                              String userId,

                              @NotBlank(message = "Time is required, Time must be in the format 'hh:mm AM/PM'")
                              String time,

                              @NotBlank(message = "Location is required")
                              String location,

                              String description,

                              @NotNull(message = "Date is required")
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                              LocalDate date
) {
}
