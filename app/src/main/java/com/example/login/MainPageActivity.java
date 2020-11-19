package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBar
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static com.example.login.R.id.item3;

public class MainPageActivity extends AppCompatActivity {
    private Button btn_myPage;
    private DrawerLayout mDrawerLayout;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        fragment1 = new Fragment1(); //fragment 생성
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

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
                    Toast.makeText(getApplicationContext(), title + "First Item", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.item2){
                    Toast.makeText(getApplicationContext(), title + "Second Item", Toast.LENGTH_SHORT).show();
                }
                else if(id == item3){
                    Toast.makeText(getApplicationContext(), title + "Third Item", Toast.LENGTH_SHORT).show();
                }

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
                        }
                        return false;
                    }
                }
        );

        btn_myPage=findViewById(R.id.btn_my_page);

        Intent intent_myPage = new Intent(this, MyPageActivity.class);

        btn_myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_myPage);
            }
        });
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
}