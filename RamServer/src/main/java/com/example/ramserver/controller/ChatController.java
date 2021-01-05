package com.example.ramserver.controller;

import com.example.ramserver.Response.MsgResponse;
import com.example.ramserver.model.User;
import com.example.ramserver.service.ChatRoomService;
import com.example.ramserver.service.InsertImgService;
import com.example.ramserver.service.LoginService;
import com.example.ramserver.vo.ChatRoomVo;
import com.example.ramserver.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    InsertImgService insertImgService;

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

    @PostMapping(value = "/profileImage",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MsgResponse getRoomInfoWithImgage(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        MultipartFile file=multipartHttpServletRequest.getFile("file");
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        byte[] data=file.getBytes();
        ImageVo imageVo=new ImageVo("bmh1211@gmail.com",data);
        insertImgService.InsertImage(imageVo);

        MsgResponse response=new MsgResponse();
        response.setMsg("success");
        return response;
    }
}
