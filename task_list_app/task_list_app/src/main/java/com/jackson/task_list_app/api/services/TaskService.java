package com.jackson.task_list_app.api.services;

import java.util.List;

import com.jackson.task_list_app.api.models.MyTask;
import com.jackson.task_list_app.api.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<MyTask> getTask() {
        return taskRepository.findAll(); 
    }

    public MyTask createMyTask(MyTask newMyTask) {
        return taskRepository.save(newMyTask);
    }
}
