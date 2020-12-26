package com.example.ramserver.Response;

import com.example.ramserver.vo.TradeVo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class TradeResponse {
    String msg;

    TradeVo tradeVo;
}
