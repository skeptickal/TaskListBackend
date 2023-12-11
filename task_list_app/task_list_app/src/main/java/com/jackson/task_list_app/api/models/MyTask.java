package com.jackson.task_list_app.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Table;



@Entity
@Table(name = "my tasks")
public class MyTask {
    
    @Column(name = "task name")
    @Id
    private Long id;

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;  
}

@Override
public String toString() {
    return "id='" + getId() + "'";
}

}
