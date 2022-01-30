package com.example.todo.services.impl;

import com.example.todo.dto.EventDto;
import com.example.todo.dto.GeneralResponse;
import com.example.todo.dto.UserDto;
import com.example.todo.entities.Event;
import com.example.todo.mapper.EventMapper;
import com.example.todo.repositories.EventRepository;
import com.example.todo.services.IEventServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServices implements IEventServices {

    EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final GeneralResponse response;

    public EventServices(EventMapper eventMapper, GeneralResponse response) {
        this.eventMapper = eventMapper;
        this.response = response;
    }

    @Override
    public GeneralResponse createEvent(EventDto eventDto) {
        try {
            Event event = eventRepository.save(eventMapper.dtoToEntity(eventDto));
            response.setCode(200);
            response.setSuccess(true);
            response.setData(eventMapper.entityToDto(event));
            response.setMessage("Event created.");
        } catch (Exception e) {
            response.setCode(400);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public GeneralResponse deleteEvent(EventDto eventDto) {
        try {
            eventRepository.deleteEvent(eventDto);
            response.setCode(200);
            response.setSuccess(true);
            response.setMessage("Event deleted.");
        } catch (Exception e) {
            response.setCode(400);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public GeneralResponse updateEvent(EventDto eventDto) {
        try {
            Event event = eventRepository.find(eventDto);
            event = eventRepository.save(eventMapper.dtoToEntity(eventDto));
            response.setCode(200);
            response.setSuccess(true);
            response.setData(eventMapper.entityToDto(event));
            response.setMessage("Event updated.");
        } catch (Exception e) {
            response.setCode(400);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public GeneralResponse listEvents(EventDto eventDto) {
        try {
            List<Event> eventList = eventRepository.findAll(eventDto);
            List<EventDto> dtoList = new ArrayList<>();
            response.setCode(200);
            response.setSuccess(true);
            response.setData(eventList.stream().map(e -> dtoList.add(eventMapper.entityToDto(e))));
            response.setMessage("Event updated.");
        } catch (Exception e) {
            response.setCode(400);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
