package com.jackson.task_list_app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jackson.task_list_app.api.models.MyTask;
import com.jackson.task_list_app.api.services.TaskService;



@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<MyTask> getTask() {
        return taskService.getTask();
    }

    @PostMapping("/tasks")
    public ResponseEntity<MyTask> createMyTask(@RequestBody MyTask newMyTask) {
        MyTask createdMyTask = taskService.createMyTask(newMyTask);
        return new ResponseEntity<>(createdMyTask, HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{id}") // Adjust the path variable according to your requirements
    public ResponseEntity<MyTask> updateMyTask(@PathVariable Long id, @RequestBody MyTask updatedMyTask) {
        MyTask existingTask = taskService.getTaskById(id);

        if (existingTask != null) {
            updatedMyTask.setId(id);
            MyTask updatedTask = taskService.updateMyTask(updatedMyTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
