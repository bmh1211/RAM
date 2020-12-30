package com.example.ramserver.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BoardCheckVo {

    private String userId;
    private int boardId;
}
