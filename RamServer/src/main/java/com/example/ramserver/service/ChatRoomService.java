package com.example.ramserver.service;

import com.example.ramserver.Response.EnterChatResponse;
import com.example.ramserver.mapper.ChatRoomMapper;
import com.example.ramserver.vo.ChatRoomVo;
import com.example.ramserver.vo.FindMessageVo;
import com.example.ramserver.vo.ImageVo;
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

    public List<ImageVo> MakeResponse(String myId){return chatRoomMapper.MakeResponse(myId);}

    public List<EnterChatResponse> GetMessage(FindMessageVo findMessageVo){
        return chatRoomMapper.GetMessage(findMessageVo);
    }

}
