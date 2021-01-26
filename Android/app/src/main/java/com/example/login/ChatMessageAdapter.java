package com.example.login;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.login.MessageType.MessageType;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter {
    boolean message_left=true;
    List msgs=new ArrayList();
    private String senderId;
    public ChatMessageAdapter(Context context,int textViewResourceId,String senderId){
        super(context,textViewResourceId);
        this.senderId=senderId;
    }

    //@Override
    public void add(ChatMessage object){
        msgs.add(object);
        super.add(object);
    }

    @Override
    public int getCount(){
        return msgs.size();
    }

    @Override
    public ChatMessage getItem(int index){
        return (ChatMessage)msgs.get(index);
    }
    //리스트의 n번째 아이템에 대한 내용을 화면에 출력하는 역할
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row=convertView;
        if(row==null){
            //inflator 생성하여,chatting_message.xml읽어서 VIew객체로 생성
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.chatting_message,parent,false);
        }
        //Arrary List 에 들어있는 채팅 문자열을 읽어
        ChatMessage msg=(ChatMessage) msgs.get(position);

        //inflater를 이용해서 생성한 View에 Chatmessage 삽입
        TextView msgText=(TextView)row.findViewById(R.id.chatmessage);
        row.findViewById(R.id.btn_accept).setVisibility(View.GONE);
        row.findViewById(R.id.btn_deny).setVisibility(View.GONE);

        msgText.setText(msg.getMessage());
        msgText.setTextColor(Color.parseColor("#000000"));
        if(msg.getSender().equals(senderId)){
            msgText.setBackground(this.getContext().getResources().getDrawable(R.drawable.outbox2));
        }
        else{
            msgText.setBackground(this.getContext().getResources().getDrawable(R.drawable.inbox2));
            if(msg.getMsgType()== MessageType.Quest){
                row.findViewById(R.id.btn_accept).setVisibility(View.VISIBLE);
                row.findViewById(R.id.btn_deny).setVisibility(View.VISIBLE);
            }
        }

        //msgText.setBackground(this.getContext().getResources().getDrawable((message_left?R.drawable.inbox2:R.drawable.outbox2)));

        LinearLayout chatMessageContainer=(LinearLayout)row.findViewById(R.id.chatmessage_container);
        /*int align;
        if(message_left){
            align= Gravity.LEFT;
            message_left=false;
        }
        else{
            align=Gravity.RIGHT;
            message_left=true;
        }*/
        return row;
    }
}
