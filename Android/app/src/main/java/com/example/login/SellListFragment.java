package com.example.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.login.Data.ListItem;
import com.example.login.adapter.TradeListAdapter;
import com.example.login.network.NetworkTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SellListFragment extends Fragment {
    private String TAG = "SellListFragment";
    private ListView lv_recentSell;
    Fragment fragment_board;
    static final ArrayList<ListItem> itemlist = new ArrayList<ListItem>();
    private Button btn_mypage;
    Fragment fragment_mypage;
    SwipeRefreshLayout swipe_layout_board;
    private TradeListAdapter sell_adapter;

    private SharedPreferences.Editor sharedPreferences_fragment_move_editor;
    private Fragment fragment_posting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_list, container, false);

        btn_mypage = (Button)view.findViewById(R.id.btn_mypage);
        fragment_board = new BoardFragment();
        fragment_mypage = new MyPageFragment();
        lv_recentSell=(ListView)view.findViewById(R.id.lv_recentSell);
        //swipe_layout_board = (SwipeRefreshLayout)view.findViewById(R.id.swipe_layout_board);

        sell_adapter = new TradeListAdapter();
        lv_recentSell.setAdapter(sell_adapter);

        sharedPreferences_fragment_move_editor = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE).edit();
        fragment_posting = new PostingFragment();

        // 게시판 데이터 까기 테스트
        this.GetSellPost();

        lv_recentSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = parent.getItemAtPosition(position) ;
                Log.w("SellListFragment","listItem : "+listItem.toString());
//                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

                sharedPreferences_fragment_move_editor.putString("fragment_move","SellListFragment").commit();

                // 프래그먼트로 이동 -> 현재는 테스트용으로 fragment1 으로 지정해놓음
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_posting).commit();
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
        Log.w(TAG,"GetSellPost() 함수 실행");

        NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/trade/list?type=false&tradeTime=2020-01-01","GET"); // true가 구매리스트, false가 판매리스트
        try{
            //{"msg":"success","tradeVo":{"tradeId":1,"buyerId":"bmh1211@gmail.com","sellerId":"jae961217@naver.com","boardId":"1","tradeTime":"2020-12-23T00:00:00.000+00:00","boardTitle":null}}
            JSONObject resultObject = new JSONObject(networkTask.execute().get());

            if(resultObject == null)
            {
                Log.e("연결결과","연결실패");
                return;
            }

            String resultString = resultObject.getString("msg");

            if(resultString.equals("failed")){
                Toast.makeText(getActivity(),resultString, Toast.LENGTH_SHORT).show();
            }
            else if(resultString.equals("success")){
                Toast.makeText(getActivity(),resultString, Toast.LENGTH_SHORT).show();

                JSONObject sellObject = resultObject.getJSONObject("tradeVo");
                Log.w("SellListFragment",sellObject.toString());

                String title, tradeTime, userID;
                title = sellObject.getString("title");
                tradeTime = sellObject.getString("tradeTime").substring(0, 10);
                userID = sellObject.getString("buyerId");

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

    public void LoadSellPost(){
        Log.w(TAG,"LoadSellPost() 함수 실행");

        String date = "";

        NetworkTask networkTask = new NetworkTask(getActivity().getApplicationContext(),"http://3.35.48.170:3000/trade/list?type=false&tradeTime="+date,"GET"); // true가 구매리스트, false가 판매리스트
        try{
            //{"msg":"success","tradeVo":{"tradeId":1,"buyerId":"bmh1211@gmail.com","sellerId":"jae961217@naver.com","boardId":"1","tradeTime":"2020-12-23T00:00:00.000+00:00","boardTitle":null}}
            JSONObject resultObject = new JSONObject(networkTask.execute().get());

            if(resultObject == null)
            {
                Log.e("연결결과","연결실패");
                return;
            }

            String resultString = resultObject.getString("msg");

            if(resultString.equals("failed")){
                Toast.makeText(getActivity(),resultString, Toast.LENGTH_SHORT).show();
            }
            else if(resultString.equals("success")){
                Toast.makeText(getActivity(),resultString, Toast.LENGTH_SHORT).show();

                JSONObject sellObject = resultObject.getJSONObject("tradeVo");
                Log.w("SellListFragment",sellObject.toString());

                String title, tradeTime, userID;
                title = sellObject.getString("title");
                tradeTime = sellObject.getString("tradeTime").substring(0, 10);
                userID = sellObject.getString("buyerId");

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

    public void SetItemToBoard(ViewGroup container){
        lv_recentSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

                // 프래그먼트로 이동 -> 현재는 테스트용으로 fragment_mypage 로 지정해놓음
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_mypage).commit();


            }
        });
    }
}