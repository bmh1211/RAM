package com.example.ramserver.mapper;

import com.example.ramserver.Response.EnterChatResponse;
import com.example.ramserver.vo.ChatRoomVo;
import com.example.ramserver.vo.FindMessageVo;
import com.example.ramserver.vo.ImageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {
    List<String> FindChatter(String myId);
    List<ImageVo> MakeResponse(String myId);
    List<EnterChatResponse> GetMessage(FindMessageVo findMessageVo);
}
