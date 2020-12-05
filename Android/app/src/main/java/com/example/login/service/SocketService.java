package com.example.login.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.login.ChatMessageAdapter;

import org.java_websocket.client.WebSocketClient;

import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class SocketService extends Service {
    private StompClient mStompClient;
    private WebSocketClient webSocketClient;
    ChatMessageAdapter chatMessageAdapter;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //SErvice 객체와 (Activity사이에서)
        //데이터를 주고받을때 사용하는 메서드
        //데이터를 전달할 필요가 없으면 return null;
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        StompClientConnect();
        Log.d("test","서비스 onCreate");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("test","서비스 onDestroy");
    }
    public void StompClientConnect(){
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://127.0.0.1:3000/websockethandler/websocket");
        mStompClient.connect();
        //StompClientRegister();
    }
}
