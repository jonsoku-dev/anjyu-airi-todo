package com.example.jonsokutodo.entities;

import com.example.jonsokutodo.dtos.TodoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    Boolean completed;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

    public TodoEntity() {
    }

    public TodoEntity(String title) {
        this.title = title;
        this.completed = false;
    }

    public TodoDto toDto() {
        TodoDto dto = new TodoDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setCompleted(this.completed);
        return dto;
    }
}