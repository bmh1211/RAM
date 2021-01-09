package com.example.ramserver.controller;

import com.example.ramserver.Response.MsgResponse;
import com.example.ramserver.vo.ImageVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/SellingRegister")
public class SellingRegisterController {
    @PostMapping(value = "/Register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MsgResponse RegisterSelling(HttpServletRequest request) throws IOException {
       /* MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        MultipartFile file=multipartHttpServletRequest.getFile("file");
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        byte[] data=file.getBytes();
        ImageVo imageVo=new ImageVo("bmh1211@gmail.com",data);
        insertImgService.InsertImage(imageVo);
*/
        MsgResponse response=new MsgResponse();
        response.setMsg("success");
        return response;
    }
}
