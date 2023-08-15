package com.example.jonsokutodo.dtos;

import lombok.Data;

@Data
public class PostTodoRequestBody {
    String title;

    public PostTodoRequestBody() {
    }

    public PostTodoRequestBody(String title) {
        this.title = title;
    }
}
