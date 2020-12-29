package com.example.ramserver.controller;

import com.example.ramserver.Response.MsgResponse;
import com.example.ramserver.model.User;
import com.example.ramserver.service.ChatRoomService;
import com.example.ramserver.vo.ChatRoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatRoomService chatRoomService;

    @GetMapping("/AllRoom")
    public MsgResponse getRoomInfo(HttpServletRequest request){
        HttpSession session=request.getSession();
        User info=(User)session.getAttribute("login");

        List<String> result=chatRoomService.FindChatter(info.getId());


        for(int i=0;i<result.size();i++){
            String id= result.get(i);
        }

        System.out.println(info.getId());
        MsgResponse response=new MsgResponse();
        response.setMsg("success");
        return response;
    }
}
