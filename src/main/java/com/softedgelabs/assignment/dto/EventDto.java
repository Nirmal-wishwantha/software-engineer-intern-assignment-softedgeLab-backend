package com.softedgelabs.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private String name;
    private String description;
    private LocalDate date;
    private String location;
    private String createdBy;
    private int capacity;
    private int remainingCapacity;
    private String tags;

}
