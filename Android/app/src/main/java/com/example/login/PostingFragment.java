package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

        // 취소 버튼 눌렀을 때 동작 기능
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
            }
        });

        //작성 버튼 눌렀을 때 동작 기능
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String send_title = et_title.getText().toString();
                String send_posting=et_posting.getText().toString();

                if(send_posting.equals("") && send_title.equals("")){
                    Toast.makeText((MainPageActivity)getActivity(), "제목과 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(send_title.equals("")){
                    Toast.makeText((MainPageActivity)getActivity(), "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(send_posting.equals("")){
                    Toast.makeText((MainPageActivity)getActivity(), "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    JSONObject jsonObject = new JSONObject();

                    try {
                        Toast.makeText((MainPageActivity)getActivity(), "작성되었습니다", Toast.LENGTH_SHORT).show();
                        
                        jsonObject.put("title",et_title.getText() );
                        jsonObject.put("body", et_posting.getText());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}