package com.softedgelabs.assignment.controller;

import com.softedgelabs.assignment.dto.EventAnalyticsDto;
import com.softedgelabs.assignment.dto.EventDto;
import com.softedgelabs.assignment.dto.EventResponseDto;
import com.softedgelabs.assignment.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    final EventsService eventsService;
    @Autowired
    public EventController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> addNewEvent(@RequestBody EventDto eventDto) {
        EventResponseDto eventResponseDto = eventsService.addEvent(eventDto);
        return new ResponseEntity<>(eventResponseDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id) {
        String s = eventsService.deleteEvent(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent( @PathVariable Integer id,@RequestBody EventDto eventDto) {
        EventResponseDto eventResponseDto = eventsService.updateEvent(eventDto, id);
        return new ResponseEntity<>(eventResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getEvents() {
        List<EventResponseDto> allEvents = eventsService.getAllEvents();
        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }

    @GetMapping("/analytics/{id}")
    public ResponseEntity<EventAnalyticsDto> getEventAnalytics(@PathVariable Integer id) {
        EventAnalyticsDto eventAnalytics = eventsService.getEventAnalytics(id);
        return new ResponseEntity<>(eventAnalytics, HttpStatus.OK);
    }


}
