package com.example.ramserver.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Data
@Setter
@Getter
public class BoardVo {
    private int boardId;
    private String id;
    private String title;
    private int price;
    private boolean status;
    private String content;
    private String img;
    //private Image img;

}
