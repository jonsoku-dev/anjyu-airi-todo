package com.example.jonsokutodo.repositories;

import com.example.jonsokutodo.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoJpaRepository extends JpaRepository<TodoEntity, String> {
}
