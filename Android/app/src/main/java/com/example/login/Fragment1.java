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
import android.widget.Toast;

public class Fragment1 extends Fragment {
    private ListView lv_board;
    static final String[] LIST_MENU={"LIST_1","LIST_2","LIST_3","LIST_4","LIST_5","LIST_6","LIST_7","LIST_8","LIST_9"};
    private Button btn_write;
    Fragment postingFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        lv_board = (ListView)view.findViewById(R.id.lv_board);
        postingFragment = new PostingFragment();
        btn_write=(Button)view.findViewById(R.id.btn_write);

        ArrayAdapter board_adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,LIST_MENU);
        lv_board.setAdapter(board_adapter);

        lv_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                // TODO : Toast 동작이 갑자기 안됨 - 나중에 수정
                Toast.makeText(container.getContext(), strText+"번째 아이템, id : "+id, Toast.LENGTH_SHORT).show();
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:게시물 작성 버튼눌렀을 때 게시물 작성 창 띄우기
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,postingFragment).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}