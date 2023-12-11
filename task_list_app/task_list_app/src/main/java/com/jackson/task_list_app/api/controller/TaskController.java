package com.jackson.task_list_app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jackson.task_list_app.api.models.MyTask;
import com.jackson.task_list_app.api.services.TaskService;

//import com.jackson.task_list_app.api.models.MyTask;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<MyTask> getTask() {
        return taskService.getTask();
    }
}
