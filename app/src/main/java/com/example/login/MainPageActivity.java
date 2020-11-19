package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

// TODO : 메인페이지 레이아웃부터 - ViewPager + TabLayout
public class MainPageActivity extends AppCompatActivity {
    private Toolbar tb_mainPage;
    private ViewPager2 vp_mainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        vp_mainPage=(ViewPager2)findViewById(R.id.vp_mainPage);

        tb_mainPage = (Toolbar)findViewById(R.id.tb_mainPage);
        setSupportActionBar(tb_mainPage);

        getSupportActionBar().setTitle("Main Page");
        //getSupportActionBar().setDisplayShowTitleEnabled(false); //기본 제목을 없애줌
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dialog_close_dark); //뒤로가기버튼 모양

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_my_page,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //다른 메뉴를 만들경우 여기에 id별 기능 추가(switch - case 사용)
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            case R.id.item_my_page:
                Intent intent_myPage = new Intent(this, MyPageActivity.class);

                Toast.makeText(getApplicationContext(), "마이페이지 버튼 누름", Toast.LENGTH_SHORT).show();
                startActivity(intent_myPage);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}