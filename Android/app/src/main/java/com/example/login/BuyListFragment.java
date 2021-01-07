package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class BuyListFragment extends Fragment {
    private ListView lv_recentBuy;
    static final String[] LIST_MENU={"구매 내역 리스트","LIST_1","LIST_2","LIST_3"};
    private Button btn_mypage;
    Fragment fragment_mypage;

    // 구매내역에서 판매자정보 팝업윈도우에 필요한 데이터들
    private PopupWindow ll_sellerInfo;
    private TextView tv_sellerName;
    private TextView tv_price;
    private TextView tv_location;
    private TextView tv_evalPoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_list, container, false);

        btn_mypage = (Button)view.findViewById(R.id.btn_mypage);
        lv_recentBuy=(ListView)view.findViewById(R.id.lv_recentBuy);
        fragment_mypage = new MyPageFragment();

        this.GetBuyPost();

        ArrayAdapter buy_adapter = new ArrayAdapter(container.getContext(),android.R.layout.simple_list_item_1,LIST_MENU);
        lv_recentBuy.setAdapter(buy_adapter);

        lv_recentBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position);
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

                showSellerInfo();
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_mypage).commit();
            }
        });

        return view;
    }

    public void showSellerInfo(){
        // 팝업창이 들어갈 뷰를 하나 생성해주고, 해당 뷰의 레이아웃을 LinearLayout 으로 지정
        View popupView = getLayoutInflater().inflate(R.layout.popupwindow_seller_info,null);
        ll_sellerInfo=new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        // 외부영역 선택시 PopUp창 사라짐
        ll_sellerInfo.setFocusable(true);

        // 팝업창의 위치를 디스플레이의 중앙에 위치시킴
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

    public void GetBuyPost()
    {
        Log.w("GetPosting","함수 실행");
        NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/trade/list?type=true","GET"); // true가 구매리스트, false가 판매리스트
        try{
            //{"msg":"조회 성공","tradeVo":{"tradeId":1,"buyerId":"bmh1211@gmail.com","sellerId":"jae961217@naver.com","boardId":"1","tradeTime":"2020-12-23T00:00:00.000+00:00","boardTitle":null}}
            JSONObject resultObject = new JSONObject(networkTask.execute().get());
            Log.w("resultObject",resultObject.toString());

            if(resultObject == null)
            {
                Log.e("연결결과","연결실패");
            }
            else{
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}