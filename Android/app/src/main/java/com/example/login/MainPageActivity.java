package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBar
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainPageActivity extends AppCompatActivity {
    //    private Button btn_myPage;
    private DrawerLayout mDrawerLayout;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment_my_page;
    Toolbar toolbar;
    Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // todo 첫화면이 홈화면이 뜨게 수정함
        fragment1 = new HomeFragment(); //fragment 생성
        fragment2 = new BoardFragment();
        fragment3 = new ChattingRoomFragment();
        fragment_my_page=new MyPageFragment();
        //listView = (ListView) getSupportFragmentManager().findFragmentById(R.id.lv_board);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.item1){
                    Toast.makeText(getApplicationContext(), title + "홈 화면", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.item2){
                    Toast.makeText(getApplicationContext(), title + "게시판 목록", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.item3){
                    Toast.makeText(getApplicationContext(), title + "채팅 목록", Toast.LENGTH_SHORT).show();
                }
                // todo 네번째 fragment 어딨는지 모르겠어서 일단 냅둠
//                else if(id == R.id.item4){
//                    Toast.makeText(getApplicationContext(), title + "Third Item", Toast.LENGTH_SHORT).show();
//                }

                return true;
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
        //초기화면 설정

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch(item.getItemId())
                        {
                            case R.id.test1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                                return true;
                            case R.id.test2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
                                return true;
                            case R.id.test3:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();
                                return true;
                            case R.id.test4:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_my_page).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void fragDataSend(Bundle bundle) {
        this.mBundle = bundle;
    }
}