package com.softedgelabs.assignment.service.impl;

import com.softedgelabs.assignment.dto.EventDto;
import com.softedgelabs.assignment.dto.EventResponseDto;
import com.softedgelabs.assignment.entity.Events;
import com.softedgelabs.assignment.repo.EventsRepo;
import com.softedgelabs.assignment.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsImpl implements EventsService {

   final EventsRepo eventsRepo;

   @Autowired
    public EventsImpl(EventsRepo eventsRepo) {
        this.eventsRepo = eventsRepo;
    }


    @Override
    public EventResponseDto addEvent(EventDto eventDto) {
        Events save = eventsRepo.save(new Events(null, eventDto.getName(), eventDto.getDescription(), eventDto.getDate(),
                eventDto.getLocation(), eventDto.getCreatedBy(), eventDto.getCapacity(), eventDto.getRemainingCapacity(),
                eventDto.getTags(), null));


        return new EventResponseDto(save.);
    }
}
