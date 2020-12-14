package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeProfileFragment extends Fragment {
    private EditText et_present_password;
    private EditText et_change_password;
    private EditText et_check_password;
    private EditText et_change_nickname;
    private TextView tv_present_password;
    private TextView tv_change_password;
    private TextView tv_check_password;
    private TextView tv_change_nickname;
    private ImageView iv_present_password;
    private ImageView iv_change_password;
    private ImageView iv_check_password;
    private ImageView iv_change_nickname;
    private Button btn_change_cancel;
    private Button btn_change_apply;
    Fragment fragment_my_page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_profile, container, false);

        this.InitializeView(view);
        this.SetListener();

        return view;
    }

    public void InitializeView(View view){
        et_present_password=(EditText)view.findViewById(R.id.et_present_password);
        et_change_password=(EditText)view.findViewById(R.id.et_change_password);
        et_check_password=(EditText)view.findViewById(R.id.et_check_password);
        et_change_nickname=(EditText)view.findViewById(R.id.et_change_nickname);
        tv_present_password=(TextView)view.findViewById(R.id.tv_present_password);
        tv_change_password=(TextView)view.findViewById(R.id.tv_change_password);
        tv_check_password=(TextView)view.findViewById(R.id.tv_check_password);
        tv_change_nickname=(TextView)view.findViewById(R.id.tv_change_nickname);
        iv_present_password=(ImageView)view.findViewById(R.id.iv_present_password);
        iv_change_password=(ImageView)view.findViewById(R.id.iv_change_password);
        iv_check_password=(ImageView)view.findViewById(R.id.iv_check_password);
        iv_change_nickname=(ImageView)view.findViewById(R.id.iv_change_nickname);
        btn_change_apply = (Button)view.findViewById(R.id.btn_change_apply);
        btn_change_cancel=(Button)view.findViewById(R.id.btn_change_cancel);
        fragment_my_page = new MyPageFragment();
    }

    public void SetListener(){
        btn_change_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_my_page).commit();
            }
        });

        btn_change_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPageActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_my_page).commit();
            }
        });
    }
}