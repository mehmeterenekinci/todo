package com.example.todo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Event {
    private String date;
    private String title;
    private String message;
    private String userPhone;
    private String isComplete;
}
