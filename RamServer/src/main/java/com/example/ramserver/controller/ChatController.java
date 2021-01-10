package com.example.ramserver.controller;

import com.example.ramserver.Response.ChatRoomResponse;
import com.example.ramserver.Response.EnterChatResponse;
import com.example.ramserver.Response.MsgResponse;
import com.example.ramserver.model.ChatterInfo;
import com.example.ramserver.model.User;
import com.example.ramserver.service.ChatRoomService;
import com.example.ramserver.service.InsertImgService;
import com.example.ramserver.service.LoginService;
import com.example.ramserver.vo.ChatRoomVo;
import com.example.ramserver.vo.FindMessageVo;
import com.example.ramserver.vo.ImageVo;
import org.apache.catalina.webresources.FileResource;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    InsertImgService insertImgService;

    @GetMapping(value="/AllRoom",produces=MediaType.APPLICATION_JSON_VALUE)
    public MultiValueMap<String,String> getRoomInfo(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session=request.getSession();
        User info=(User)session.getAttribute("login");

        //List<String> result=chatRoomService.FindChatter(info.getId());
        MultiValueMap<String,String> form=new LinkedMultiValueMap<>();
        List<ImageVo> result=chatRoomService.MakeResponse(info.getId());
        List<ChatRoomResponse> responseList=new ArrayList<ChatRoomResponse>();
        for(int i=0;i<result.size();i++){
          /*  ChatRoomResponse tmp=new ChatRoomResponse(result.get(i).getId(),result.get(i).ByteToBase64());
            responseList.add(tmp);*/
           /* File file=new File(result.get(i).getId());
            Path path= Paths.get(file.toURI());
            Files.write(path,result.get(i).getImg());*/
            //form.add("id",result.get(i).getId());
            //form.add("file",result.get(i).getImg());
            System.out.println(result.get(i).getImg());
            form.add("id",result.get(i).getId());
            form.add("data",result.get(i).ByteToBase64());

        }
        httpServletResponse.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
        //return result;
        return form;
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

    @GetMapping(value="/enterChattingRoom")
    //받은 정보 채팅방 입장한 아이디, 상대방 아이디
    //return 할 정보 : 입장한 사람 아이디, 상대방 아이디,현재까지의 채팅 내용,
    public List<EnterChatResponse> EnterChat(HttpServletRequest request,@RequestParam("id") String otherId){
        HttpSession session=request.getSession();
        User info=(User)session.getAttribute("login");
        String enterId=info.getId();
        FindMessageVo findMessageVo=new FindMessageVo(enterId,otherId);
        return chatRoomService.GetMessage(findMessageVo);
    }

}
