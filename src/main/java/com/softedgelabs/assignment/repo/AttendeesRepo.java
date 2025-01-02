package com.softedgelabs.assignment.repo;

import com.softedgelabs.assignment.entity.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeesRepo extends JpaRepository<Attendees, Integer> {

    Attendees findByEmail(String email);
}
