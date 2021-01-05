package com.example.login;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

    static final String[] LIST_MENU={"LIST_1","LIST_2","LIST_3"};
    Fragment fragment1;
    Fragment fragment_change_profile;
    Button btn_changeProfile;
    Button btn_list_trade_pick;

    private PopupWindow pw_chooser;
    private Button btn_buy_list;
    private Button btn_sell_list;
    private Button btn_pick_list;
    Fragment fragment_buy_list;
    Fragment fragment_sell_list;
    Fragment fragment_pick_list;

    // 연결 테스트용 버튼
    Button btn_connection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

//        tb_myPage = (Toolbar)view.findViewById(R.id.tb_myPage);
        fragment1 = new BoardFragment();
        fragment_change_profile = new ChangeProfileFragment();
        btn_changeProfile=(Button)view.findViewById(R.id.btn_changeProfile);
        btn_list_trade_pick=(Button)view.findViewById(R.id.btn_list_trade_pick);

        // 리스트들 통신 테스트
        //this.LoadTest();

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

        btn_list_trade_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopupChooser();
            }
        });

//        setSupportActionBar(tb_myPage);
//        getSupportActionBar().setTitle("My Page");
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //자동으로 뒤로가기 버튼을 만들어줌
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dialog_close_dark); //뒤로가기버튼 모양

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
            // TODO : 목록 불러오기
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

    public void setPopupChooser(){
        View popupView = getLayoutInflater().inflate(R.layout.popupwindow_chooser_buy_sell_pick,null);
        pw_chooser = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        pw_chooser.setFocusable(true);
        pw_chooser.showAtLocation(popupView, Gravity.CENTER,0,0);

        btn_buy_list = (Button)pw_chooser.getContentView().findViewById(R.id.btn_buy_list);
        btn_sell_list = (Button)pw_chooser.getContentView().findViewById(R.id.btn_sell_list);
        btn_pick_list = (Button)pw_chooser.getContentView().findViewById(R.id.btn_pick_list);

        btn_buy_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_buy_list = new BuyListFragment();
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_buy_list).commit();

                pw_chooser.dismiss();
            }
        });

        btn_sell_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_sell_list = new SellListFragment();
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_sell_list).commit();

                pw_chooser.dismiss();
            }
        });

        btn_pick_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_pick_list = new PickListFragment();
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_pick_list).commit();

                pw_chooser.dismiss();
            }
        });
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