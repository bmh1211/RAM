package com.example.ramserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Message {
    private String Name;
    public Message(String name){
        this.Name=name;
    }
}
