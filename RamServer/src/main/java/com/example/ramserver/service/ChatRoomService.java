package com.example.ramserver.service;

import com.example.ramserver.mapper.ChatRoomMapper;
import com.example.ramserver.vo.ChatRoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {
    @Autowired
    public ChatRoomMapper chatRoomMapper;

    public List<String> FindChatter(String myId){
        return chatRoomMapper.FindChatter(myId);
    }
}
