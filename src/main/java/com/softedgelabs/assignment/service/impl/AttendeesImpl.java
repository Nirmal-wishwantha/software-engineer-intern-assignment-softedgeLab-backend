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

        System.out.println(attendeesDto.getAttendeeEmail());
        System.out.println(attendeesDto.getAttendeeName());

        Attendees byAttendeeEmail = attendeesRepo.findByAttendeeEmailAndEvent_Id(attendeesDto.getAttendeeEmail(),id);
        if (byAttendeeEmail != null) {
            return new AttendeesDto(null,attendeesDto.getAttendeeName(),attendeesDto.getAttendeeEmail(),attendeesDto.getMobile()
                    ,"Already registered");

        }

        Optional<Events> optionalEvent = eventsRepo.findById(id);

        if (optionalEvent.isEmpty()) {
            throw new RuntimeException("Event not found with ID: " + id);
        }

        Events event = optionalEvent.get();

        if (event.getRemainingCapacity() <= 0) {
            throw new RuntimeException("Event is fully booked. No more attendees can be registered.");
        }

        Attendees attendees = new Attendees();
        attendees.setAttendeeName(attendeesDto.getAttendeeName());
        attendees.setAttendeeEmail(attendeesDto.getAttendeeEmail());
        attendees.setMobile(attendeesDto.getMobile());
        attendees.setEvent(event);

        System.out.println(attendees.getAttendeeName());
        System.out.println(attendees.getAttendeeEmail());

        Attendees savedAttendee = attendeesRepo.save(attendees);

        event.setRemainingCapacity(event.getRemainingCapacity() - 1);
        eventsRepo.save(event);

        return new AttendeesDto(
                savedAttendee.getAttendeeId(),
                savedAttendee.getAttendeeName(),
                savedAttendee.getAttendeeEmail(),
                savedAttendee.getMobile(),
                "Attendee registered successfully"
        );
    }


}
