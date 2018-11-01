package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import data.model.Post;
import data.model.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import data.model.remote.APIService;

public class RegisterActivity extends AppCompatActivity {

    private TextView dResponse;
    private APIService mAPIService;

    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText dUsername = (EditText) findViewById(R.id.dUsername);
        final EditText dPassword = (EditText) findViewById(R.id.dPassword);
        final Button registerBtn = (Button) findViewById(R.id.registerBtn);

        dResponse = (TextView) findViewById(R.id.dResponse);

        mAPIService = ApiUtils.getAPIService();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = dUsername.getText().toString().trim();
                String password = dPassword.getText().toString().trim();
                if(!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)){
                    sendPost(login, password);
                }
            }
        });
        }
        public void sendPost(String login, String password){
            mAPIService.savePost(login, password).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, retrofit2.Response<Post> response) {

                    if(response.isSuccessful()){
                        showResponse(response.body().toString());
                        Log.i(TAG, "post sent to API" + response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
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


}
