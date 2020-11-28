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

                Bundle bundle = new Bundle(); // 번들은 이동시킬 데이터를 모아 담을 보따리 느낌

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
                    // TODO : 데이터를 객체형태로 보내서 제목, 내용 둘다 보여질수 있게 하기
                    bundle.putString("et_title",et_title.getText().toString());

                    fragment1.setArguments(bundle);//번들안의 데이터를 넣어주는 과정

                    ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}