package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private ListView lv_board;
    //ArrayList<String> LIST_MENU;
    static final String[] LIST_MENU={"1","2","3","4","5","6","7","8","9","10"};
    private Button btn_write;
    Fragment postingFragment;
    ArrayAdapter board_adapter;
    SwipeRefreshLayout swipe_layout_board;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        lv_board = (ListView)view.findViewById(R.id.lv_board);
        postingFragment = new PostingFragment();
        btn_write=(Button)view.findViewById(R.id.btn_write);
        //LIST_MENU=new ArrayList<>();
        board_adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,LIST_MENU);
        swipe_layout_board = (SwipeRefreshLayout)view.findViewById(R.id.swipe_layout_board);

        // TODO : 기존에 가지고 있는 게시판 리스트가 있다면 불러오기(DB에서??)
//        if(getArguments() != null){
//            // 번들을 통해서 온 데이터가 있는 경우
//            String test=getArguments().getString("et_title","");
//            LIST_MENU.add(test);
//            board_adapter.notifyDataSetChanged();
//        }

        lv_board.setAdapter(board_adapter);

        lv_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText+"번째 아이템, id : "+id, Toast.LENGTH_SHORT).show();
            }
        });

        lv_board.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 리스트뷰 최상단
//                if(!lv_board.canScrollVertically(-1)){
//                }
                if(!lv_board.canScrollVertically(1)){
                    // TODO : 최하단 스크롤 시 보이는 리스트 추가
                    Toast.makeText((MainPageActivity)getActivity(), "리스트뷰 최하단 스크롤 시도", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        // 게시물 작성 버튼 클릭시
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,postingFragment).commit();
            }
        });

        swipe_layout_board.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText((MainPageActivity)getActivity(), "새로고침 정상 작동", Toast.LENGTH_SHORT).show();

                // 새로고침 완료
                swipe_layout_board.setRefreshing(false);
            }
        });

        // 새로고침 화살표 한바퀴 돌때마다 각각의 색상으로 나타난다?
        // TODO : 맨 위 하나만나옴 - 알아보기
        swipe_layout_board.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        // Inflate the layout for this fragment
        return view;
    }
}
// listview 새로고침 reference : https://medium.com/@bluesh55/android-%EB%8B%B9%EA%B2%A8%EC%84%9C-%EC%83%88%EB%A1%9C%EA%B3%A0%EC%B9%A8-%EA%B0%84%EB%8B%A8%ED%95%98%EA%B2%8C-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-a42846c14c23