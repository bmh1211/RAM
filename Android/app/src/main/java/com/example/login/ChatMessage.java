package com.example.login;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ChatMessage {
    private String sender;
    private String Message;
    public String getMessage(){
        return Message;
    }
    public String getSender(){return sender;}
    public ChatMessage(String msg){
        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat sdfNow=new SimpleDateFormat("HH:mm:ss");
        String formatDate=sdfNow.format(date);
        Message=msg+"("+formatDate+")";
    }

    public ChatMessage(String sender,String msg){
        this.sender=sender;
        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat sdfNow=new SimpleDateFormat("HH:mm:ss");
        String formatDate=sdfNow.format(date);
        Message=msg+"("+formatDate+")";
    }
}
