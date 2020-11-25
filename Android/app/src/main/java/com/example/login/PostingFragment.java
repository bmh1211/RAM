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

public class PostingFragment extends Fragment {
    private Button btn_cancel;
    private Button btn_apply;
    private ListView lv_posting;
    static final String[] LIST_MENU={"1","2","3","4","5","6","7","8","9"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posting, container, false);

        btn_cancel=(Button)view.findViewById(R.id.btn_cancel);
        btn_apply=(Button)view.findViewById(R.id.btn_apply);

        lv_posting=(ListView)view.findViewById(R.id.lv_posting);
        ArrayAdapter board_adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,LIST_MENU);
        lv_posting.setAdapter(board_adapter);

        lv_posting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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