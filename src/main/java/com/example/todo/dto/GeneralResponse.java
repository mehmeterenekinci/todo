package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GeneralResponse {

    /**
     * HTTP Return Code
     */
    private Integer code;

    /**
     * Return Message
     */
    private String message;

    /**
     * Request is succeeded or not
     */
    private Boolean success;

    /**
     * Return object
     */
    private Object data;

}
