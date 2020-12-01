package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

// TODO : 기능에 대한 작업 필요
public class MyPageActivity extends AppCompatActivity {
    private Toolbar tb_myPage;
    private ListView lv_recentSell;
    private ListView lv_recentBuy;
    private ListView lv_favorite;
    static final String[] LIST_MENU={"LIST_1","LIST_2","LIST_3"};
    //private PopupWindow pw_sellerInfo;
    private PopupWindow ll_sellerInfo;
    private TextView tv_sellerName;
    private TextView tv_price;
    private TextView tv_location;
    private TextView tv_evalPoint;

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

        lv_recentSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(MyPageActivity.this, strText, Toast.LENGTH_SHORT).show();
            }
        });

        lv_recentBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //TODO : 안드로이드 팝업윈도우
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(MyPageActivity.this, strText, Toast.LENGTH_SHORT).show();

                // 팝업창이 들어갈 뷰를 하나 생성해주고, 해당 뷰의 레이아웃을 LinearLayout 으로 지정
                View popupView = getLayoutInflater().inflate(R.layout.popupwindow_seller_info,null);
                //pw_sellerInfo=new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ll_sellerInfo=new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                // 외부영역 선택시 PopUp창 사라짐
                //pw_sellerInfo.setFocusable(true);
                ll_sellerInfo.setFocusable(true);

                // 팝업창의 위치를 디스플레이의 중앙에 위치시킴
                //pw_sellerInfo.showAtLocation(popupView, Gravity.CENTER,0,0);
                ll_sellerInfo.showAtLocation(popupView, Gravity.CENTER,0,0);

                // 팝업창에 들어갈 TextView 객체 선언
                tv_sellerName=(TextView)ll_sellerInfo.getContentView().findViewById(R.id.tv_sellerName);
                tv_price=(TextView)ll_sellerInfo.getContentView().findViewById(R.id.tv_price);
                tv_location=(TextView)ll_sellerInfo.getContentView().findViewById(R.id.tv_location);
                tv_evalPoint=(TextView)ll_sellerInfo.getContentView().findViewById(R.id.tv_evalPoint);

                // 팝업창에 들어갈 TextView 들의 Text 를 지정해줌
                tv_sellerName.setText("거래 상대 : "+ "상대이름");
                tv_price.setText("가격 : "+ "가격(원)");
                tv_location.setText("거래 위치 : "+"거래위치");
                tv_evalPoint.setText("5.0");
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
// 팝업윈도우 관련 reference : https://puzzleleaf.tistory.com/48