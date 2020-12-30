package com.example.login;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.network.NetworkTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MyPageFragment extends Fragment {
    private Toolbar tb_myPage;
    private TextView tv_name;
    private TextView tv_nickname;
    private TextView tv_email;
    private ListView lv_recentSell;
    private ListView lv_recentBuy;
    private ListView lv_favorite;
    static final String[] LIST_MENU={"LIST_1","LIST_2","LIST_3"};
    Fragment fragment1;
    Fragment fragment_change_profile;
    Button btn_changeProfile;
    
    // 연결 테스트용 버튼
    Button btn_connection;

    //private PopupWindow pw_sellerInfo;
    private PopupWindow ll_sellerInfo;
    private TextView tv_sellerName;
    private TextView tv_price;
    private TextView tv_location;
    private TextView tv_evalPoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

//        tb_myPage = (Toolbar)view.findViewById(R.id.tb_myPage);
        lv_recentSell=(ListView)view.findViewById(R.id.lv_recentSell);
        lv_recentBuy=(ListView)view.findViewById(R.id.lv_recentBuy);
        lv_favorite=(ListView)view.findViewById(R.id.lv_favorite);
        fragment1 = new BoardFragment();
        fragment_change_profile = new ChangeProfileFragment();
        btn_changeProfile=(Button)view.findViewById(R.id.btn_changeProfile);

        // 리스트들 통신 테스트
        this.LoadTest();

        // 연결 테스트용
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
        tv_email = (TextView) view.findViewById(R.id.tv_email);

        btn_connection=(Button)view.findViewById(R.id.btn_connection);
        btn_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/myPage/info","GET");
                try{
                    JSONObject resultObject = new JSONObject(networkTask.execute().get());
                    if(resultObject == null){
                        Log.w("연결결과","연결실패");
                    }
                    else{
                        Toast.makeText(getActivity(), resultObject.getString("msg"), Toast.LENGTH_SHORT).show();

                        // 받아온 string 데이터로 기존의 텍스트뷰 내용 바꾸기
                        tv_name.setText(resultObject.getString("userName"));
                        tv_nickname.setText(resultObject.getString("nickName"));
                        tv_email.setText("● 이메일 : "+resultObject.getString("id"));
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }catch(ExecutionException e){
                    e.printStackTrace();
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });

//        setSupportActionBar(tb_myPage);
//        getSupportActionBar().setTitle("My Page");
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dialog_close_dark); //뒤로가기버튼 모양

        // 리스트에 들어갈 내용은 나중에 변경시켜줄 예정
        ArrayAdapter sell_adapter = new ArrayAdapter(container.getContext(),android.R.layout.simple_list_item_1,LIST_MENU);
        ArrayAdapter buy_adapter = new ArrayAdapter(container.getContext(),android.R.layout.simple_list_item_1,LIST_MENU);
        ArrayAdapter favorite_adapter = new ArrayAdapter(container.getContext(),android.R.layout.simple_list_item_1,LIST_MENU);

        lv_recentSell.setAdapter(sell_adapter);
        lv_recentBuy.setAdapter(buy_adapter);
        lv_favorite.setAdapter(favorite_adapter);

        lv_recentSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

                // 프래그먼트로 이동 -> 현재는 테스트용으로 fragment1 으로 지정해놓음
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
            }
        });

        lv_favorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

                // 프래그먼트로 이동 -> 현재는 테스트용으로 fragment1 으로 지정해놓음
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
            }
        });

        lv_recentBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

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
                tv_evalPoint.setText("5.0(평점)");
            }
        });

        lv_favorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();
            }
        });

        btn_changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(container.getContext(), "정보수정", Toast.LENGTH_SHORT).show();
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_change_profile).commit();
            }
        });

        return view;
    }

    private void LoadTest()   //fragment 불릴때 게시글 목록 생성
    {
        NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/","GET");

        try{
            JSONArray resultObject = new JSONArray(networkTask.execute().get());

            if(resultObject==null){
                Log.w("연결결과","연결 실패");
            }
            else{
                for(int i = 0; i < resultObject.length(); i++){
                    // JSONArray에 있는 i번째 JSONObject
                    // tempObject.getString("key이름")으로 스트링값 받아옴
                    JSONObject tempObject = resultObject.getJSONObject(i);
                    
                    LIST_MENU[i]=tempObject.getString("title");
                }
            }
        }catch(ExecutionException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //return super.onCreateOptionsMenu(menu);
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_change_profile,menu);
//
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        //다른 메뉴를 만들경우 여기에 id별 기능 추가(switch - case 사용)
//        switch(item.getItemId()){
//            case android.R.id.home:
//                finish();
//                return true;
//
//            case R.id.item_change_profile:
//                Intent intent_changeProfile = new Intent(this, ChangeProfileActivity.class);
//
//                Toast.makeText(getApplicationContext(), "정보수정 버튼 누름", Toast.LENGTH_SHORT).show();
//                startActivity(intent_changeProfile);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
// 툴바 관련 reference : https://www.hanumoka.net/2017/10/28/android-20171028-android-toolbar/
// 리스트뷰 관련 reference : https://recipes4dev.tistory.com/42
// 팝업윈도우 관련 reference : https://puzzleleaf.tistory.com/48