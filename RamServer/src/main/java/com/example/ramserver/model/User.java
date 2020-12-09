package com.example.ramserver.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String password;
    private String phoneNumber;
    private String nickName;
    private String bank;
    private String account;
    private int point;
    private String region;
}
