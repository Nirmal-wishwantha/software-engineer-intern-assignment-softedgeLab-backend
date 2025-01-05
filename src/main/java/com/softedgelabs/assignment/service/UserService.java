package com.softedgelabs.assignment.service;

import com.softedgelabs.assignment.dto.LoginDto;
import com.softedgelabs.assignment.dto.RegisterDto;
import com.softedgelabs.assignment.dto.ResponseDto;

public interface UserService {

    public ResponseDto register(RegisterDto registerDto);
    public ResponseDto login(LoginDto loginDto);

}
