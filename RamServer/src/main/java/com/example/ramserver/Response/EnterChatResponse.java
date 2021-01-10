package com.example.ramserver.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EnterChatResponse {
    private String enterId;
    private String otherId;
    private String message;
    private Date date;

}
