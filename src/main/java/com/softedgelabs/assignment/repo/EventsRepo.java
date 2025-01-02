package com.softedgelabs.assignment.repo;

import com.softedgelabs.assignment.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepo extends JpaRepository<Events, Integer> {
}
