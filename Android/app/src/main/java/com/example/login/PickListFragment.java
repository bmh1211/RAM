package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PickListFragment extends Fragment {
    private ListView lv_favorite;
    Fragment fragment_board;
    static final String[] LIST_MENU={"찜 내역 리스트","LIST_1","LIST_2","LIST_3"};
    private Button btn_mypage;
    Fragment fragment_mypage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_list, container, false);

        btn_mypage = (Button)view.findViewById(R.id.btn_mypage);
        fragment_mypage = new MyPageFragment();
        fragment_board = new BoardFragment();
        lv_favorite = (ListView)view.findViewById(R.id.lv_favorite);

        ArrayAdapter favorite_adapter = new ArrayAdapter(container.getContext(),android.R.layout.simple_list_item_1,LIST_MENU);
        lv_favorite.setAdapter(favorite_adapter);

        lv_favorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();

                // 프래그먼트로 이동 -> 현재는 테스트용으로 fragment1 으로 지정해놓음
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_board).commit();
            }
        });

        lv_favorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position) ;
                Toast.makeText(container.getContext(), strText, Toast.LENGTH_SHORT).show();
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
}