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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login.adapter.ChatRoomAdapter;
import com.example.login.item.RoomItem;
import com.example.login.network.NetworkTask;
import com.example.login.service.SocketService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ChattingRoomFragment extends Fragment {
    private Messenger mServiceMessenger=null;
    private static final String TAG = "TAG";
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NetworkTask networkTask=new NetworkTask(getActivity().getApplicationContext(),"http://192.168.56.1:3000/chat/AllRoom","GET");
        try {
            JSONObject resultObject=new JSONObject(networkTask.execute().get());
            if(resultObject==null){
                Log.d("fail","연결 실패");
            }
            else{
                System.out.println(resultObject);
            }
           /// String resultString=resultObject.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        ChatRoomAdapter chatRoomAdapter=new ChatRoomAdapter(getActivity().getApplicationContext());

        view=inflater.inflate(R.layout.fragment_chattingroom, container, false);



        ListView listView=view.findViewById(R.id.chatRoomView);
        chatRoomAdapter.addItem(new RoomItem("김재연","백엔드 정복완료",R.drawable.applemango));
        chatRoomAdapter.addItem(new RoomItem("방민호","게시판? 다했지",R.drawable.confirm));
        chatRoomAdapter.addItem(new RoomItem("이준영","아 안드로이드 지겹지~",R.drawable.datebg));

        listView.setAdapter(chatRoomAdapter);
        return view;
    }

}
