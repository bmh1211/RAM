package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

// TODO : 기능에 대한 작업 필요
public class MyPageActivity extends AppCompatActivity {
    private Toolbar tb_myPage;
    private ListView lv_recentSell;
    private ListView lv_recentBuy;
    private ListView lv_favorite;
    static final String[] LIST_MENU={"LIST_1","LIST_2","LIST_3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        tb_myPage = (Toolbar)findViewById(R.id.tb_myPage);
        lv_recentSell=(ListView)findViewById(R.id.lv_recentSell);
        lv_recentBuy=(ListView)findViewById(R.id.lv_recentBuy);
        lv_favorite=(ListView)findViewById(R.id.lv_favorite);

        setSupportActionBar(tb_myPage);
        getSupportActionBar().setTitle("My Page");
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dialog_close_dark); //뒤로가기버튼 모양

        // 리스트에 들어갈 내용은 나중에 변경시켜줄 예정
        ArrayAdapter sell_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,LIST_MENU);
        ArrayAdapter buy_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,LIST_MENU);
        ArrayAdapter favorite_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,LIST_MENU);

        lv_recentSell.setAdapter(sell_adapter);
        lv_recentBuy.setAdapter(buy_adapter);
        lv_favorite.setAdapter(favorite_adapter);

        // TODO : 리스트에 있는 아이템 클릭했을 때의 기능 추가필요
        lv_recentSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(MyPageActivity.this, strText, Toast.LENGTH_SHORT).show();
            }
        });

        lv_recentBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //TODO : 퀵메뉴?? 를 사용해서 판매자 정보 말풍선으로 띄워보기 **
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(MyPageActivity.this, strText, Toast.LENGTH_SHORT).show();
            }
        });

        lv_favorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(MyPageActivity.this, strText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_change_profile,menu);

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
                Intent intent_changeProfile = new Intent(this, ChangeProfileActivity.class);

                Toast.makeText(getApplicationContext(), "정보수정 버튼 누름", Toast.LENGTH_SHORT).show();
                startActivity(intent_changeProfile);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
// 툴바 관련 reference : https://www.hanumoka.net/2017/10/28/android-20171028-android-toolbar/
// 리스트뷰 관련 reference : https://recipes4dev.tistory.com/42