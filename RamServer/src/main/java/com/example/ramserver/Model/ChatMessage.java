package com.example.ramserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
public class ChatMessage {
    private String author;
    private String msg;
    private String Date;
}
