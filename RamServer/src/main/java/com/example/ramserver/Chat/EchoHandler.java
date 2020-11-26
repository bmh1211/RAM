package com.example.ramserver.Chat;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EchoHandler extends TextWebSocketHandler {
    //Map<String, WebSocketSession> sessions=new HashMap<String,WebSocketSession>();
    private List<WebSocketSession> sessionList=new ArrayList<WebSocketSession>();


    //채팅방에 입장했을시에
    @Override
    public void afterConnectionEstablished(WebSocketSession session)throws Exception{
        sessionList.add(session);
        System.out.println("채팅방 입장 :"+ session.getPrincipal().getName());
    }

    @Override
    protected  void handleTextMessage(WebSocketSession session, TextMessage message)throws Exception{
        for(WebSocketSession sess:sessionList){
            sess.sendMessage(new TextMessage(session.getPrincipal().getName()+"|"+message.getPayload()));
        }
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)throws Exception{
        sessionList.remove(session);
        System.out.println("채팅방 퇴장자 ; "+session.getPrincipal().getName());
    }
}
