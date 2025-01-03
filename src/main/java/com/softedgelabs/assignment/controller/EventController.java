package com.softedgelabs.assignment.controller;

import com.softedgelabs.assignment.dto.EventDto;
import com.softedgelabs.assignment.dto.EventResponseDto;
import com.softedgelabs.assignment.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event")
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

}
