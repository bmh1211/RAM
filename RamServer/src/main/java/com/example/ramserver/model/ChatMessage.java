package com.example.ramserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessage {
    private String author;
    private String msg;
    private String Date;
}
