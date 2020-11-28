package com.example.ramserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
@Data
@AllArgsConstructor
public class SearchParam {
    private String account;
    private String email;
    private int page;


}
