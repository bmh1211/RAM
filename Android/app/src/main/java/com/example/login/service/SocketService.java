package com.example.login.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.login.ChatMessageAdapter;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class SocketService extends Service {

    public static final int MSG_REGISTER_CLIENT = 1;
    //public static final int MSG_UNREGISTER_CLIENT = 2;
    public static final int MSG_SEND_TO_SERVICE = 3;
    public static final int MSG_SEND_TO_ACTIVITY = 4;
    private Messenger mClient = null;

    private StompClient mStompClient;
    ChatMessageAdapter chatMessageAdapter;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //SErvice 객체와 (Activity사이에서)
        //데이터를 주고받을때 사용하는 메서드
        //데이터를 전달할 필요가 없으면 return null;
        return mMessenger.getBinder();
    }
    private final Messenger mMessenger=new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.w("send test","SocketSerivec - message what : "+msg.what+", msg obj"+msg.obj);
            switch(msg.what){
                case MSG_REGISTER_CLIENT:
                    mClient=msg.replyTo;
                    break;
                case MSG_SEND_TO_SERVICE:
                    mStompClient.send("/app/hello", msg.obj.toString()).subscribe();
            }
            return false;
        }
    }));
    @Override
    public void onCreate(){
        super.onCreate();
        StompClientConnect();
        StompClientRegister();
        Log.d("test","서비스 onCreate");


    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("test","서비스 onDestroy");
    }
    public void StompClientConnect(){
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws:/192.168.56.1:3000/websockethandler/websocket");
        mStompClient.connect();
    }

    public void StompClientRegister(){
        mStompClient.topic("/topic/roomid").subscribe(topicMessage->{
            Log.d("register test",topicMessage.getPayload());
        });
    }

    public void StompClientDisconnect(){
        mStompClient.disconnect();
    }


}
