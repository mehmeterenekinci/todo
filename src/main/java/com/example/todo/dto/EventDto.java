package com.example.todo.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class EventDto {
    @Size(min = 12 , max = 12 , message = "Format should be 'yyyyMMddHHmm'!")
    private String date;
    @NotNull
    private String title;
    private String message;
    @NotNull
    private String userPhone;
    @NotNull
    private String isComplete;
}
