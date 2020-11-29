package com.example.ramserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class User {
    private String ID;
    private String password;
    private String phonNumber;
    private String nickName;
    private String bank;
    private String account;
    private int point;
    private String region;
}
