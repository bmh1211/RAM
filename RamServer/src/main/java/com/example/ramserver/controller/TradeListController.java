package com.example.ramserver.controller;

import com.example.ramserver.Response.TradeResponse;
import com.example.ramserver.model.User;
import com.example.ramserver.service.TradeService;
import com.example.ramserver.vo.TradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/trade")
public class TradeListController {

    @Autowired
    TradeService tradeService;


    @GetMapping("/list")
    public TradeResponse GetTradeList(HttpServletRequest request, @RequestParam("type") boolean type, @RequestParam("tradeTime") String tradeTime) throws ParseException {
        HttpSession session = request.getSession();
        User info = (User)session.getAttribute("login");

        TradeResponse response = new TradeResponse();
        TradeVo tradeVo = new TradeVo();

        if(type==true)
        {
            tradeVo.setBuyerId(info.getId());
            tradeVo.setTradeTime(StringToDate(tradeTime));
        }
        else
        {
            tradeVo.setSellerId(info.getId());
            tradeVo.setTradeTime(StringToDate(tradeTime));
        }

        TradeVo result = tradeService.list(tradeVo, type);

        if(result==null)
            response.setMsg("failed");
        else
            response.setMsg("success");
        response.setTradeVo(result);

        return response;
    }


    private Date StringToDate(String Date) throws ParseException {
        return (new SimpleDateFormat("yyyy-MM-dd")).parse(Date);
    }
}
