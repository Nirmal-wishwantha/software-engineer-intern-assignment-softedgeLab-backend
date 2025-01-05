package com.softedgelabs.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {

    private Integer eventId;
    private String name;
    private String description;
    private LocalDate date;
    private String location;
    private String createdBy;
    private int capacity;
    private int remainingCapacity;
    private String tags;
    private String massages;

    public EventResponseDto(String name, String description, LocalDate date, String location, String createdBy, int capacity, int remainingCapacity, String tags, String massages) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.createdBy = createdBy;
        this.capacity = capacity;
        this.remainingCapacity = remainingCapacity;
        this.tags = tags;
        this.massages = massages;
    }
}
