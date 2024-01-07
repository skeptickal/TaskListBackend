package com.jackson.task_list_app.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jackson.task_list_app.api.models.Task;
import com.jackson.task_list_app.api.models.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);
    
}
