package com.example.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.login.network.NetworkTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class TradeListFragment extends Fragment {
    private String str_CurrentPhotoPath = "";
    private ImageButton ib_take_picture;
    final static int REQUEST_TAKE_PHOTO = 99;
    private Button btn_submit;
    private EditText et_password_submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade_list, container, false);

        // 서버에 전송할 데이터 관련
        ib_take_picture = (ImageButton) view.findViewById(R.id.ib_take_pictrue);
        et_password_submit = (EditText) view.findViewById(R.id.et_password_submit);
        btn_submit = (Button)view.findViewById(R.id.btn_submit);

        // 권한 요청 받기
        this.checkPermissions();

        ib_take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재는 버튼을 누르면 사진을 찍도록 되어있지만 qr을 찍어서 인식되면 바로 카메라 켜지도록 변경
                getImageFromCamera();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_CurrentPhotoPath.equals("")){
                    Toast.makeText(getActivity(), "등록 할 사진이 필요합니다", Toast.LENGTH_SHORT).show();
                }
                else{
//                    sendImageFile(et_password_submit.getText().toString(), str_CurrentPhotoPath);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void checkPermissions() {
        String permission_camera = Manifest.permission.CAMERA;
        String[] permissions = {permission_camera};

        int PERMISSION_ALL = 1;

        // checkSelfPermission이 API 23부터 가능, 마시멜로우(Build.VERSION_CODES.M)이 API 23임
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(permission_camera) == PackageManager.PERMISSION_GRANTED) {
                Log.w("권한설정 상태", "권한 설정 완료");
            } else {
                Log.w("권한설정 상태", "권한 설정 요청");
                ActivityCompat.requestPermissions(getActivity(), permissions, PERMISSION_ALL);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // permissions[0] == Manifest.permission.CAMERA , permissions[1] == Manifest.permission.WRITE_EXTERNAL_STORAGE
        // grantResults[0] => permissions[0]의 권한에 대한 결과 , grantResults[1] => permissions[1]의 권한에 대한 결과
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.w("권한설정 결과함수 호출", "onRequestPermissionsResult");

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            // PERMISSION_GRANTED == 0 , PERMISSION_DENIED == -1
            Log.w("권한설정 결과", "Permission : " + permissions[0] + " was " + grantResults[0]);
            Log.w("권한설정 결과", "Permission : " + permissions[1] + " was " + grantResults[1]);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        try {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO:
                    if (resultCode == RESULT_OK) {
                        Log.w("TradeListFragment", "사진 파일 생성");
                        Log.w("TradeListFragment", "str_CurrentPhotoPath : " + str_CurrentPhotoPath); // 해당 파일의 경로
                        File file_from_path = new File(str_CurrentPhotoPath);
                        Bitmap bitmap = (Bitmap) MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), Uri.fromFile(file_from_path));

                        if (bitmap != null) {
                            ib_take_picture.setImageBitmap(bitmap);
                        }
                    } else if (resultCode == RESULT_CANCELED) {
                        Log.w("TradeListFragment", "사진 파일 생성 실패");
                        str_CurrentPhotoPath = "";
                        Log.w("TradeListFragment", "str_CurrentPhotoPath : " + str_CurrentPhotoPath); // 해당 파일의 경로
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카메라로 찍은 사진을 파일로 만들어주는 함수
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        str_CurrentPhotoPath = image.getAbsolutePath();

        Log.w("TradeListFragment", "imageFileName : " + imageFileName + ".jpg"); // 생성된 파일 이름
        Log.w("TradeListFragment", "str_CurrentPhotoPath : " + str_CurrentPhotoPath); // 해당 파일의 경로

        return image;
    }

    // 카메라로 사진을 찍는 함수
    private void getImageFromCamera() {
        Intent intent_take_picture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent_take_picture.resolveActivity(getActivity().getPackageManager()) != null) {
            File file_image = null;
            try {
                file_image = createImageFile();
            } catch (Exception e) {
                Log.w("TradeListFragment", "그림 파일 만드는 도중 에러 발생");
            }
            if (file_image != null) {
                Uri uri_image = FileProvider.getUriForFile(getActivity(), "com.example.login.fileprovider", file_image);

                intent_take_picture.putExtra(MediaStore.EXTRA_OUTPUT, uri_image);
                startActivityForResult(intent_take_picture, REQUEST_TAKE_PHOTO);
            }
        }
    }

    // 만약 비밀번호와 사진을 같이 보낸다면 이 함수 사용(url 수정 필요), 사진만 보낸다면 jsonObject에서 비밀번호 들어가는 부분 제거
    // sendImageFile(send_title,send_posting,date_posting,10000,str_CurrentPhotoPath);
    public void sendImageFile(String password, String path) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetworkTask fileNetworkTask = new NetworkTask(((MainPageActivity) getActivity()).getApplicationContext(), "http://3.35.48.170:3000/", jsonObject, path, "FILE");

        try {
            JSONObject resultFileObject = new JSONObject(fileNetworkTask.execute().get());
            // resultFileObjet = "{"msg":"Register success"}"
            System.out.println(resultFileObject);

            if (resultFileObject.getString("msg").equals("Register success")) {
                Toast.makeText(getActivity(), resultFileObject.getString("msg"), Toast.LENGTH_SHORT).show();
            } else if (resultFileObject == null) {
                Log.w("실패 알림", "연결 실패");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}