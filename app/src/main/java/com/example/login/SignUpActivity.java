package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    public Boolean check1 = false;
    public Boolean check2 = false;
    public Boolean check3 = false;
    public Boolean check4 = true;
    public Boolean check5 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        Button btn_confirm = (Button) findViewById(R.id.btn_confirm_signup);
        EditText et_name = (EditText) findViewById(R.id.et_name_signup);
        EditText et_pw = (EditText) findViewById(R.id.et_pw_signup);
        EditText et_pwcheck = (EditText) findViewById(R.id.et_pwcheck_signup);
        EditText et_email = (EditText) findViewById(R.id.et_email_signup);
        EditText et_nickname = (EditText) findViewById(R.id.et_nickname_signup);
        ImageView iv_err1 = (ImageView) findViewById(R.id.iv_err1);
        ImageView iv_err2 = (ImageView) findViewById(R.id.iv_err2);
        ImageView iv_err3 = (ImageView) findViewById(R.id.iv_err3);
        ImageView iv_err4 = (ImageView) findViewById(R.id.iv_err4);
        ImageView iv_err5 = (ImageView) findViewById(R.id.iv_err5);
        TextView tv_1 = (TextView) findViewById(R.id.tv_1);
        TextView tv_2 = (TextView) findViewById(R.id.tv_2);
        TextView tv_3 = (TextView) findViewById(R.id.tv_3);
        TextView tv_4 = (TextView) findViewById(R.id.tv_4);
        TextView tv_5 = (TextView) findViewById(R.id.tv_5);
        iv_err1.setVisibility(View.INVISIBLE);
        iv_err2.setVisibility(View.INVISIBLE);
        iv_err3.setVisibility(View.INVISIBLE);
        iv_err4.setVisibility(View.INVISIBLE);
        iv_err5.setVisibility(View.INVISIBLE);


        btn_back.setOnClickListener(new View.OnClickListener() //뒤로가기 버튼
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
        });

        et_name.addTextChangedListener(new TextWatcher() { //이름 체크
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_name.getText().toString().length() > 5 || et_name.getText().toString().length() == 0)
                {
                    tv_1.setText("4자 이내로 작성해주세요");
                    iv_err1.setVisibility(View.VISIBLE);
                    iv_err1.setImageResource(R.drawable.error);
                    check1 = false;
                }
                else
                {
                    tv_1.setText("");
                    iv_err1.setVisibility(View.VISIBLE);
                    iv_err1.setImageResource(R.drawable.confirm);
                    check1 = true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { //비밀번호 체크
                if(et_pw.getText().toString().length() < 8 || et_pw.getText().toString().length() > 15)
                {
                    tv_2.setText("비밀번호는 8자에서 15자 사이로 작성해주세요 ");
                    iv_err2.setVisibility(View.VISIBLE);
                    iv_err2.setImageResource(R.drawable.error);
                    check2 = false;
                }
                else
                {
                    tv_2.setText("");
                    iv_err2.setVisibility(View.VISIBLE);
                    iv_err2.setImageResource(R.drawable.confirm);
                    check2 = true;
                }

                if(et_pw.getText().toString().equals(et_pwcheck.getText().toString()))
                {
                    tv_3.setText("비밀번호가 일치합니다.");
                    iv_err3.setVisibility(View.VISIBLE);
                    iv_err3.setImageResource(R.drawable.confirm);
                    check3 = true;
                }
                else
                {
                    tv_3.setText("비밀번호가 일치하지 않습니다.");
                    iv_err3.setVisibility(View.VISIBLE);
                    iv_err3.setImageResource(R.drawable.error);
                    check3 = false;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_pwcheck.addTextChangedListener(new TextWatcher() { //비밀번호 일치 체크
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_pw.getText().toString().equals(et_pwcheck.getText().toString()))
                {
                    tv_3.setText("비밀번호가 일치합니다.");
                    iv_err3.setVisibility(View.VISIBLE);
                    iv_err3.setImageResource(R.drawable.confirm);
                    check3 = true;
                }
                else
                {
                    tv_3.setText("비밀번호가 일치하지 않습니다.");
                    iv_err3.setVisibility(View.VISIBLE);
                    iv_err3.setImageResource(R.drawable.error);
                    check3 = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_nickname.addTextChangedListener(new TextWatcher() { //닉네임체크
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_nickname.getText().toString().length() > 10)
                {
                    tv_5.setText("사용할 수 있는 닉네임입니다.");
                    iv_err5.setVisibility(View.VISIBLE);
                    iv_err5.setImageResource(R.drawable.confirm);
                    check5 = true;
                }
                else
                {
                    tv_5.setText("닉네임을 10자 이내로 작성해주세요.");
                    iv_err5.setVisibility(View.VISIBLE);
                    iv_err5.setImageResource(R.drawable.error);
                    check5 = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() //회원가입 완료 버튼
        {
            @Override
            public void onClick(View v)
            {
                if(check1 == true && check2 == true && check3 == true && check4 == true && check5 == true)
                {
                    Toast.makeText(SignUpActivity.this, "완료되었습니다!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignUpActivity.this, "이메일을 인증해주세요!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "다시 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}