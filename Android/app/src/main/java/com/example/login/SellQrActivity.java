package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class SellQrActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    private String resultString="";
    private String TAG="SellQrActivity";
    private Fragment fragment_trade_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_qr);

        // 처음 qr코드 인식 관련
        fragment_trade_list = new TradeListFragment();
        qrScan = new IntentIntegrator(this);

        // qr코드 스캔
        this.QRcheck();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        try{
            switch(requestCode){
                case IntentIntegrator.REQUEST_CODE:
                    IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
                    if(result!=null){
                        if(result.getContents()==null){
                            //qr 코드가 없는 경우
                            Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show();
                            Log.w(TAG,"NULL");
                        }
                        else{
                            //qr 코드가 존재하는 경우
                            Toast.makeText(this, "스캔되었습니다", Toast.LENGTH_SHORT).show();
                            Log.w(TAG,result.getContents());

                            try{
                                // data를 json으로 변환
                                JSONObject jsonObject = new JSONObject(result.getContents());
                                Log.w(TAG,jsonObject.toString());

                                // 스캔 성공해서 Tester에서 MINO를 받아올 경우에 넘어갈 수 있도록
                                if(jsonObject.getString("msg").equals("Success")){
                                    resultString = jsonObject.getString("Tester");

                                    if(resultString.equals("MINO")){
                                        Log.w(TAG,"QR 스캔 성공");
                                        getSupportFragmentManager().beginTransaction().replace(R.id.QR_container,fragment_trade_list).commit();
                                    }
                                }
                            }catch(JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        super.onActivityResult(requestCode,resultCode,intent);
                    }

                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void QRcheck(){
        qrScan.setPrompt("QR코드를 스캔해주세요0");
        qrScan.setOrientationLocked(false);
        qrScan.setBeepEnabled(false);
        qrScan.initiateScan();
    }
}