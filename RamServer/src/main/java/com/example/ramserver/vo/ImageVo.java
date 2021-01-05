package com.example.ramserver.vo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageVo {
    private String id;
    private byte[] img;
}
