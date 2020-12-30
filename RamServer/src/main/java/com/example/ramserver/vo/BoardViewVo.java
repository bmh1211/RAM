package com.example.ramserver.vo;

import lombok.Data;

@Data
public class BoardViewVo {
    private int boardId;
    private String id;
    private String title;
    private int price;
    private boolean status;
    private String content;
    private String img;
    //private img img;
    private String nickName;
    private String region;

}
