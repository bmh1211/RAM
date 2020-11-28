package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private ListView lv_board;
    ArrayList<String> LIST_MENU;
    private Button btn_write;
    Fragment postingFragment;
    ArrayAdapter board_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        lv_board = (ListView)view.findViewById(R.id.lv_board);
        postingFragment = new PostingFragment();
        btn_write=(Button)view.findViewById(R.id.btn_write);
        LIST_MENU=new ArrayList<>();
        board_adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,LIST_MENU);

        // TODO : 기존에 가지고 있는 게시판 리스트가 있다면 불러오기(DB에서??)
        if(getArguments() != null){
            // 번들을 통해서 온 데이터가 있는 경우
            String test=getArguments().getString("et_title","");
            LIST_MENU.add(test);
            board_adapter.notifyDataSetChanged();
        }

        lv_board.setAdapter(board_adapter);

        lv_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText+"번째 아이템, id : "+id, Toast.LENGTH_SHORT).show();
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            // 게시물 작성 버튼 클릭시
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,postingFragment).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}