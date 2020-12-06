package com.example.login;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ChatMessage {
    private String Message;
    public String getMessage(){
        return Message;
    }
    public ChatMessage(String msg){

        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat sdfNow=new SimpleDateFormat("HH:mm:ss");
        String formatDate=sdfNow.format(date);
        Message=msg+"("+formatDate+")";
    }
}
