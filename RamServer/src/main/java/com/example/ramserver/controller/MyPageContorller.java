package com.example.ramserver.controller;

import com.example.ramserver.Response.MsgResponse;
import com.example.ramserver.Response.UserResponse;
import com.example.ramserver.model.User;
import com.example.ramserver.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myPage")
public class MyPageContorller {

    @Autowired
    MyPageService myPageService;

    @GetMapping("/info")
    public UserResponse MyPage(@RequestParam("id") String id) {
        User result = myPageService.myPage(id);
        UserResponse response = new UserResponse();
        if(result == null) {
            response.setMsg("Info Failed");
        }
        else
        {
            response.setMsg("Info Success");
        }
        response.setUser(result);
        return response;
    }

    @PostMapping("/update")
    public MsgResponse Update(@RequestBody User user)
    {
        int result = myPageService.myPageUpdate(user);
        MsgResponse response = new MsgResponse();
        if(result==0)
            response.setMsg("Update Failed");
        else
            response.setMsg("Update Success");

        return response;
    }
}
