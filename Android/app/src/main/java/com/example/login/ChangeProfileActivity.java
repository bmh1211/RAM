package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

// TODO : 회원정보 수정 - 비밀번호 변경, 생체정보 변경 등 작업 필요
public class ChangeProfileActivity extends AppCompatActivity {
    private Toolbar tb_changeProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        tb_changeProfile=(Toolbar)findViewById(R.id.tb_changeProfile);
        setSupportActionBar(tb_changeProfile);

        getSupportActionBar().setTitle("Change Profile");
        //getSupportActionBar().setDisplayShowTitleEnabled(false); //기본 제목을 없애줌
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dialog_close_dark); //뒤로가기버튼 모양
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //다른 메뉴를 만들경우 여기에 id별 기능 추가(switch - case 사용)
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}