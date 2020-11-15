package com.example.ramserver.Controller;

import com.example.ramserver.Model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signin")
public class SignInController {
    @PostMapping(value = "/submit")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        //로그인 제출시 body에 담아서 정보 생성
        return searchParam;
    }
}
