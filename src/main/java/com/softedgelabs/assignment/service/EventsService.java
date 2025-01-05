package com.softedgelabs.assignment.service;


import com.softedgelabs.assignment.dto.EventAnalyticsDto;
import com.softedgelabs.assignment.dto.EventDto;
import com.softedgelabs.assignment.dto.EventResponseDto;
import com.softedgelabs.assignment.dto.RegisterDto;

import java.util.List;

public interface EventsService {

    public EventResponseDto addEvent(EventDto eventDto);

    public EventResponseDto updateEvent(EventDto eventDto,Integer id);

    public String deleteEvent(Integer eventId);

    public List<EventResponseDto> getAllEvents();

    public EventAnalyticsDto getEventAnalytics(Integer eventId);


}
