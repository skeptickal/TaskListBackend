package com.jackson.task_list_app.api.services;

import java.util.List;
import java.util.Optional;

import com.jackson.task_list_app.api.models.Task;
import com.jackson.task_list_app.api.models.TaskStatus;
import com.jackson.task_list_app.api.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTask() {
        return taskRepository.findAll(); 
    }

    public Task createTask(Task newTask) {
        return taskRepository.save(newTask);
    }

    public Task updateTask(Task updatedTask) {
        // Assuming that the 'id' property of 'updatedTask' is not null
        if (updatedTask.getId() != null) {
            // Check if the task with the given ID exists
            Optional<Task> optionalExistingTask = taskRepository.findById(updatedTask.getId());
            
            if (optionalExistingTask.isPresent()) {
                // Update the existing task with the properties of the updated task
                Task existingTask = optionalExistingTask.get();
                existingTask.setName(updatedTask.getName());

                if (updatedTask.getStatus() != null) {
                    existingTask.setStatus(updatedTask.getStatus());
                }

                // Save the updated task
                return taskRepository.save(existingTask);
            }
        }
        return null; 
    }

    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}
