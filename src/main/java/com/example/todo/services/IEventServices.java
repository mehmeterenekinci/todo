package com.example.todo.services;

import com.example.todo.dto.EventDto;
import com.example.todo.dto.GeneralResponse;
import com.example.todo.dto.UserDto;

public interface IEventServices {

    GeneralResponse createEvent(EventDto eventDto);

    GeneralResponse deleteEvent(EventDto eventDto);

    GeneralResponse updateEvent(EventDto eventDto);

    GeneralResponse listEvents(EventDto eventDto);
}
