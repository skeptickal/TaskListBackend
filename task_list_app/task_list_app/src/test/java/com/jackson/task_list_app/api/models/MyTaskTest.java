package com.jackson.task_list_app.api.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class MyTaskTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testEntityProperties() {
        // Create a MyTask instance
        MyTask task = new MyTask();
        task.setId(1L);
        task.setName("TestTask");

        // Save the task to the in-memory database
        entityManager.persist(task);
        entityManager.flush();

        // Retrieve the task from the database
        MyTask retrievedTask = entityManager.find(MyTask.class, task.getId());

        // Test the entity properties
        assertEquals(1L, retrievedTask.getId());
        assertEquals("TestTask", retrievedTask.getName());

        // Test the toString method
        assertEquals("id='1'", retrievedTask.toString());
    }

    @Test
    public void testDefaultConstructor() {
        // Create a MyTask instance using the default constructor
        MyTask task = new MyTask();

        // Ensure that the properties are null for a newly created instance
        assertNull(task.getId());
        assertNull(task.getName());
    }

    @Test
    public void testParameterizedConstructor() {
        // Create a MyTask instance using the parameterized constructor
        MyTask task = new MyTask(1L, "TestTask");

        // Test the entity properties
        assertEquals(1L, task.getId());
        assertEquals("TestTask", task.getName());
    }
}
