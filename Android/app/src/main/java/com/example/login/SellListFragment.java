package com.example.login;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.login.Data.ListItem;
import com.example.login.adapter.TradeListAdapter;
import com.example.login.network.NetworkTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SellListFragment extends Fragment {
    private ListView lv_recentSell;
    Fragment fragment_board;
    static final String[] LIST_MENU={"판매 내역 리스트","LIST_1","LIST_2","LIST_3"};
    static final ArrayList<ListItem> itemlist = new ArrayList<ListItem>();
    private Button btn_mypage;
    Fragment fragment_mypage;
    SwipeRefreshLayout swipe_layout_board;
    private TradeListAdapter sell_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_list, container, false);

        btn_mypage = (Button)view.findViewById(R.id.btn_mypage);
        fragment_board = new BoardFragment();
        fragment_mypage = new MyPageFragment();
        lv_recentSell=(ListView)view.findViewById(R.id.lv_recentSell);
        //swipe_layout_board = (SwipeRefreshLayout)view.findViewById(R.id.swipe_layout_board);

        // 게시판 데이터 까기 테스트
        this.GetSellPost();

        sell_adapter = new TradeListAdapter(container.getContext(),itemlist);
        lv_recentSell.setAdapter(sell_adapter);

        lv_recentSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

                // 프래그먼트로 이동 -> 현재는 테스트용으로 fragment1 으로 지정해놓음
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_board).commit();
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

    public void GetSellPost()
    {
        Log.w("GetPosting","함수 실행");
        NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/trade/list?type=false","GET"); // true가 구매리스트, false가 판매리스트
        try{
            //{"msg":"조회 성공","tradeVo":{"tradeId":1,"buyerId":"bmh1211@gmail.com","sellerId":"jae961217@naver.com","boardId":"1","tradeTime":"2020-12-23T00:00:00.000+00:00","boardTitle":null}}
            JSONObject resultObject = new JSONObject(networkTask.execute().get());
            Log.w("resultObject",resultObject.toString());

            // list일 경우
            //JSONArray buyArray = resultObject.getJSONArray("list");

            if(resultObject == null)
            {
                Log.e("연결결과","연결실패");
                return;
            }

            String resultString = resultObject.getString("msg");

            if(resultString.equals("거래내역이 없습니다")){
                Toast.makeText(getActivity(),resultString, Toast.LENGTH_SHORT).show();
            }
            else if(resultString.equals("조회 성공")){
                String title, tradeTime, userID;
                title = resultObject.getString("boardTitle");
                tradeTime = resultObject.getString("tradeTime");
                userID = resultObject.getString("buyerId");

                // 생성된 아이템 목록에 추가
                sell_adapter.addItem(title,userID,tradeTime);
                sell_adapter.notifyDataSetChanged();
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