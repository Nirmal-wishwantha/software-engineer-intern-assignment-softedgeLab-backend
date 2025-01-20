package com.softedgelabs.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterEventRequestDto {

    private LocalDate Date;
    private String location;
    private String tags;

}