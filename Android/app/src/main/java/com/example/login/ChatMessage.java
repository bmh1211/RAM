package com.example.login;

import java.util.Date;
import java.text.ParseException;
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
        java.sql.Date date=new java.sql.Date(now);
        SimpleDateFormat sdfNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate=sdfNow.format(date);
        Message=msg+"("+formatDate+")";
    }

    public ChatMessage(String sender,String msg){
        this.sender=sender;
        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat sdfNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate=sdfNow.format(date);
        Message=msg+"("+formatDate+")";
    }


    public ChatMessage(String sender,String msg,String Date) throws ParseException {
        this.sender=sender;
        /*SimpleDateFormat sdfNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formatDate=sdfNow.parse(Date);*/
        Message=msg+"("+Date+")";
    }
}
