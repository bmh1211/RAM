package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class LogInActivity extends AppCompatActivity {
    private EditText et_id, et_password;
    private Button btn_login, btn_findId, btn_findPassword, btn_register;
    private CheckBox chk_login;
    String ID_temp = "1"; // 귀찮아서 바꿈
    String PW_temp = "1"; // 귀찮아서 바꿈
    //String ID_temp = "bmh1211@gmail.com"; // 임시지정한 ID
    //String PW_temp = "1234567890"; // 임시지정한 PW
    private DBOpenHelper DB_Helper;
    private Toolbar tb_logIn;
    private SharedPreferences.Editor sp_editor_login;
    private SharedPreferences sp_login;
    private HttpURLConnection con;
    private String loginResult;
    private HTTPConnetction connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // ======================= 사용할 객체들 =====================//
        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        chk_login = (CheckBox) findViewById(R.id.chk_login);
        btn_findId = (Button) findViewById(R.id.btn_findId);
        btn_findPassword = (Button) findViewById(R.id.btn_findPassword);
        btn_register = (Button) findViewById(R.id.btn_register);
        connection=new HTTPConnetction();

        // ======================= 다른 액티비티로 이동하기 위한 intent들 =================//
        Intent intent_mainPage = new Intent(this, MainPageActivity.class);
        Intent intent_findId = new Intent(this, FindIdActivity.class);
        Intent intent_findPassword = new Intent(this, FindPasswordActivity.class);
        Intent intent_register = new Intent(this, SignUpActivity.class);

//        // =================== 내부 SQLite 사용을 위한 DB생성 ==================//
//        // reference : https://github.com/yoondowon/InnerDatabaseSQLite/blob/master/app/src/main/java/com/example/user/innerdatabasesqlite/
//        DB_Helper = new DBOpenHelper(this);
//        DB_Helper.open();
//        DB_Helper.create();

        // ================== 자동로그인을 위한 SharedPreference와 Editor ==============//
        sp_login = getSharedPreferences("setting", 0);
        sp_editor_login = sp_login.edit();

        // ====================== 로그인 버튼을 눌렀을 때 ========================//
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = et_id.getText().toString(); // 입력된 id를 가져옴
                String PW = et_password.getText().toString(); // 입력된 비밀번호를 가져옴

                //서버와 연결 내용( 현재는 배포가 안되었으니 주석처리 )
                //실험해 보고 싶으면 서버 실행하고 하단 LoginReqeust 함수 에서 url 아이피부분만 자기 컴퓨터 ip로 바꿔서 돌리면
                //loginresult 변수에 Fail인지 Success인지 확인하면됨!!
                //파라미터 세팅
                /*String json="";
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("id",ID);
                    jsonObject.put("password",PW);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject result=connection.HttpPost("http://192.168.56.1:3000/signin/submit",jsonObject);
                    System.out.println("결과값 " +result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                new Thread(){
                    public void run(){
                        loginResult=LoginRequest(ID,PW);
                    }
                }.start();

                if(loginResult.equals("Login Success")){
                    startActivity(intent_mainPage);
                }
                else{

                }
                /*if (ID_temp.equals(ID) && PW_temp.equals(PW)) {
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

//                    // 안드로이드 내부 DB에 데이터 집어넣기(테스트용)
//                    DB_Helper.open();
//                    DB_Helper.insertColumn("방민호", "MINO", PW, "010-5014-3278", ID);
//                    // 해당 기능은 회원가입 액티비티가 구현되면 그쪽으로 옮겨줄예정 - 로그인 액티비티에서는 테스트용도

                    startActivity(intent_mainPage);
                } else if (ID_temp.equals(ID) && !PW_temp.equals(PW)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 오류", Toast.LENGTH_SHORT).show();

                    if (chk_login.isChecked() == true) {
                        sp_editor_login.clear();
                        sp_editor_login.commit();
                        chk_login.setChecked(false);
                    }
                } else if (!ID_temp.equals(ID) && PW_temp.equals(PW)) {
                    Toast.makeText(getApplicationContext(), "존재하지 않는 ID", Toast.LENGTH_SHORT).show();

                    if (chk_login.isChecked() == true) {
                        sp_editor_login.clear();
                        sp_editor_login.commit();
                        chk_login.setChecked(false);
                    }
                } else {
                    //Toast.makeText(getApplicationContext(),ID_temp+", "+ID+", "+PW_temp+", "+PW,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();

                    if (chk_login.isChecked() == true) {
                        sp_editor_login.clear();
                        sp_editor_login.commit();
                        chk_login.setChecked(false);
                    }
                }*/
            }
        });

        // ===================== 아이디찾기, 비밀번호찾기, 회원가입 버튼 ===========================//
        btn_findId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "아이디 찾기", Toast.LENGTH_SHORT).show();
                startActivity(intent_findId);
            }
        });

        btn_findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "비밀번호 찾기", Toast.LENGTH_SHORT).show();
                startActivity(intent_findPassword);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원가입", Toast.LENGTH_SHORT).show();
                startActivity(intent_register);
            }
        });

        // ========================= 자동로그인 체크박스 ===========================//
        // reference : https://es1015.tistory.com/18
        chk_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String ID = et_id.getText().toString(); // 입력된 id를 가져옴
                String PW = et_password.getText().toString(); // 입력된 비밀번호를 가져옴

                if (!ID.equals("") && !PW.equals("")) {
                    if (isChecked == true) {
                        Toast.makeText(LogInActivity.this, "자동로그인 활성화", Toast.LENGTH_SHORT).show();
                        sp_editor_login.putString("ID", ID);
                        sp_editor_login.putString("PW", PW);
                        sp_editor_login.putBoolean("chk_login", true);
                        sp_editor_login.commit();
                    } else {
                        Toast.makeText(LogInActivity.this, "자동로그인 해제", Toast.LENGTH_SHORT).show();
                        sp_editor_login.clear();
                        sp_editor_login.commit();
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "ID와 비밀번호를 입력해주십시오", Toast.LENGTH_SHORT).show();
                    chk_login.setChecked(false);
                }
            }
        });

        if (sp_login.getBoolean("chk_login", false) == true) {
            et_id.setText(sp_login.getString("ID", ""));
            et_password.setText((sp_login.getString("PW", "")));
            chk_login.setChecked(true);
        }

    }
    //로그인 요청
    private String LoginRequest(String id,String password){
        try{
            URL url=new URL("http://192.168.56.1:3000/signin/submit");
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");
            con.setRequestProperty("Accept","application/json");
            con.setDefaultUseCaches(false);
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            setCookieHeader();


            //파라미터 세팅
            String json="";
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",id);
            jsonObject.put("password",password);
            OutputStream os=con.getOutputStream();
            os.write(jsonObject.toString().getBytes("UTF-8"));
           /* os.flush();
            os.close();*/

            int num=con.getResponseCode();
            /*if(con.getResponseCode()!=HttpURLConnection.HTTP_OK){
                Log.d("Log","Connection Error");
                return null;
            }*/

            BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line;
            String loginResult="";
            while((line=reader.readLine())!=null){
                loginResult+=line;
            }
            getCookieHeader();
            return loginResult;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //쿠키 설정
    private void setCookieHeader(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("sessionCookie",Context.MODE_PRIVATE);
        String sessionid = pref.getString("sessionid",null);
        if(sessionid!=null) {
            Log.d("LOG","세션 아이디"+sessionid+"가 요청 헤더에 포함 되었습니다.");
            con.setRequestProperty("Cookie", sessionid);
        }
    }
    private void getCookieHeader(){
        List<String> cookies=con.getHeaderFields().get("Set-Cookie");
        if(cookies!=null){
            for(String cookie: cookies){
                String sessionID=cookie.split(";\\s*")[0];
                //JSESSOINID=~~~
                //세션 아이디가 포함된 쿠키를 얻음
                setSessionIdInSharedPref(sessionID);
            }
        }
    }

    private void setSessionIdInSharedPref(String sessionID) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(pref.getString("sessionid",null) == null){ //처음 로그인하여 세션아이디를 받은 경우
            Log.d("LOG","처음 로그인하여 세션 아이디를 pref에 넣었습니다."+sessionID);
        }else if(!pref.getString("sessionid",null).equals(sessionID)){ //서버의 세션 아이디 만료 후 갱신된 아이디가 수신된경우
            Log.d("LOG","기존의 세션 아이디"+pref.getString("sessionid",null)+"가 만료 되어서 "
                    +"서버의 세션 아이디 "+sessionID+" 로 교체 되었습니다.");
        }
        edit.putString("sessionid",sessionID);
        edit.apply(); //비동기 처리
    }

}