package com.jackson.task_list_app.models;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class MyTask {
    
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
