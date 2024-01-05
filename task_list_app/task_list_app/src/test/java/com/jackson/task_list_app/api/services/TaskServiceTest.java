package com.jackson.task_list_app.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jackson.task_list_app.api.models.Task;
import com.jackson.task_list_app.api.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository mockTaskRepository;

    @Test
    void testUpdateTask() {
        when(mockTaskRepository.findById(anyLong())).thenReturn(Optional.of(new Task(1L, "do the dishes")));
        when(mockTaskRepository.save(any(Task.class))).thenReturn(new Task(1L, "wash the dishes"));

        Task actual = taskService.updateTask(new Task(1L, "wash the dishes"));
        verify(mockTaskRepository).findById(1L);

        assertEquals("wash the dishes", actual.getName());
    }

    @Test
    void testUpdateTaskWhenAbsent() {
        when(mockTaskRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        Task actual = taskService.updateTask(new Task(1L, "wash the dishes"));

        assertEquals(null, actual);
    }

     @Test
    void testUpdateTaskThrows() {

        when(mockTaskRepository.findById(anyLong())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> taskService.updateTask(new Task(3L, "throw an exception")));
    }

    @Test
    void testGetTask() {
        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(new Task(1L, "do the dishes"));
        mockTasks.add(new Task(2L, "wash the dishes"));

        when(mockTaskRepository.findAll()).thenReturn(mockTasks);

        List<Task> actual = taskService.getTask();
        verify(mockTaskRepository).findAll();

        assertEquals(mockTasks, actual);
    }

    @Test
    void testCreateTask() {

        when(mockTaskRepository.save(any(Task.class))).thenReturn(new Task(2L, "wash the dishes"));
        Task actual = taskService.createTask(new Task(2L, "wash the dishes"));

        assertEquals("wash the dishes", actual.getName());
    }


}