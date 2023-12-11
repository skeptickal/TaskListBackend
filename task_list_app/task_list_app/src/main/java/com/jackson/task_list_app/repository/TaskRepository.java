package com.jackson.task_list_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackson.task_list_app.models.MyTask; // Update this import statement

public interface TaskRepository extends JpaRepository<MyTask, Long> {
    
}
