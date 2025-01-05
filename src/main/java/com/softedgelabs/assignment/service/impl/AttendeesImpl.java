package com.softedgelabs.assignment.service.impl;

import com.softedgelabs.assignment.dto.AttendeesDto;
import com.softedgelabs.assignment.entity.Attendees;
import com.softedgelabs.assignment.entity.Events;
import com.softedgelabs.assignment.repo.AttendeesRepo;
import com.softedgelabs.assignment.repo.EventsRepo;
import com.softedgelabs.assignment.service.AttendeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendeesImpl implements AttendeesService {

    final EventsRepo eventsRepo;
    final AttendeesRepo attendeesRepo;

    @Autowired
    public AttendeesImpl(EventsRepo eventsRepo, AttendeesRepo attendeesRepo) {
        this.eventsRepo = eventsRepo;
        this.attendeesRepo = attendeesRepo;
    }

    @Override
    public AttendeesDto registerAttendees(Integer id, AttendeesDto attendeesDto) {

        System.out.println(attendeesDto.getName());
        System.out.println(attendeesDto.getEmail());

        // Fetch the event
        Optional<Events> optionalEvent = eventsRepo.findById(id);

        if (optionalEvent.isEmpty()) {
            throw new RuntimeException("Event not found with ID: " + id);
        }

        Events event = optionalEvent.get();

        // Validate event capacity
        if (event.getRemainingCapacity() <= 0) {
            throw new RuntimeException("Event is fully booked. No more attendees can be registered.");
        }

        // Create the Attendee object
        Attendees attendees = new Attendees();
        attendees.setAttendeeName(attendeesDto.getName());
        attendees.setAttendeeEmail(attendeesDto.getEmail());
        attendees.setEvent(event);

        System.out.println(attendees.getAttendeeName());
        System.out.println(attendees.getAttendeeEmail());

        // Save the attendee to the database
        Attendees savedAttendee = attendeesRepo.save(attendees);

        // Update the event's remaining capacity
        event.setRemainingCapacity(event.getRemainingCapacity() - 1);
        eventsRepo.save(event);


        // Return the response DTO
        return new AttendeesDto(
                savedAttendee.getAttendeeId(),
                savedAttendee.getAttendeeName(),
                savedAttendee.getAttendeeEmail(),
                "Attendee registered successfully"
        );
    }


}
