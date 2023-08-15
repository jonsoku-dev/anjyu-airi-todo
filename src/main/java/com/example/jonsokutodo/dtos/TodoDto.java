package com.example.jonsokutodo.dtos;

import lombok.Data;

@Data
public class TodoDto {
    String id;
    String title;
    Boolean completed;
}
