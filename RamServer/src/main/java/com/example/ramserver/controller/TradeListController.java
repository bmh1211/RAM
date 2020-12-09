package com.example.ramserver.controller;

import com.example.ramserver.model.Trade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tradeList")
public class TradeListController {

    @GetMapping("/test")
    public Trade GetTradeList(
            @RequestBody Trade trade,
            @RequestParam(value = "id") String id)
    {
        // id를 통해서 찾아오고
        trade = new Trade();

        return trade;
    }
}
