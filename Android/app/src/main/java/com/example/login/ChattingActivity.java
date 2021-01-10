package com.example.login;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.network.NetworkTask;
import com.example.login.service.SocketService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ChattingActivity extends AppCompatActivity {
    Button messageSendBtn;
    ChatMessageAdapter chatMessageAdapter;
    private static final String TAG = "TAG";
    private boolean mIsBound;
    private Messenger mServiceMessenger=null;
    private Intent thisIntent;
    private String OtherName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisIntent=getIntent();
        OtherName=thisIntent.getStringExtra("id");
        setContentView(R.layout.fragment_chatting);
        messageSendBtn=(Button)findViewById(R.id.btn_send);
        messageSendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SendMessage();
            }
        });

        NetworkTask fileNetworkTask=new NetworkTask(getApplicationContext(),"http://192.168.56.1:3000/chat/enterChattingRoom?id="+OtherName,null,"GET");
        try {
            JSONObject resultFileObject=new JSONObject(fileNetworkTask.execute().get());
            if(resultFileObject==null){
                Log.d("fail","연결 실패");
            }
            else{
                System.out.println(resultFileObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setStartService();
        GetListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void GetListView(){
        chatMessageAdapter=new ChatMessageAdapter(getApplicationContext(),R.layout.chatting_message);
        ListView listView=(ListView)findViewById(R.id.listView12);
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
    //서비스 시작
    private void setStartService(){
        startService(new Intent(getApplicationContext(), SocketService.class));
       bindService(new Intent(getApplicationContext(), SocketService.class),mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    private ServiceConnection mConnection=new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("test","onServiceconnected");
            mServiceMessenger=new Messenger(iBinder);
            try{
                Message msg=Message.obtain(null,SocketService.MSG_REGISTER_CLIENT);
                msg.replyTo=mMessenger;
                mServiceMessenger.send(msg);
            } catch (RemoteException e) {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }

    };

    //service로부터 message 받음
    private final Messenger mMessenger=new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.i("test receive","act :what "+msg.what);
            switch(msg.what){
                case SocketService.MSG_SEND_TO_ACTIVITY:
                    String value2=msg.getData().getString("test");
                    Log.i("test","act : value2 "+value2);
                    chatMessageAdapter.add(new ChatMessage(value2));
                    break;
            }
            return false;
        }
    }));
    //버튼 클릭 이벤트
    public void SendMessage(){
        EditText etMsg=(EditText)findViewById(R.id.etMessage);
        String strMsg=(String)etMsg.getText().toString();

        chatMessageAdapter.add(new ChatMessage(strMsg));
        sendMessageToservice(strMsg);
    }
    //서비스로 메시지 전송
    private void sendMessageToservice(String str){
        if(mIsBound){
            if(mServiceMessenger!=null){
                try{
                    Message msg=Message.obtain(null, SocketService.MSG_SEND_TO_SERVICE,str);
                    msg.replyTo=mMessenger;
                    mServiceMessenger.send(msg);
                } catch (RemoteException e) {

                }
            }
        }
    }
}
