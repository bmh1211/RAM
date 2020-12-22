package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostingFragment extends Fragment {
    private Button btn_cancel;
    private Button btn_apply;
    private EditText et_title;
    private EditText et_posting;
    private ImageButton ib_add_photo;

    Fragment fragment1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posting, container, false);

        btn_cancel=(Button)view.findViewById(R.id.btn_cancel);
        btn_apply=(Button)view.findViewById(R.id.btn_apply);
        et_title=(EditText)view.findViewById(R.id.et_title);
        et_posting=(EditText)view.findViewById(R.id.et_posting);
        ib_add_photo = (ImageButton)view.findViewById(R.id.ib_add_photo);
        fragment1=new BoardFragment();

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
                String send_posting = et_posting.getText().toString();

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
                        saveText(et_title.getText().toString(), et_posting.getText().toString(), 1,1);        //핸드폰 sharedpreference에 저장
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                }
            }
        });

        ib_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : 이미지 클릭했을 때 사진촬영/앨범에서선택 고를 수 있도록
            }
        });

        // Inflate the layout for this fragment
        return view;

    }
    private void saveText(String title, String name, int hour, int minute){
        String temp = PostingData.getArray(getActivity());
        JSONObject jsonObject = new JSONObject();
        if(temp != "empty")
        {
            try{
                JSONArray jsonTemp = new JSONArray(temp);
                try{
                    jsonObject.put("name",name);
                    jsonObject.put("title",title);
                    jsonObject.put("hour",hour);
                    jsonObject.put("minute",minute);
                    jsonTemp.put(jsonObject);
                    Log.d("test 게시글 작성",jsonTemp.toString());
                    PostingData.setArray(getActivity(),jsonTemp.toString());
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            JSONArray jsonTemp = new JSONArray();
            try{
                jsonObject.put("name",name);
                jsonObject.put("title",title);
                jsonObject.put("hour",hour);
                jsonObject.put("minute",minute);
                jsonTemp.put(jsonObject);
                PostingData.setArray(getActivity(),jsonTemp.toString());
            }catch(JSONException e)
            {
                e.printStackTrace();
            }}
    }
}