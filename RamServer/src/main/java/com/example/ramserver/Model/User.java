package com.example.ramserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
//@Data
public class User {
    private String ID;
    @Getter
    private String password;
   /* private String phonNumber;
    private String nickName;
    private String bank;
    private String account;
    private int point;
    private String region;*/
}
