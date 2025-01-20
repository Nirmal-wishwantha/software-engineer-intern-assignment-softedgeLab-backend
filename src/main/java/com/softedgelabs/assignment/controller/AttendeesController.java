package com.softedgelabs.assignment.controller;

import com.softedgelabs.assignment.dto.AttendeesDto;
import com.softedgelabs.assignment.service.AttendeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeesController {

    final AttendeesService attendeesService;

    @Autowired
    public AttendeesController(AttendeesService attendeesService) {
        this.attendeesService = attendeesService;
    }


    @PostMapping("/{id}")
    public ResponseEntity<AttendeesDto> attendeesRegister(@PathVariable Integer id, @RequestBody AttendeesDto attendeesDto) {
        AttendeesDto attendeesDto1 = attendeesService.registerAttendees(id, attendeesDto);
        return new ResponseEntity<>(attendeesDto1, HttpStatus.CREATED);
    }


}
