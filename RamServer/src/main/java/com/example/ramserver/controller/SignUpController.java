package com.example.ramserver.controller;


import com.example.ramserver.Response.MsgResponse;
import com.example.ramserver.service.JoinService;
import com.example.ramserver.vo.JoinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    JoinService joinService;

    @PostMapping("/submit")
    public MsgResponse postMethod(@RequestBody JoinVo joinVo)
    {
        int result = joinService.join(joinVo); //여기가 error 지점
        MsgResponse response = new MsgResponse();
        if(result == 0)
            response.setMsg("Join Failed");
        else
            response.setMsg("Join Success");



        return response;
    }

    //ID 중복확인
    @GetMapping("/checkId")
    public MsgResponse CheckId(@RequestParam("id") String id)
    {
        int result = joinService.checkId(id);
        MsgResponse response = new MsgResponse();
        if(result == 0)
            response.setMsg("New Id");
        else
            response.setMsg("Existing Id");
        return response;
    }
}
