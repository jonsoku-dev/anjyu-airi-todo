package com.example.jonsokutodo.controllers;

import com.example.jonsokutodo.dtos.PostTodoRequestBody;
import com.example.jonsokutodo.dtos.TodoDto;
import com.example.jonsokutodo.entities.TodoEntity;
import com.example.jonsokutodo.repositories.TodoJpaRepository;
import com.example.jonsokutodo.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;
    private final TodoJpaRepository todoJpaRepository;

    // Restful API: 暗黙的な開発者たちの約束
    @GetMapping("/")

    public String index() {
        return "index page";
    }

    // TODO: TODO LISTを取得する (GET /todos)
    // example: curl http://localhost:8080/todos
    @GetMapping("/todos")
    public List<TodoDto> getTodos() {
//        List<TodoEntity> todos = this.todoRepository.findAll();
        List<TodoEntity> todos = this.todoJpaRepository.findAll();

        // Entity -> Dto に変換
        // List<TodoEntity> -> List<TodoDto>
        List<TodoDto> dtos = new ArrayList<>();

        for (TodoEntity todo : todos) {
            dtos.add(todo.toDto());
        }

        return dtos;
    }

    // TODO: 一つのTODOを取得する (GET /todos/{id})
    // example: curl http://localhost:8080/todos/1
    @GetMapping("/todos/{id}")
    public TodoDto getTodo(@PathVariable String id) {
//        TodoEntity todo = this.todoRepository.findById(id);
        Optional<TodoEntity> todo = this.todoJpaRepository.findById(id);
        if (todo.isEmpty()) {
            throw new RuntimeException("Todo not found");
        }
        return todo.get().toDto();
    }

    // TODO: TODOを作成する (POST /todos)
    // example: curl -X POST http://localhost:8080/todos
    @PostMapping("/todos")
    public TodoDto postTodo(
            @RequestBody PostTodoRequestBody body
    ) {
        System.out.println(body.getTitle());
        TodoEntity todo = new TodoEntity(body.getTitle());
//        this.todoRepository.save(todo);
        this.todoJpaRepository.save(todo);
        return todo.toDto();
    }

    // TODO: TODOを更新する (PATCH /todos/{id})
    // example: curl -X PATCH http://localhost:8080/todos/1
    @PatchMapping("/todos/{id}")
    public TodoDto patchTodo(@PathVariable String id) {
//        TodoEntity todo = this.todoRepository.update(id);
        Optional<TodoEntity> todo = this.todoJpaRepository.findById(id);

        if (todo.isEmpty()) {
            throw new RuntimeException("Todo not found");
        }

        todo.get().setCompleted(true);

        return todo.get().toDto();
    }

    // TODO: TODOを削除する (DELETE /todos/{id})
    // example: curl -X DELETE http://localhost:8080/todos/1
    @DeleteMapping("/todos/{id}")
    public TodoDto deleteTodo(@PathVariable String id) {
//        TodoEntity todo = this.todoRepository.deleteById(id);

        Optional<TodoEntity> todo = this.todoJpaRepository.findById(id);

        if (todo.isEmpty()) {
            throw new RuntimeException("Todo not found");
        }

        this.todoJpaRepository.deleteById(todo.get().getId());

        return todo.get().toDto();
    }
}

// /todos, /todos/{id}, /todos/create, /todos/{id}/update, /todos/{id}/delete

// /todos, /todos/{id}