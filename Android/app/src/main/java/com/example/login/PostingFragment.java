package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class PostingFragment extends Fragment {
    private Button btn_cancel;
    private Button btn_apply;
    private EditText et_title;
    private EditText et_posting;
    Fragment fragment1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posting, container, false);

        btn_cancel=(Button)view.findViewById(R.id.btn_cancel);
        btn_apply=(Button)view.findViewById(R.id.btn_apply);
        et_title=(EditText)view.findViewById(R.id.et_title);
        et_posting=(EditText)view.findViewById(R.id.et_posting);
        fragment1=new Fragment1();
        
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : 취소 버튼 눌렀을 떄 동작 기능
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
            }
        });
        
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : 작성 버튼 눌렀을 때 동작 기능
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}