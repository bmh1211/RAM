package com.example.ramserver.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MainBoard")
public class MainBoardController {
    @GetMapping(value="dd")
    public String ShowPost(){
        return "Fd";
        //게시글 조회
    }

}
