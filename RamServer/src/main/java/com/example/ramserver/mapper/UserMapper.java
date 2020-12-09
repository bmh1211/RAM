package com.example.ramserver.mapper;

import com.example.ramserver.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findAll();
}
