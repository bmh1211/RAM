package com.example.login;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPConnetction { //ddddd

    public static void HttpGet() {
        new AsyncTask<Void, Void, JSONObject>() {
            @Override
            protected JSONObject doInBackground(Void... voids) {
                JSONObject result = null;
                try {
                    URL url = new URL("https://0f0e146e0b89.ngrok.io/upload");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestProperty("key", "value");
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(15000); //통신 타임아웃

                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                    } else {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                    }
                } catch (ConnectException e) {
                    Log.e("test", "ConnectionException");
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
            }
        }.execute();
    }

    public static void HttpPost(String URL, JSONObject jsonObject){

        new AsyncTask<URL, Void, JSONObject>(){ //doInBackground파라미터 타입, onProgressUpdate 파라미터 타입, doInBackground 리턴값
            @Override
            protected JSONObject doInBackground(URL... voids) {

                JSONObject result = null;
                try{
                    URL url = new URL("요청 URL");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestProperty("content-type", "application/json; utf-8");
                    connection.setRequestProperty("Accept","application/json");
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(15000);

                    String jsonInputString  = jsonObject.toString();  //보낼데이터
                    try(OutputStream os = connection.getOutputStream()){
                        byte[] input = jsonInputString.getBytes("utf-8"); //전송할값
                        os.write(input,0,input.length);
                        os.flush();
                        os.close();
                    }

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                    } else {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        result = new JSONObject(response.toString());
                    }

                } catch (ConnectException e) {
                    Log.e("test", "ConnectException");
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
            }
        }.execute();
    }

}
