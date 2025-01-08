package com.softedgelabs.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String name;
    private String email;
    private String massages;

    public ResponseDto(String email, String massages) {
        this.email = email;
        this.massages = massages;
    }
}
