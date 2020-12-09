package com.example.ramserver.service;

import com.example.ramserver.mapper.UserMapper;
import com.example.ramserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    public User findAll(){
        return userMapper.findAll();
    }
}
