package com.softedgelabs.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeesDto {

    private Integer id;
    private String attendeeName;
    private String attendeeEmail;
    private String massages;


}
