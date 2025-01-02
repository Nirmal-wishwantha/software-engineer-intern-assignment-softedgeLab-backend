package com.softedgelabs.assignment.service;


import com.softedgelabs.assignment.dto.EventDto;
import com.softedgelabs.assignment.dto.EventResponseDto;
import com.softedgelabs.assignment.dto.RegisterDto;

public interface EventsService {

    public EventResponseDto addEvent(EventDto eventDto);
}
