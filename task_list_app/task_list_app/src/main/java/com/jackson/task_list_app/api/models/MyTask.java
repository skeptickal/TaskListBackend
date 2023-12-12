package com.jackson.task_list_app.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Table;



@Entity
@Table(name = "my tasks")
public class MyTask {
    
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

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

@Override
public String toString() {
    return "id='" + getId() + "'";
}

}
