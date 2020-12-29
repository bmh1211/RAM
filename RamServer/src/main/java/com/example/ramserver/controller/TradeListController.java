package com.example.ramserver.controller;

import com.example.ramserver.Response.TradeResponse;
import com.example.ramserver.model.Trade;
import com.example.ramserver.model.User;
import com.example.ramserver.service.TradeService;
import com.example.ramserver.vo.TradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/trade")
public class TradeListController {

    @Autowired
    TradeService tradeService;

    @GetMapping("/list")
    public TradeResponse GetTradeList(HttpServletRequest request, @RequestParam("type") boolean type)
    {
        HttpSession session = request.getSession();
        User info = (User)session.getAttribute("login");

        TradeResponse response = new TradeResponse();
        TradeVo result = tradeService.list(info.getId(),type);
        if(result==null)
            response.setMsg("거래내역이 없습니다");
        else
            response.setMsg("조회 성공");
        response.setTradeVo(result);

        return response;
    }
}
