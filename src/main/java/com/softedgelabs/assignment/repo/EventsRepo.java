package com.softedgelabs.assignment.repo;

import com.softedgelabs.assignment.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventsRepo extends JpaRepository<Events, Integer> {

    public Events findById(int eventId);

    List<Events> findByDate(LocalDate date);
    List<Events> findByLocation(String location);
    List<Events> findByTagsContaining(String tags);

    @Query("SELECT e FROM Events e WHERE " +
            "(:startDate IS NULL OR e.date = :startDate) " +
            "AND (:location IS NULL OR e.location LIKE %:location%) " +
            "AND (:tags IS NULL OR e.tags LIKE %:tags%)")
    List<Events> findEventsWithFilters(
            @Param("startDate") LocalDate startDate,
            @Param("location") String location,
            @Param("tags") String tags);



}
