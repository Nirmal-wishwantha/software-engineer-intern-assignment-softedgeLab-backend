package com.softedgelabs.assignment.service.impl;

import com.softedgelabs.assignment.dto.LoginDto;
import com.softedgelabs.assignment.dto.RegisterDto;
import com.softedgelabs.assignment.dto.ResponseDto;
import com.softedgelabs.assignment.entity.User;
import com.softedgelabs.assignment.repo.UserRepo;
import com.softedgelabs.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserImpl implements UserService {

    final UserRepo attendeesRepo;

    @Autowired
    public UserImpl(UserRepo attendeesRepo) {
        this.attendeesRepo = attendeesRepo;
    }


    @Override
    public ResponseDto register(RegisterDto registerDto) {

        User byEmail = attendeesRepo.findByEmail(registerDto.getEmail());

        if (byEmail != null) {
            return new ResponseDto(registerDto.getName(),registerDto.getEmail(),"Already registered this username");
        }

        String encodedPassword = Base64.getEncoder().encodeToString(registerDto.getPassword().getBytes());

        User save = attendeesRepo.save(new User(null, registerDto.getName(), registerDto.getEmail(),
                encodedPassword));

        return new ResponseDto(save.getName(),save.getEmail(),"Registered successfully");
    }

    @Override
    public ResponseDto login(LoginDto loginDto) {

        User byEmail = attendeesRepo.findByEmail(loginDto.getEmail());
        String encodedPassword = Base64.getEncoder().encodeToString(loginDto.getPassword().getBytes());

        if (byEmail != null) {

            if (byEmail.getPassword().equals(encodedPassword)) {
                return new ResponseDto(byEmail.getName(),byEmail.getEmail(),"Logged in successfully");
            }
            return new ResponseDto(byEmail.getName(),byEmail.getEmail(),"Wrong password");
        }
        return new ResponseDto(loginDto.getEmail(),"email not registered");


    }


}
