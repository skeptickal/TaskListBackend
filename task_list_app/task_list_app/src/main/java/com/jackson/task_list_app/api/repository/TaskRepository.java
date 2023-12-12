package com.jackson.task_list_app.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jackson.task_list_app.api.models.MyTask;

@Repository
public interface TaskRepository extends JpaRepository<MyTask, Long> {
    
}
