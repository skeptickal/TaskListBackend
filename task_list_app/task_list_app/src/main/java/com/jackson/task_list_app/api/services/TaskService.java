package com.jackson.task_list_app.api.services;

import java.util.List;
import java.util.Optional;

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

    public MyTask updateMyTask(MyTask updatedTask) {
        // Assuming that the 'id' property of 'updatedTask' is not null
        if (updatedTask.getId() != null) {
            // Check if the task with the given ID exists
            Optional<MyTask> optionalExistingTask = taskRepository.findById(updatedTask.getId());
            
            if (optionalExistingTask.isPresent()) {
                // Update the existing task with the properties of the updated task
                MyTask existingTask = optionalExistingTask.get();
                existingTask.setName(updatedTask.getName());

                // Save the updated task
                return taskRepository.save(existingTask);
            }
        }
        return null; 
    }

    public MyTask getTaskById(Long id) {
        Optional<MyTask> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }
}
