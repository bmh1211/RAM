package com.example.ramserver.controller;

import com.example.ramserver.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MyPage")
public class MyPageContorller {

    @PostMapping("/info")
    public User postMethod(
            @RequestParam("Id") String userId,
            @RequestBody User user)
    {
        //밑에 있는 User Body에 UserId를 이용해서 DB에서 조회해서 가져오기
        user = new User();
        System.out.println(userId); //test 코드
        return user;
    }
}
