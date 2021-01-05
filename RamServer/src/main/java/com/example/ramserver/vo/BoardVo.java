package com.example.ramserver.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Date;

@Data
@Setter
@Getter
public class BoardVo {
    private int boardId;
    private String id;
    private String title;
    private int price;
    private int status;
    private Date boardTime;
    private String content;
    private String img;
    //private Image img; 이미지 해결되면 수정예정
    private String nickName;
    private String region;
}
