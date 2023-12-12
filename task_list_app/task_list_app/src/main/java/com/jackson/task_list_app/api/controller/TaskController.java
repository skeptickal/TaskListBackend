package com.jackson.task_list_app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jackson.task_list_app.api.models.MyTask;
import com.jackson.task_list_app.api.services.TaskService;



@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<MyTask> getTask() {
        System.out.println("hi hello");
        return taskService.getTask();
    }
}
