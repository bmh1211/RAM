package com.example.ramserver.controller;

import com.example.ramserver.Response.BoardListResponse;
import com.example.ramserver.Response.BoardResponse;
import com.example.ramserver.Response.MsgResponse;
import com.example.ramserver.model.User;
import com.example.ramserver.service.BoardService;
import com.example.ramserver.vo.BoardCheckVo;
import com.example.ramserver.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public BoardListResponse list(@RequestParam("index") int index)
    {
        BoardListResponse response = new BoardListResponse();
        List<BoardVo> result = boardService.boardList(index);
        if(result==null)
            response.setMsg("failed");
        else
            response.setMsg("success");

        response.setList(result);
        return response;
    }

    @GetMapping("/detail")
    public BoardResponse detail(@RequestParam("boardId") int boardId)
    {
        BoardResponse response = new BoardResponse();
        BoardVo result = boardService.boardDetail(boardId);

        if(result==null)
            response.setMsg("failed");
        else
            response.setMsg("success");

        response.setPost(result);
        return response;
    }

    @PostMapping("/register")
    public MsgResponse register(@RequestBody BoardVo boardVo)
    {
        int maxCount = boardService.max();
        boardVo.setBoardId(maxCount+1);
        int result = boardService.register(boardVo);
        MsgResponse response = new MsgResponse();
        if(result == 0)
            response.setMsg("failed");
        else
            response.setMsg("success");

        return response;
    }

    @PostMapping("/modify")
    public MsgResponse modify(@RequestBody BoardVo boardVo)
    {
        BoardCheckVo boardCheckVo = new BoardCheckVo();
        boardCheckVo.setBoardId(boardVo.getBoardId());
        boardCheckVo.setUserId(boardVo.getId());

        int check = boardService.check(boardCheckVo);
        MsgResponse response = new MsgResponse();
        if(check == 0)
            response.setMsg("failed");
        else
        {
            int modifyResult = boardService.modify(boardVo);
            if(modifyResult == 0)
                response.setMsg("failed");
            else
                response.setMsg("success");
        }

        return response;
    }

    @PostMapping("/delete")
    public MsgResponse delete(@RequestParam("boardId") int boardId, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User info = (User)session.getAttribute("login");
        BoardCheckVo boardCheckVo = new BoardCheckVo();
        boardCheckVo.setUserId(info.getId());
        boardCheckVo.setBoardId(boardId);
        int check = boardService.check(boardCheckVo);
        MsgResponse response = new MsgResponse();
        if(check == 0)
            response.setMsg("failed"); //권한 x
        else
        {
            int deleteResult = boardService.delete(boardId);
            if(deleteResult == 0)
                response.setMsg("failed");
            else
                response.setMsg("success");
        }


        return response;
    }
}
