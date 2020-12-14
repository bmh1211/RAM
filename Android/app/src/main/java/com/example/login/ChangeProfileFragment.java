package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeProfileFragment extends Fragment {
    private EditText et_present_password;
    private EditText et_change_password;
    private EditText et_check_password;
    private EditText et_change_nickname;
    private TextView tv_present_password;
    private TextView tv_change_password;
    private TextView tv_check_password;
    private TextView tv_change_nickname;
    private ImageView iv_present_password;
    private ImageView iv_change_password;
    private ImageView iv_check_password;
    private ImageView iv_change_nickname;
    private Button btn_change_cancel;
    private Button btn_change_apply;
    Fragment fragment_my_page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_profile, container, false);

        this.InitializeView(view);
        this.SetButtonClickListener();
        this.SetTextChangeListener();

        return view;
    }

    public void InitializeView(View view){
        et_present_password=(EditText)view.findViewById(R.id.et_present_password);
        et_change_password=(EditText)view.findViewById(R.id.et_change_password);
        et_check_password=(EditText)view.findViewById(R.id.et_check_password);
        et_change_nickname=(EditText)view.findViewById(R.id.et_change_nickname);
        tv_present_password=(TextView)view.findViewById(R.id.tv_present_password);
        tv_change_password=(TextView)view.findViewById(R.id.tv_change_password);
        tv_check_password=(TextView)view.findViewById(R.id.tv_check_password);
        tv_change_nickname=(TextView)view.findViewById(R.id.tv_change_nickname);
        iv_present_password=(ImageView)view.findViewById(R.id.iv_present_password);
        iv_change_password=(ImageView)view.findViewById(R.id.iv_change_password);
        iv_check_password=(ImageView)view.findViewById(R.id.iv_check_password);
        iv_change_nickname=(ImageView)view.findViewById(R.id.iv_change_nickname);
        btn_change_apply = (Button)view.findViewById(R.id.btn_change_apply);
        btn_change_cancel=(Button)view.findViewById(R.id.btn_change_cancel);
        fragment_my_page = new MyPageFragment();
    }

    // Button 이벤트 리스너 함수
    public void SetButtonClickListener(){
        btn_change_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_my_page).commit();
            }
        });

        btn_change_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_my_page).commit();
            }
        });


    }

    // EditText 이벤트 리스너 함수
    public void SetTextChangeListener(){
        // TODO : 원래 비밀번호와 같은지 체크
        et_present_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean PW_IsCorrect=true; // 임시

                if(PW_IsCorrect==true){
                    et_change_password.setEnabled(true);
                    et_check_password.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 변경하고자 하는 비밀번호 입력
        et_change_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_change_password.getText().toString().length() < 8 || et_change_password.getText().toString().length() > 15)
                {
                    tv_change_password.setText("비밀번호는 8자에서 15자 사이로 작성해주세요 ");
                    iv_change_password.setVisibility(View.VISIBLE);
                    iv_change_password.setImageResource(R.drawable.error);
                }
                else
                {
                    tv_change_password.setText("");
                    iv_change_password.setVisibility(View.VISIBLE);
                    iv_change_password.setImageResource(R.drawable.confirm);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 변경하고자 하는 비밀번호와 일치하는지 체크
        et_check_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_check_password.getText().toString().equals(et_change_password.getText().toString()))
                {
                    tv_check_password.setText("비밀번호가 일치합니다.");
                    iv_check_password.setVisibility(View.VISIBLE);
                    iv_check_password.setImageResource(R.drawable.confirm);
                }
                else
                {
                    tv_check_password.setText("비밀번호가 일치하지 않습니다.");
                    iv_check_password.setVisibility(View.VISIBLE);
                    iv_check_password.setImageResource(R.drawable.error);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 변경하고자 하는 닉네임 입력
        et_change_nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_change_nickname.getText().toString().length() <= 10)
                {
                    tv_change_nickname.setText("사용할 수 있는 닉네임입니다.");
                    iv_change_nickname.setVisibility(View.VISIBLE);
                    iv_change_nickname.setImageResource(R.drawable.confirm);
                }
                else
                {
                    tv_change_nickname.setText("닉네임을 10자 이내로 작성해주세요.");
                    iv_change_nickname.setVisibility(View.VISIBLE);
                    iv_change_nickname.setImageResource(R.drawable.error);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}