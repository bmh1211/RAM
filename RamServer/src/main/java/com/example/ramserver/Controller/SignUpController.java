package com.example.ramserver.Controller;


import com.example.ramserver.Model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @PostMapping(value = "/submit")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        //회원가입 제출시 body에 담아서 정보 생성
        return searchParam;
    }

    @GetMapping("/CheckID")
    public String CheckID(){
        return "false";
    }
}
