package com.example.ramserver.mapper;

import com.example.ramserver.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE ID=#{ID}")
    User findAll(@Param("ID") String ID);
}
