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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login.service.SocketService;

public class ChattingRoomFragment extends Fragment {
    private Messenger mServiceMessenger=null;
    private static final String TAG = "TAG";
    ChatMessageAdapter chatMessageAdapter;
    View view;
    Button messageSendBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_chattingroom, container, false);
        // Inflate the layout for this fragment
        //버튼 초기화, 클릭 이벤트 추가
        messageSendBtn=(Button)view.findViewById(R.id.btn_send);
        messageSendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
       /* Intent intent=new Intent(
                getActivity().getApplicationContext(), SocketService.class);
        getActivity().startService(intent);*/

        //Stomp 연결
        //StompClientConnect();
        //connectWebSocket();
        GetListView();

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
}
