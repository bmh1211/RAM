package com.example.ramserver.Model;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
@Data
public class ChatMessage {
    private String email;
    private String text;
    private String author;
    private Date createDate;

    public ChatMessage(String email ,String text,String author,Date createDate){
        this.email=email;
        this.text=text;
        this.author=author;
        this.createDate=createDate;
    }

    public String toString(){
        return "{" +

                "\"id\":\"" + email + '\"' +

                ",\"text\":\"" + text + '\"' +

                ",\"author\":\"" + author + '\"' +

                ",\"createDate\":\"" + createDate + "\"" +

                "}";
    }

}
