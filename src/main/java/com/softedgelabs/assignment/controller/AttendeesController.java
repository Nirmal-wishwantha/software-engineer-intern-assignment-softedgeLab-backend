package com.softedgelabs.assignment.controller;


import com.softedgelabs.assignment.dto.LoginDto;
import com.softedgelabs.assignment.dto.RegisterDto;
import com.softedgelabs.assignment.dto.ResponseDto;
import com.softedgelabs.assignment.service.AttendeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendees")
public class AttendeesController {

    final AttendeesService attendeesService;

    @Autowired
    public AttendeesController(AttendeesService attendeesService) {
        this.attendeesService = attendeesService;
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDto> attendeesRegister(@RequestBody RegisterDto registerDto) {
        ResponseDto register = attendeesService.register(registerDto);

        return new ResponseEntity<>(register, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> attendeesLogin(@RequestBody LoginDto loginDto) {
        ResponseDto login = attendeesService.login(loginDto);
        return new ResponseEntity<>(login, HttpStatus.CREATED);
    }

}
