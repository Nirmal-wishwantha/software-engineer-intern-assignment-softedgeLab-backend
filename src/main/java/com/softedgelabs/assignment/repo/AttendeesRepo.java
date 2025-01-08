package com.softedgelabs.assignment.repo;

import com.softedgelabs.assignment.entity.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeesRepo extends JpaRepository<Attendees, Integer> {

    List<Attendees> findByEventId(int eventId);

    Attendees findByAttendeeEmailAndEvent_Id(String email,Integer eventId);
}
