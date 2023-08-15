package com.example.jonsokutodo.repositories;

import com.example.jonsokutodo.entities.TodoEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {
    List<TodoEntity> todos = new ArrayList<>();

    public List<TodoEntity> findAll() {
        return this.todos;
    }

    public TodoEntity findById(String id) {
        TodoEntity targetTodo = null;

        for (TodoEntity todo : this.todos) {
            if (todo.getId().equals(id)) {
                targetTodo = todo;
            }
        }
        Optional<TodoEntity> opt = Optional.ofNullable(targetTodo);

        if (opt.isEmpty()) {
            throw new RuntimeException("Todo not found");
        }

        return opt.get();
    }

    public TodoEntity save(TodoEntity todo) {
        this.todos.add(todo);
        return todo;
    }

    public TodoEntity update(String id) {
        TodoEntity targetTodo = null;

        for (TodoEntity todo : this.todos) {
            if (todo.getId().equals(id)) {
                targetTodo = todo;
            }
        }

        Optional<TodoEntity> opt = Optional.ofNullable(targetTodo);

        if (opt.isEmpty()) {
            throw new RuntimeException("Todo not found");
        }

        opt.get().setCompleted(true);

        return opt.get();
    }

    public TodoEntity deleteById(String id) {
        TodoEntity targetTodo = null;

        for (TodoEntity todo : this.todos) {
            if (todo.getId().equals(id)) {
                targetTodo = todo;
            }
        }

        Optional<TodoEntity> opt = Optional.ofNullable(targetTodo);

        if (opt.isEmpty()) {
            throw new RuntimeException("Todo not found");
        }

        this.todos.remove(opt.get());
        return opt.get();
    }
}
