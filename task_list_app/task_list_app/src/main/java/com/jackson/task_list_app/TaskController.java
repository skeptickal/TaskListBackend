package com.jackson.task_list_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @GetMapping("/tasks")
    public String getTask() {
        return "do the laundry";
    }
}
