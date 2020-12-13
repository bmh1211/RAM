package com.example.ramserver.controller;

import com.example.ramserver.model.LoginResponse;
import com.example.ramserver.model.User;
import com.example.ramserver.service.LoginService;
import com.example.ramserver.vo.LoginVo;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/signin")
public class SignInController {

    @Autowired
    LoginService loginService;
    LoginResponse loginResponse;

    @GetMapping("/login")
    public String login(
            @RequestParam("id") String id,
            @RequestParam("pwd") String pwd
    )
    {
        String res = CheckIdPwd(id,pwd);
        if(res=="1")
        {
            return "존재하지 않는 아이디 입니다.";
        }
        else if(res=="2")
        {
            return "아이디와 비밀번호가 일치하지 않습니다.";
        }
        else
        {
            //this.postMethod(); 구조가 어렵다.
            //여기서 user 정보를 보내줘야하는데 아니면 로그인 성공해서 main으로 넘어갈 때 여기서 요청해서 가져오는것도 방법일듯
            return "로그인 성공";
        }
    }

    //id와 pwd로 DB에서 조회해서 결과값 return
    public String CheckIdPwd(String Id, String Pwd)
    {
        return "3";
    }


    @PostMapping("/submit")
    public String postMethod(@RequestBody LoginVo loginVo, HttpServletRequest request){
        HttpSession session=request.getSession();
        //System.out.println(user.getId());
        //User dto=userMapper.findAll();
        //로그인 제출시 body에 담아서 정보 생성
        //System.out.println(searchParam);
        //User userList=
       // return dto.getId();
        User result=loginService.findAll(loginVo);

        if(result==null){
            session.invalidate();//세션 삭제
            //loginResponse.setMsg("Login Failed");
            return "Login Failed";
        }
        else {
            session.setAttribute("login",result);
            //loginResponse.setMsg("Login Success");
            return "Login Success";
        }
        //return loginService.findAll(loginVo);
        //return searchParam;
    }
}
