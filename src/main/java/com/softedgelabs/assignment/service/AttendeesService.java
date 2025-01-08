package com.softedgelabs.assignment.service;

import com.softedgelabs.assignment.dto.AttendeesDto;
import com.softedgelabs.assignment.entity.Attendees;

public interface AttendeesService {

    public AttendeesDto registerAttendees(Integer id,AttendeesDto attendees);
}
