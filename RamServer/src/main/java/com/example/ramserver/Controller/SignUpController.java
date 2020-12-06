package com.example.ramserver.Controller;


import com.example.ramserver.Model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    //최종회원 가입 버튼 클릭
    @PostMapping(value = "/submit")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        //회원가입 제출시 body에 담아서 정보 생성
        searchParam = new SearchParam("asdf","qwer", 3);


        return searchParam;
    }

    //ID 중복확인
    @GetMapping("/CheckID")
    public String CheckID(
            @RequestParam("Id") String userId)
    {
        //DB에서 조회해서 결과 확인
        if(userId=="asdf")
        {
            return "true";
        }
        else
            return "false";

    }
}
