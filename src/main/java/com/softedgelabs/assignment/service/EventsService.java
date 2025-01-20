package com.softedgelabs.assignment.service;


import com.softedgelabs.assignment.dto.*;
import com.softedgelabs.assignment.entity.Events;

import java.time.LocalDate;
import java.util.List;

public interface EventsService {

    public EventResponseDto addEvent(EventDto eventDto);

    public EventResponseDto updateEvent(EventDto eventDto,Integer id);

    public String deleteEvent(Integer eventId);

    public List<EventResponseDto> getAllEvents();

    public EventAnalyticsDto getEventAnalytics(Integer eventId);

    public List<EventDto> getFilteredEvents(LocalDate startDate,String location,String tags);


}
