package com.jackson.task_list_app.api.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jackson.task_list_app.api.models.Task;
import com.jackson.task_list_app.api.models.TaskStatus;
import com.jackson.task_list_app.api.services.TaskService;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GetMappings
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasks(@RequestParam(required = false) String status) {
        if (status != null) {
            try {
                TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
                return taskService.getTasksByStatus(taskStatus);
            } catch (IllegalArgumentException e) {
                // Handle invalid status value
                return Collections.emptyList();
            }
        } else {
            return taskService.getTask();
        }
    }

    // Post Mappings
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
        Task createdTask = taskService.createTask(newTask);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Put Mappings
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask,
            @RequestParam(required = false) TaskStatus newStatus) {
        Task existingTask = taskService.getTaskById(id);

        if (existingTask != null) {
            updatedTask.setId(id);
            Task task = taskService.updateTask(updatedTask);

            if (newStatus != null) {
                existingTask.setStatus(newStatus);
                task = taskService.updateTask(existingTask);
            }

            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Mappings
    @DeleteMapping("/tasks/{id}/delete")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        Task existingTask = taskService.getTaskById(id);

        if (existingTask != null) {
            existingTask.setStatus(TaskStatus.DELETED);
            Task updatedTask = taskService.updateTask(existingTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
