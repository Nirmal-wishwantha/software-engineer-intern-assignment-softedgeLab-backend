package com.softedgelabs.assignment.repo;

import com.softedgelabs.assignment.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventsRepo extends JpaRepository<Events, Integer> {

    public Events findById(int eventId);

    List<Events> findByDate(LocalDate date);
    List<Events> findByLocation(String location);
    List<Events> findByTagsContaining(String tags);
}
