package com.jackson.task_list_app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get Mappings
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) String status) {
        if (status != null) {
            try {
                TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
                return new ResponseEntity<>(taskService.getTasksByStatus(taskStatus), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
        } else {
            return new ResponseEntity<>(taskService.getTask(), HttpStatus.OK);
        }
    }

    // Post Mappings
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task newTask) {
        try {
            return taskService.createTask(newTask);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    // Put Mappings
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask) throws Exception {
        updatedTask.setId(id);
        Task task = taskService.updateTask(updatedTask);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } else {
            throw new IllegalAccessException();
        }
    }

    // Delete Mappings
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) throws Exception {
        Task existingTask = taskService.getTaskById(id);

        if (existingTask != null) {
            existingTask.setStatus(TaskStatus.DELETED);
            Task updatedTask = taskService.updateTask(existingTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.ACCEPTED);
        } else {
            throw new IllegalAccessException();
        }
    }

}
