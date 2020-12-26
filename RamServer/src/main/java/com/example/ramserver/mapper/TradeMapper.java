package com.example.ramserver.mapper;

import com.example.ramserver.vo.TradeVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeMapper {
    TradeVo buyList(String id);

    TradeVo sellList(String id);
}
