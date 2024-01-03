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

import com.jackson.task_list_app.api.models.MyTask;
import com.jackson.task_list_app.api.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository mockTaskRepository;

    @Test
    void testUpdateMyTask() {
        when(mockTaskRepository.findById(anyLong())).thenReturn(Optional.of(new MyTask(1L, "do the dishes")));
        when(mockTaskRepository.save(any(MyTask.class))).thenReturn(new MyTask(2L, "wash the dishes"));

        MyTask actual = taskService.updateMyTask(new MyTask(1L, "wash the dishes"));
        verify(mockTaskRepository).findById(1L);

        assertEquals("wash the dishes", actual.getName());
    }

    @Test
    void testUpdateMyTaskWhenAbsent() {
        when(mockTaskRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        MyTask actual = taskService.updateMyTask(new MyTask(1L, "wash the dishes"));

        assertEquals(null, actual);
    }

     @Test
    void testUpdateMyTaskThrows() {

        when(mockTaskRepository.findById(anyLong())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> taskService.updateMyTask(new MyTask(3L, "throw an exception")));
    }

    @Test
    void testGetTask() {
        List<MyTask> mockTasks = new ArrayList<>();
        mockTasks.add(new MyTask(1L, "do the dishes"));
        mockTasks.add(new MyTask(2L, "wash the dishes"));

        when(mockTaskRepository.findAll()).thenReturn(mockTasks);

        List<MyTask> actual = taskService.getTask();
        verify(mockTaskRepository).findAll();

        assertEquals(mockTasks, actual);
    }

    @Test
    void testCreateMyTask() {

        when(mockTaskRepository.save(any(MyTask.class))).thenReturn(new MyTask(2L, "wash the dishes"));
        MyTask actual = taskService.createMyTask(new MyTask(2L, "wash the dishes"));

        assertEquals("wash the dishes", actual.getName());
    }


}