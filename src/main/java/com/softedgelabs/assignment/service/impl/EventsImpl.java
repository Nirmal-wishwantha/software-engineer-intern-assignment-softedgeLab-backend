package com.softedgelabs.assignment.service.impl;

import com.softedgelabs.assignment.dto.EventAnalyticsDto;
import com.softedgelabs.assignment.dto.EventDto;
import com.softedgelabs.assignment.dto.EventResponseDto;
import com.softedgelabs.assignment.entity.Attendees;
import com.softedgelabs.assignment.entity.Events;
import com.softedgelabs.assignment.repo.AttendeesRepo;
import com.softedgelabs.assignment.repo.EventsRepo;
import com.softedgelabs.assignment.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventsImpl implements EventsService {

   final EventsRepo eventsRepo;
   final AttendeesRepo attendeesRepo;

   @Autowired
    public EventsImpl(EventsRepo eventsRepo, AttendeesRepo attendeesRepo) {
        this.eventsRepo = eventsRepo;
       this.attendeesRepo = attendeesRepo;
   }


    @Override
    public EventResponseDto addEvent(EventDto eventDto) {

        if(eventDto.getCapacity() <= 0){
            return new EventResponseDto(eventDto.getName(),eventDto.getDescription(),eventDto.getDate(),eventDto.getLocation(),
                    eventDto.getCreatedBy(),eventDto.getCapacity(),eventDto.getRemainingCapacity(),eventDto.getTags(),
                    "This capacity must be greater than zero");
        }

        if(eventDto.getCapacity() > 100){
            return new EventResponseDto(eventDto.getName(),eventDto.getDescription(),eventDto.getDate(),eventDto.getLocation(),
                    eventDto.getCreatedBy(),eventDto.getCapacity(),eventDto.getRemainingCapacity(),eventDto.getTags(),
                    "This capacity must be lesthan than 100");
        }

        Events save = eventsRepo.save(new Events(null, eventDto.getName(), eventDto.getDescription(), eventDto.getDate(),
                eventDto.getLocation(), eventDto.getCreatedBy(), eventDto.getCapacity(), eventDto.getRemainingCapacity(),
                eventDto.getTags()));


        return new EventResponseDto(save.getId(),save.getName(),save.getDescription(),save.getDate(),save.getLocation(),
                save.getCreatedBy(),save.getCapacity(),save.getRemainingCapacity(),save.getTags(),"New Event Add Successful");
    }


    @Override
    public String deleteEvent(Integer id) {

        Optional<Events> byId = eventsRepo.findById(id);

        if (byId.isPresent()) {
            eventsRepo.deleteById(id);
            return "Event Deleted Successfully";
        }
        return "Event Not Found";

    }


    @Override
    public EventResponseDto updateEvent(EventDto eventDto, Integer id) {

        Events existingEvent = eventsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));

        if (existingEvent.getDate().isBefore(LocalDate.now())) {

            existingEvent.setName(eventDto.getName());
            existingEvent.setDescription(eventDto.getDescription());
            existingEvent.setDate(eventDto.getDate());
            existingEvent.setLocation(eventDto.getLocation());
            existingEvent.setCreatedBy(eventDto.getCreatedBy());
            existingEvent.setCapacity(eventDto.getCapacity());
            existingEvent.setRemainingCapacity(eventDto.getRemainingCapacity());
            existingEvent.setTags(eventDto.getTags());

            Events updatedEvent = eventsRepo.save(existingEvent);

            return new EventResponseDto(
                    updatedEvent.getId(),
                    updatedEvent.getName(),
                    updatedEvent.getDescription(),
                    updatedEvent.getDate(),
                    updatedEvent.getLocation(),
                    updatedEvent.getCreatedBy(),
                    updatedEvent.getCapacity(),
                    updatedEvent.getRemainingCapacity(),
                    updatedEvent.getTags(),
                    "Event updated successfully"
            );
        }

        return new EventResponseDto(existingEvent.getId(),existingEvent.getName(),existingEvent.getDescription(),
                existingEvent.getDate(),existingEvent.getLocation(),existingEvent.getCreatedBy(),existingEvent.getCapacity(),
                existingEvent.getRemainingCapacity(),existingEvent.getTags(),"Date or Location Already booking");

    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        List<Events> all = eventsRepo.findAll();

        List<EventResponseDto> allDto = new ArrayList<>();

        for (Events event : all) {
            allDto.add(new EventResponseDto(
                    event.getId(),
                    event.getName(),
                    event.getDescription(),
                    event.getDate(),
                    event.getLocation(),
                    event.getCreatedBy(),
                    event.getCapacity(),
                    event.getRemainingCapacity(),
                    event.getTags(),
                    "Event found successfully"
            ));
        }
        return allDto;

    }

    @Override
    public EventAnalyticsDto getEventAnalytics(Integer eventId) {

        Optional<Events> byId = eventsRepo.findById(eventId);

        if (byId.isPresent()) {
            List<Attendees> attendeesList = attendeesRepo.findByEventId(eventId);

            Events events = byId.get();

            int totalAttendees = attendeesList.size();

            double capacityUtilization = ((double) totalAttendees / events.getCapacity() * 100);

            return new EventAnalyticsDto(events.getId(),events.getName(),totalAttendees,events.getCapacity(),capacityUtilization,
                    "Total filed event " + totalAttendees+"%" + " attendees");

        }

        return new EventAnalyticsDto(byId.get().getId(),byId.get().getName(),"Event Not Found");
    }


}
