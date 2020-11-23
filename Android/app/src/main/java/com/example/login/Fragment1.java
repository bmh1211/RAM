package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class Fragment1 extends Fragment {
    private ListView lv_board;
    static final String[] LIST_MENU={"LIST_1","LIST_2","LIST_3","LIST_4","LIST_5","LIST_6","LIST_7","LIST_8","LIST_9"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        lv_board = (ListView)view.findViewById(R.id.lv_board);

        ArrayAdapter board_adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,LIST_MENU);

        lv_board.setAdapter(board_adapter);

        lv_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(getActivity().getApplicationContext(), strText+"번째 아이템, id : "+id, Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}