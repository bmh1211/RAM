package com.example.ramserver.service;

import com.example.ramserver.mapper.TradeMapper;
import com.example.ramserver.vo.TradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    public TradeMapper tradeMapper;

    public TradeVo list(TradeVo tradeVo, boolean type)
    {
        if(type==true)
            return tradeMapper.buyList(tradeVo);
        else
            return tradeMapper.sellList(tradeVo);
    }
}
