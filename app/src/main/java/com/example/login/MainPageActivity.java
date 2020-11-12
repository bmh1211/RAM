package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPageActivity extends AppCompatActivity {
    private Button btn_my_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btn_my_page=findViewById(R.id.btn_my_page);

        Intent intent_my_page = new Intent(this, MyPageActivity.class);

        btn_my_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_my_page);
            }
        });
    }
}