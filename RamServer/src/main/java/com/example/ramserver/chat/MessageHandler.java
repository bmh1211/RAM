package com.example.ramserver.chat;

import com.example.ramserver.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageHandler  {
    //hello라는 api로 매핑 ex) client에서 ../hello라는 api로
    //메시지를 보내면 "broadcasting()메소드가 호출됨
    //클라이언트로부터 오는 메시지는 이 메소드의 파라미터와 바인딩
    //리턴값은 Sendto 어노테이션에 mapping되어있는 api를 구독하고 있는 클라이언트들에게 브로드캐스팅
    @MessageMapping("/hello")
    @SendTo("/topic/roomid")
    public ChatMessage broadcasting(ChatMessage message){

        System.out.println(message);
        return message;
    }
    /*public Message broadcasting(ClientMessage message){
        System.out.println(message.getContent());
        return new Message(message.getContent());
    }*/


    /*@Override
    public void AfterConnectionEstablished(WebSocketSession session)
        throws Exception{
        sessionList.add(session);
        Debug.println("{} 연결됨",session.getId());
    }*/
}
