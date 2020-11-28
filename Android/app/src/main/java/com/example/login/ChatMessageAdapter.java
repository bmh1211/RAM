package com.example.login;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter {
    List msgs=new ArrayList();
    public ChatMessageAdapter(Context context,int textViewResourceId){
        super(context,textViewResourceId);
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
        msgText.setText(msg.getMessage());
        msgText.setTextColor(Color.parseColor("#000000"));
        return row;
    }
}
