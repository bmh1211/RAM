package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MyPageActivity extends AppCompatActivity {
    private Toolbar tb_myPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        tb_myPage = (Toolbar)findViewById(R.id.tb_myPage);
        setSupportActionBar(tb_myPage);

        getSupportActionBar().setTitle("My Page");
        //getSupportActionBar().setDisplayShowTitleEnabled(false); //기본 제목을 없애줌
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dialog_close_dark); //뒤로가기버튼 모양
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //다른 메뉴를 만들경우 여기에 id별 기능 추가(switch - case 사용)
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
                
            case R.id.item_change_profile:
                Toast.makeText(getApplicationContext(), "정보수정 버튼 누름", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
// reference : https://www.hanumoka.net/2017/10/28/android-20171028-android-toolbar/