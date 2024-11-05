package com.tg.ecoil.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "schedules")
public class Schedule {

    @Id
    private String id;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Time is required")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9] [APap][mM]$", message = "Time must be in the format 'hh:mm AM/PM'")
    private String time;

    @NotBlank(message = "Location is required")
    private String location;


    private String description;

    @NotNull(message = "Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private boolean completed;

    public Schedule() {
    }

    public Schedule(final String id,
                    final String userId,
                    final String name,
                    final String time,
                    final String location,
                    final String description,
                    final LocalDate date,
                    final boolean completed) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.time = time;
        this.location = location;
        this.description = description;
        this.date = date;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(final boolean completed) {
        this.completed = completed;
    }
}

