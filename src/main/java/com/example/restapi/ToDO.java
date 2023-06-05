package com.example.restapi;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private boolean isDone;

    private Integer userId;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public String getDescription(){
        return this.description;
    }

    public void setIsDone(boolean done){
        this.isDone = done;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
