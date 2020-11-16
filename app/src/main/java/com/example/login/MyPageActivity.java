package com.example.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class MyPageActivity extends AppCompatActivity {
    private Toolbar tb_myPage;
    private ActionBar ab_myPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        tb_myPage = (Toolbar)findViewById(R.id.tb_myPage);
        //여기부터!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        setSupportActionBar(tb_myPage);
        ab_myPage=getSupportActionBar();
//        ab_myPage.setDisplayShowCustomEnabled(true);
//        ab_myPage.setDisplayShowTitleEnabled(false); //기본 제목을 없애줌
//        ab_myPage.setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
    }
}
// reference : https://game-happy-world.tistory.com/11