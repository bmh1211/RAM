package com.example.login;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView iv_profile;
    private TextView tv_name_real;
    private TextView tv_nickname_real;
    private TextView tv_email_real;
    private TextView tv_favoriteRegion_real;
    private TextView tv_phoneNumber_real;
    private TextView tv_bank_real;
    private TextView tv_bankaccount_real;
    private TextView tv_point_real;

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

    private PopupWindow pw_checkPW;
    private Button btn_check;
    private EditText et_present_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

//        tb_myPage = (Toolbar)view.findViewById(R.id.tb_myPage);
        fragment1 = new BoardFragment();
        fragment_change_profile = new ChangeProfileFragment();
        btn_changeProfile=(Button)view.findViewById(R.id.btn_changeProfile);
        btn_list_trade_pick=(Button)view.findViewById(R.id.btn_list_trade_pick);

        iv_profile = (ImageView) view.findViewById(R.id.iv_profile);
        tv_name_real = (TextView) view.findViewById(R.id.tv_name_real);
        tv_nickname_real = (TextView) view.findViewById(R.id.tv_nickname_real);
        tv_email_real = (TextView) view.findViewById(R.id.tv_email_real);
        tv_favoriteRegion_real = (TextView) view.findViewById(R.id.tv_favoriteRegion_real);
        tv_phoneNumber_real = (TextView) view.findViewById(R.id.tv_phoneNumber_real);
        tv_bank_real = (TextView) view.findViewById(R.id.tv_bank_real);
        tv_bankaccount_real = (TextView) view.findViewById(R.id.tv_bankaccount_real);
        tv_point_real = (TextView) view.findViewById(R.id.tv_point_real);

        // 마이페이지에 유저 데이터 출력
        this.GetUserData();

        // 마이페이지에 유저 프로필 사진 출력
        // todo : this.함수();

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
                //((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_change_profile).commit();
                pwCheckPopUp();
            }
        });

        return view;
    }

    public void GetUserData(){
        NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/myPage/info","GET");
        try{
            JSONObject resultObject = new JSONObject(networkTask.execute().get());
            JSONObject userObject = resultObject.optJSONObject("user");

            if(resultObject == null){
                Log.w("연결결과","연결실패");
            }
            else{
                Toast.makeText(getActivity(), resultObject.getString("msg"), Toast.LENGTH_SHORT).show();
                Log.w("resultObject : ",userObject.toString());

                // userObject => {"id":"bmh1211@gmail.com","password":"1234","userName":"Bang","phoneNumber":"010-5014-3278","nickName":"Mino","bank":"HANA","account":"74813243423","point":13,"region":"INCHEON"}
                tv_name_real.setText(userObject.getString("userName"));
                tv_nickname_real.setText(userObject.getString("nickName"));
                tv_email_real.setText(userObject.getString("id"));
                tv_favoriteRegion_real.setText(userObject.getString("region"));
                tv_phoneNumber_real.setText(userObject.getString("phoneNumber"));
                tv_bank_real.setText(userObject.getString("bank"));
                tv_bankaccount_real.setText(userObject.getString("account"));
                tv_point_real.setText(Integer.toString(userObject.getInt("point")));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
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

    public void pwCheckPopUp(){
        NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/myPage/info","GET");
        try{
            JSONObject resultObject = new JSONObject(networkTask.execute().get());
            JSONObject userObject = resultObject.optJSONObject("user");

            if(resultObject == null){
                Log.w("연결결과","연결실패");
            }
            else{
                Toast.makeText(getActivity(), resultObject.getString("msg"), Toast.LENGTH_SHORT).show();
                Log.w("resultObject : ",userObject.toString());

                // userObject => {"id":"bmh1211@gmail.com","password":"1234","userName":"Bang","phoneNumber":"010-5014-3278","nickName":"Mino","bank":"HANA","account":"74813243423","point":13,"region":"INCHEON"}
                View popupView = getLayoutInflater().inflate(R.layout.popupwindow_check_password,null);
                pw_checkPW = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                pw_checkPW.setFocusable(true);
                pw_checkPW.showAtLocation(popupView, Gravity.CENTER,0,0);

                et_present_password = (EditText)pw_checkPW.getContentView().findViewById(R.id.et_present_password);
                btn_check = (Button)pw_checkPW.getContentView().findViewById(R.id.btn_check);

                btn_check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            Log.w("입력한 비밀번호",et_present_password.getText().toString());
                            Log.w("가져온 비밀번호",userObject.getString("password"));

                            if(et_present_password.getText().toString().equals(userObject.getString("password"))){
                                // 비밀번호가 일치하면 정보수정 프레그먼트로 이동
                                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_change_profile).commit();

                                pw_checkPW.dismiss();
                            }
                            else{
                                // 비밀번호가 일치하지 않으면 써놓은 비밀번호 지우기
                                Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                                et_present_password.setText(null);
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
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