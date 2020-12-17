package com.example.ramserver.Response;

import com.example.ramserver.model.User;
import lombok.Data;

@Data
public class UserResponse {
    private String msg;

    private User user;
}
