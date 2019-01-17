package com.tasjava.yoza.tastest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import data.model.AddGame;
import data.model.PostLogin;
import data.model.remote.APIService;
import data.model.remote.ApiUtils;
import data.model.remote.NetworkClient;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ProfileActivity extends AppCompatActivity {

    private TextView dResponse;
    private Intent image;
    private MultipartBody.Part multipartBody;
    private String filePath;
    private Uri fileUri;
    private File fineIntStr;
    private Context context = this;
    private APIService mAPIService;
    private static final String TAG = "ProfileActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            fileUri = data.getData();
            filePath = FileChooser.getPath(context,fileUri);
            System.out.print(filePath);
        }//
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted and now can proceed
//                    mymethod(); //a sample method called

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ProfileActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // add other cases for more permissions
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActivityCompat.requestPermissions(ProfileActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

//        -----------------
//        KOMPONENTY LAYOUTU
//        -----------------
        
        final ImageView profileimage = (ImageView) findViewById(R.id.profileImage);
        final TextView username = (TextView) findViewById(R.id.pUsername);
        final Button updateImageBtn = (Button) findViewById(R.id.updateImageBtn);

        dResponse = (TextView) findViewById(R.id.dResponse);


        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        updateImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = username.getText().toString().trim();
                if(!TextUtils.isEmpty(login)){
                    sendImage(login, fileUri);
                    Log.e(TAG, "onClick: IMAGE UPDATED");
                }
            }
        });
        mAPIService = ApiUtils.getAPIService();

        Call<ResponseBody> call = mAPIService.getLoggedUsername();

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("Response", response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
//                        Log.i("onSuccess", response.body().toString());
                        try{
                            username.setText(response.body().string());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void sendImage(String login, Uri fileUri){
        Retrofit retrofit = NetworkClient.getRetrofitClient(this);
        APIService uploadAPIs = retrofit.create(APIService.class);

        getRealPathFromURI(context, fileUri);

        File file = new File(filePath);
        
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);


        mAPIService.updateProfileImage(login, part).enqueue(new Callback<ResponseBody>() {
//          
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                if(response.isSuccessful()){
                    showResponse(response.body().toString());
                Log.i(TAG, "post sent to API" + response.body().toString());
                }
    }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "unable to sent a post to API");
                t.printStackTrace();
            }
        });
    }
    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            Log.e(TAG, "getRealPathFromURI Exception : " + e.toString());
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public void showResponse(String response){
        if(dResponse.getVisibility() == View.GONE){
            dResponse.setVisibility(View.VISIBLE);
        }
        dResponse.setText(response);
    }
}
