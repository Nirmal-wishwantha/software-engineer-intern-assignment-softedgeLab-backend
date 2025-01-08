package com.softedgelabs.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAnalyticsDto {

    private Integer eventId;
    private String eventName;
    private Integer totalAttendees;
    private Integer capacity;
    private Double capacityUtilization;
    private String massage;

    public EventAnalyticsDto(Integer eventId, String eventName, String massage) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.massage = massage;
    }
}
