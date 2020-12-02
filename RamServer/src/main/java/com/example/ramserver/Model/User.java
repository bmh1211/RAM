package com.example.ramserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String ID;
    private String password;
    private String phoneNumber;
    private String nickName;
    private String bank;
    private String account;
    private int point;
    private String region;
}
