package com.example.login;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.login.service.SocketService;

import org.java_websocket.client.WebSocketClient;

import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class Fragment3 extends Fragment {


    private static final String TAG = "TAG";
    private StompClient mStompClient;
    private WebSocketClient webSocketClient;
    ChatMessageAdapter chatMessageAdapter;
    View view;
    Button messageSendBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_3, container, false);
        // Inflate the layout for this fragment
        //버튼 초기화, 클릭 이벤트 추가
        messageSendBtn=(Button)view.findViewById(R.id.btn_send);
        messageSendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SendMessage(view);
            }
        });
        Intent intent=new Intent(
                getActivity().getApplicationContext(), SocketService.class);
        getActivity().startService(intent);

        //Stomp 연결
        //StompClientConnect();
        //connectWebSocket();
        //GetListView();

        return view;
    }

    public void GetListView(){

        chatMessageAdapter=new ChatMessageAdapter(getActivity().getApplicationContext(),R.layout.chatting_message);

        ListView listView=(ListView)view.findViewById(R.id.listView12);
        listView.setAdapter(chatMessageAdapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        //메시지가 추가되었을때 마지막 메시지를 스크롤할수 있는 리스트뷰를 만든다
        chatMessageAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged(){
                super.onChanged();
                listView.setSelection(chatMessageAdapter.getCount()-1);
            }
        });
    }

    private Map<String, String> subscriptions;


   /*public void StompClientConnect(){
       mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://127.0.0.1:3000/websockethandler/");
       //mStompClient.connect();
       Disposable lifecycle=mStompClient.lifecycle().subscribe(lifecycleEvent -> {
           switch(lifecycleEvent.getType()){
               case OPENED:
                   Log.i(TAG,"Connection Openede");
                   break;
               case ERROR:
                   Log.i(TAG,"Error",lifecycleEvent.getException());
                   break;
               case CLOSED:
                   Log.i(TAG,"Stomp Connection Closed");
                   break;
           }
       });
       if(!mStompClient.isConnected()){
           mStompClient.connect();
       }
       StompClientRegister();
   }*/

   public void StompClientRegister(){
       mStompClient.topic("/topic/roomid").subscribe(topicMessage->{
           Log.d(TAG,topicMessage.getPayload());
       });
   }
   public void SendMessage(View view){

       EditText etMsg=(EditText)getView().findViewById(R.id.etMessage);
       String strMsg=(String)etMsg.getText().toString();
       chatMessageAdapter.add(new ChatMessage(strMsg));
       mStompClient.send("/app/hello", strMsg);
   }
   public void StompClientDisconnect(){
       mStompClient.disconnect();
   }

   /*private void connectWebSocket(){
        URI uri = null;
        try{
            uri=new URI("주소");
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
        webSocketClient=new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i("Websocket","Opened");
                webSocketClient.send("Hello from "+ Build.MANUFACTURER+" "+Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message=s;
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        chatMessageAdapter.add(new ChatMessage(message));
                        *//*TextView textView=(TextView)view.findViewById(R.id.etMessage);
                        textView.setText(textView.getText()+"\n"+message);*//*
                    }
                });
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i("Websocket", "Closed " + reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.i("Websocket", "Error " + ex.getMessage());
            }
        };
   }*/
}