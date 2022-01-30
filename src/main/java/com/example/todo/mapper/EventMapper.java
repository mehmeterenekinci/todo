package com.example.todo.mapper;

import com.example.todo.dto.EventDto;
import com.example.todo.entities.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDto entityToDto(Event event){
        return EventDto.builder().date(event.getDate()).title(event.getTitle())
                .message(event.getMessage()).isComplete(event.getIsComplete()).build();
    }

    public Event dtoToEntity(EventDto dto){
        return Event.builder().date(dto.getDate()).title(dto.getTitle()).message(dto.getMessage())
                .isComplete(dto.getIsComplete()).build();
    }
}
