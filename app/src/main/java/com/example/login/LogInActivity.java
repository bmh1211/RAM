package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    private EditText et_id;
    private EditText et_password;
    private Button btn_login;
    private CheckBox chk_login;
    private Button btn_findId;
    private Button btn_findPassword;
    private Button btn_register;
    String ID_temp = "bmh1211@gmail.com"; // 임시지정한 ID
    String PW_temp = "1234567890"; // 임시지정한 PW
    private DBOpenHelper DB_Helper;
    //reference : https://github.com/yoondowon/InnerDatabaseSQLite/blob/master/app/src/main/java/com/example/user/innerdatabasesqlite/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        et_id = (EditText)findViewById(R.id.et_id);
        et_password=(EditText)findViewById(R.id.et_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        chk_login=(CheckBox)findViewById(R.id.chk_login);
        btn_findId=(Button)findViewById(R.id.btn_findId);
        btn_findPassword=(Button)findViewById(R.id.btn_findPassword);
        btn_register=(Button)findViewById(R.id.btn_register);

        Intent intent_mainPage = new Intent(this, MainPageActivity.class);
        Intent intent_findId = new Intent(this,FindIdActivity.class);
        Intent intent_findPassword=new Intent(this,FindPasswordActivity.class);
        Intent intent_register = new Intent(this,RegisterActivity.class);

        DB_Helper = new DBOpenHelper(this);
        DB_Helper.open();
        DB_Helper.create();

        // 로그인 버튼을 눌렀을 때
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = et_id.getText().toString(); // 입력된 id를 가져옴
                String PW = et_password.getText().toString(); // 입력된 비밀번호를 가져옴
                
                if(ID_temp.equals(ID) && PW_temp.equals(PW)){
                    Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();

                    // 안드로이드 내부 DB에 데이터 집어넣기(테스트용)
                    DB_Helper.open();
                    DB_Helper.insertColumn("방민호","MINO",PW,"010-5014-3278",ID);
                    // 해당 기능은 회원가입 액티비티가 구현되면 그쪽으로 옮겨줄예정 - 로그인 액티비티에서는 테스트용도

                    // 메인 페이지로 이동하는 코드 필요
                    startActivity(intent_mainPage);
                }
                else if(ID_temp.equals(ID) && !PW_temp.equals(PW)){
                    Toast.makeText(getApplicationContext(),"비밀번호 오류",Toast.LENGTH_SHORT).show();
                }
                else if(!ID_temp.equals(ID) && PW_temp.equals(PW)){
                    Toast.makeText(getApplicationContext(),"존재하지 않는 ID",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(getApplicationContext(),ID_temp+", "+ID+", "+PW_temp+", "+PW,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_findId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"아이디 찾기",Toast.LENGTH_SHORT).show();
                startActivity(intent_findId);
            }
            // 나중에 기능 넣어줘야함
        });
        
        btn_findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"비밀번호 찾기",Toast.LENGTH_SHORT).show();
                startActivity(intent_findPassword);
            }
            // 나중에 기능 넣어줘야함
        });
        
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"회원가입",Toast.LENGTH_SHORT).show();
                startActivity(intent_register);
            }
            // 나중에 기능 넣어줘야함
        });

        chk_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean login_isChecked = chk_login.isChecked(); // 체크상태를 받아옴

                System.out.println(login_isChecked);

                if(login_isChecked==true){
                    Toast.makeText(LogInActivity.this, "자동로그인 활성화", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LogInActivity.this, "자동로그인 해제", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}