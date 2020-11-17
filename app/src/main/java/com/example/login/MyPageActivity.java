package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

public class MyPageActivity extends AppCompatActivity {
    private Toolbar tb_myPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        tb_myPage = (Toolbar)findViewById(R.id.tb_myPage);
        //여기부터!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        setSupportActionBar(tb_myPage);
        getSupportActionBar().setTitle("My Page");
        //getSupportActionBar().setDisplayShowTitleEnabled(false); //기본 제목을 없애줌
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground); //뒤로가기버튼 모양


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
// reference : https://game-happy-world.tistory.com/11
// reference : https://kingpiggylab.tistory.com/129
// refernece : https://itpangpang.xyz/326