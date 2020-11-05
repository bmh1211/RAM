package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView wb_test;
    //String url = "https://naver.com";
    String url = "file:///android_asset/web/WebView.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        wb_test = findViewById(R.id.wb_test);
        wb_test.loadUrl(url);

        wb_test.getSettings().setJavaScriptEnabled(true);
        wb_test.setWebChromeClient(new WebChromeClient());
        // 웹뷰에 크롬 사용 허용
        wb_test.setWebViewClient(new WebViewClient());
        // 새로운 창이 아닌 기존의 창에서 실행되도록
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wb_test.canGoBack()) {
            wb_test.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}