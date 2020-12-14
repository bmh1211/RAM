package com.example.login.network;

import android.content.Context;
import android.os.AsyncTask;

import com.example.login.HTTPConnetction;

import org.json.JSONObject;

import okhttp3.internal.http.HttpMethod;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    String url;
    JSONObject values;
    String method;
    Context context;

    public NetworkTask(Context context,String url, JSONObject values, String HttpMethod){
        this.url = url;
        this.values = values;
        this.method=HttpMethod;
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progress bar를 보여주는 등등의 행위
    }

    @Override
    protected String doInBackground(Void... params) {
        JSONObject result;
        HTTPConnetction requestHttpURLConnection = new HTTPConnetction();
        if(method.equals("POST")){
            result = requestHttpURLConnection.HttpPost(context,url, values);
        }
        else if(method.equals("GET")){
            result = requestHttpURLConnection.HttpGet(url);
        }
        else{
            result=null;
        }
        return result.toString(); // 결과가 여기에 담깁니다. 아래 onPostExecute()의 파라미터로 전달됩니다.
    }

    @Override
    protected void onPostExecute(String result) {
        // 통신이 완료되면 호출됩니다.
        // 결과에 따른 수정 등은 여기서 합니다.
        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}

