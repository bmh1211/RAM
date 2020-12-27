package com.example.ramserver.mapper;

import com.example.ramserver.vo.ChatRoomVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {
    List<String> FindChatter(String myId);
}
