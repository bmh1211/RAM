package com.example.login;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class Fragment3 extends Fragment {

    private WebSocketClient webSocketClient;
    ChatMessageAdapter chatMessageAdapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        connectWebSocket();
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_3, container, false);
        return view;
    }

   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
       getActivity().getMenuInflater().inflate(R.menu.menu,menu);

       final ListView listView=(ListView)view.findViewById(R.id.listView);
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
       //return true;
   }


   public void send(View view){
       EditText etMsg=(EditText)view.findViewById(R.id.etMessage);
       String strMsg=(String)etMsg.getText().toString();
       chatMessageAdapter.add(new ChatMessage(strMsg));
   }

   private void connectWebSocket(){
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
                        /*TextView textView=(TextView)view.findViewById(R.id.etMessage);
                        textView.setText(textView.getText()+"\n"+message);*/
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
   }
}