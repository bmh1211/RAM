package com.example.ramserver.Model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainBoardPost {
    private  int BoardID;
    private String ID;
    private String Title;
    private String Region;
    private Bool Status;
    private String content;
    private String imgpath;
    private int price;
}
