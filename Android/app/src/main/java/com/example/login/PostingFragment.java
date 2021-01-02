package com.example.login;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.login.network.NetworkTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class PostingFragment extends Fragment {
    private Button btn_cancel;
    private Button btn_apply;
    private EditText et_title;
    private EditText et_posting;
    private ImageButton ib_add_photo;
    private String title, name, date, contents;
    Fragment fragment1;
    MainPageActivity activity;
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        //메시지 송수신에 필요한 객체 초기화
        activity = (MainPageActivity) getActivity();
        title ="";
        name = "";
        date = "";
        contents = "";
    }

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH시mm분ss초");
        Calendar time = Calendar.getInstance();

//        if(activity.mBundle != null) {
//            Bundle bundle = activity.mBundle;
//            receiveData = bundle.getString("sendData");
//            StudentDTO student3 = (StudentDTO) bundle.getSerializable("student3");
//            String name = student3.getName();
//            int age = student3.getAge();
//            int index = bundle.getInt("index");
//
//            textView1.append(receiveData + "\n");
//            textView1.append("name : " + name + "\nage : " + age + "\nindex : " + index);
//            activity.mBundle = null;
//        }



        //<---------------------------------------------------작성-------------------------------------------------->
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
                        String date_posting = format.format(time.getTime());
                        sendPosting(send_title, send_posting, date_posting);
//                        saveText(et_title.getText().toString(), et_posting.getText().toString(), date_posting);        //핸드폰 sharedpreference에 저장
                        // todo 서버로 넘겨주는 기능 추가하기. (현재는 로컬만)
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        ib_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : 이미지 클릭했을 때 사진촬영/앨범에서선택 고를 수 있도록 -> 내가 다음 스프린트 때 추가해봄(이준영)
            }
        });

        // Inflate the layout for this fragment
        return view;

    }
    private void sendPosting(String title, String body, String date) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("body", body);
            jsonObject.put("date", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetworkTask networkTask = new NetworkTask((MainPageActivity)getActivity(), "http://3.35.48.170:3000/singin/submit", jsonObject, "POST");   //올바른 url 넣기
        try {
            JSONObject resultObject = new JSONObject(networkTask.execute().get());
            if (resultObject == null) {
                Log.d("fail", "연결 실패");
                return;
            }
            String resultString = resultObject.getString("msg");
            if (resultString.equals("Posting Success")) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
            } else {
                Toast.makeText((MainPageActivity)getActivity(), "게시글 등록 실패", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveText(String title, String name, String date){
        String temp = PostingData.getArray(getActivity());
        JSONObject jsonObject = new JSONObject();
        if(temp != "empty")
        {
            try{
                JSONArray jsonTemp = new JSONArray(temp);
                try{
                    jsonObject.put("name",name);
                    jsonObject.put("title",title);
                    jsonObject.put("date",date);
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
                jsonObject.put("date",date);
                jsonTemp.put(jsonObject);
                PostingData.setArray(getActivity(),jsonTemp.toString());
            }catch(JSONException e)
            {
                e.printStackTrace();
            }}
    }
}