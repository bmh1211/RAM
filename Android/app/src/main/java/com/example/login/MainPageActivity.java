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
    private Adapter PostingAdapter;
    private int hour, minute;
    private String name, title, am_pm;
    private Handler handler;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        fragment1 = new BoardFragment(); //fragment 생성
        fragment2 = new HomeFragment();
        fragment3 = new ChattingFragment();
        fragment_my_page=new MyPageFragment();
        PostingAdapter = new Adapter();
        //listView = (ListView) getSupportFragmentManager().findFragmentById(R.id.lv_board);
        //listView.setAdapter(PostingAdapter);

        Intent intent = getIntent(); //intent를 받아서 fragment로 넘김

        LoadPosting(MainPageActivity.this);

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
                else if(id == R.id.item3){
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
                            case R.id.test4:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_my_page).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );

//        btn_myPage=findViewById(R.id.btn_my_page);

//        Intent intent_myPage = new Intent(this, MyPageActivity.class);

//        btn_myPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(intent_myPage);
//            }
//        });
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
    protected void LoadPosting(Context context)   //fragment 불릴때 게시글 목록 생성
    {
        String temp = PostingData.getArray(MainPageActivity.this);
        if(temp != "empty")
        {
            try{
                JSONArray response = new JSONArray(temp);
                for(int i = 0;i<response.length() ;i++) {
                    JSONObject jsonObject = response.getJSONObject(i);
                    Log.d("test 받아오기 제목",jsonObject.getString("title"));
                    Log.d("test 받아오기 제목",jsonObject.getString("name"));
                    Log.d("test 받아오기 제목",jsonObject.getString("hour"));
                    Log.d("test 받아오기 제목",jsonObject.getString("minute"));
                    Log.d("test 받아온 목록",temp);
                    name = jsonObject.getString("name");
                    title = jsonObject.getString("title");
                    hour = jsonObject.getInt("hour");
                    minute = jsonObject.getInt("minute");
                    am_pm = "오전 고정";
                    PostingAdapter.addItem(hour, minute, am_pm, name,title);
                    PostingAdapter.notifyDataSetChanged();
                }
            }catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(MainPageActivity.this, "게시물이 없음", Toast.LENGTH_SHORT).show();

        }
    }
}