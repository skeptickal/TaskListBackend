package com.jackson.task_list_app.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.task_list_app.api.models.MyTask;
import com.jackson.task_list_app.api.services.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService mockTaskService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testGetTasks() throws Exception {
       List<MyTask> myTasks = new ArrayList<>();
       myTasks.add(new MyTask(1L, "do the dishes")); 

       RequestBuilder request = MockMvcRequestBuilders.get("/tasks").accept(MediaType.APPLICATION_JSON);

       when(mockTaskService.getTask()).thenReturn(myTasks);

       MvcResult response = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

       verify(mockTaskService, times(1)).getTask();
       assertEquals(new ObjectMapper().writeValueAsString(myTasks), response.getResponse().getContentAsString());
    }

    @Test
    void testCreateMyTask() throws Exception {
       List<MyTask> myTasks = new ArrayList<>();
       myTasks.add(new MyTask(1L, "do the dishes"));
       
       String requestJson = new ObjectMapper().writeValueAsString(myTasks.get(0)); 
       RequestBuilder request = MockMvcRequestBuilders
       .post("/tasks")
       .contentType(MediaType.APPLICATION_JSON)
       .content(requestJson)
       .accept(MediaType.APPLICATION_JSON);

       when(mockTaskService.createMyTask(any(MyTask.class))).thenReturn(myTasks.get(0));

       MvcResult response = mockMvc.perform(request).andExpect(status().isCreated()).andReturn();

       verify(mockTaskService, times(1)).createMyTask(any(MyTask.class));
       assertEquals(requestJson, response.getResponse().getContentAsString());
    }

    @Test
    void testUpdateMyTask() throws Exception {
       List<MyTask> myTasks = new ArrayList<>();
       myTasks.add(new MyTask(1L, "do the dishes"));

       String requestJson = new ObjectMapper().writeValueAsString(myTasks.get(0)); 

       RequestBuilder request = MockMvcRequestBuilders
       .put("/tasks/{id}", myTasks.get(0).getId())
       .contentType(MediaType.APPLICATION_JSON)
       .content(requestJson)
       .accept(MediaType.APPLICATION_JSON);

       when(mockTaskService.updateMyTask(any(MyTask.class))).thenReturn(myTasks.get(0));
       when(mockTaskService.getTaskById(anyLong())).thenReturn(myTasks.get(0));

       MvcResult response = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

       verify(mockTaskService, times(1)).updateMyTask(any(MyTask.class));
       assertEquals(requestJson, response.getResponse().getContentAsString());
    }
}
