package com.jackson.task_list_app.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks_table")
public class Task {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TASKNAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TaskStatus status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id='" + getId() + "'";
    }

    public Task() {
        // not implemented
    }

    public Task(Long id, String name, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

}
