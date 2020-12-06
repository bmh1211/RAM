package com.example.ramserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
public class ClientMessage {

    private String name;

    public ClientMessage() {
    }

    public ClientMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getContent(){return name;}
    public void setName(String name) {
        this.name = name;
    }
}
