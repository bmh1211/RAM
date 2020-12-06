package com.example.ramserver.Controller;

import com.example.ramserver.Model.MainBoardPost;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MainBoard")
public class MainBoardController {

    @GetMapping("/selected")
    public MainBoardPost ShowPost(
            @RequestParam("boardId") int boardId,
            @RequestBody MainBoardPost mainBoardPost)
    {
        //DB에서 boardId로 검색해서 가져오기
        mainBoardPost = new MainBoardPost();


        return mainBoardPost;
    }

    @PostMapping("/all")
    public List<MainBoardPost> ShowMainBoardList(
            @RequestBody List<MainBoardPost> mainBoardPostList)
    {
        mainBoardPostList = new List<MainBoardPost>;
         //db에서 전체 조회후 가져오는 코드 필요할듯

        return mainBoardPostList;
    }


}
