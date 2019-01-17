package com.tasjava.yoza.tastest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import data.model.AddGame;
import data.model.Post;
import data.model.remote.APIService;
import data.model.remote.ApiUtils;
import data.model.remote.NetworkClient;
import data.model.remote.RetrofitClient;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

import static java.security.AccessController.getContext;

public class AddGameActivity extends AppCompatActivity {

    private TextView dResponse;
    private APIService mAPIService;
    private Intent image;
    private MultipartBody.Part multipartBody;
    private String filePath;
    private Uri fileUri;
    private File fineIntStr;
    private Context context = this;
    private static final String TAG = "AddGameActivity";

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
                    Toast.makeText(AddGameActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // add other cases for more permissions
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        ActivityCompat.requestPermissions(AddGameActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        final EditText gametitle = (EditText) findViewById(R.id.gametitle);
        final EditText gamelaunchdate = (EditText) findViewById(R.id.gamelaunchdate);
        final EditText gamepublisher = (EditText) findViewById(R.id.gamepublisher);
        final EditText gameplatform = (EditText) findViewById(R.id.gameplatform);
        final EditText gamegenre = (EditText) findViewById(R.id.gamegenre);
        final EditText gamedescription = (EditText) findViewById(R.id.gamedescription);
        final Button addimage = (Button) findViewById(R.id.addimage);
        final Button addgamebtn = (Button) findViewById(R.id.addgamebtn);

        dResponse = (TextView) findViewById(R.id.dResponse);

        mAPIService = ApiUtils.getAPIService();

        addgamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = gametitle.getText().toString().trim();
                String launchDate = gamelaunchdate.getText().toString().trim();
                String publisher = gamepublisher.getText().toString().trim();
                String platform = gameplatform.getText().toString().trim();
                String genre = gamegenre.getText().toString().trim();
                String description = gamedescription.getText().toString().trim();
//                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(launchDate) && !TextUtils.isEmpty(publisher) &&
//                        !TextUtils.isEmpty(platform) && !TextUtils.isEmpty(genre) && !TextUtils.isEmpty(description)){
//                    sendGame(title, launchDate, publisher, platform, genre, description);
//
//                }
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(launchDate) && !TextUtils.isEmpty(publisher) &&
                        !TextUtils.isEmpty(platform) && !TextUtils.isEmpty(genre) && !TextUtils.isEmpty(description)){
                    sendGame(title, launchDate, publisher, platform, genre, description, fileUri);

                }
            }
        });
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }
    public void sendGame(String title, String launchdate, String publisher, String platform, String genre, String description, Uri fileUri) {
//        public void sendGame(String title, String launchdate, String publisher, String platform, String genre, String description) {
        Retrofit retrofit = NetworkClient.getRetrofitClient(this);
        APIService uploadAPIs = retrofit.create(APIService.class);
//        Create a file object using file path
        getRealPathFromURI(context, fileUri);

        File file = new File(filePath);

//         Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
//         Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);


        mAPIService.addgame(title, launchdate, publisher, platform, genre, description, part).enqueue(new Callback<AddGame>() {
//            mAPIService.addgame(title, launchdate, publisher, platform, genre, description).enqueue(new Callback<AddGame>() {

            @Override
            public void onResponse(Call<AddGame> call, retrofit2.Response<AddGame> response) {

                if(response.isSuccessful()){
                    showResponse(response.body().toString());
                    Log.i(TAG, "post sent to API" + response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<AddGame> call, Throwable t) {
                Log.e(TAG, "unable to sent a post to API");
                t.printStackTrace();
            }
        });
    }
    public void showResponse(String response){
        if(dResponse.getVisibility() == View.GONE){
            dResponse.setVisibility(View.VISIBLE);
        }
        dResponse.setText(response);
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

}
