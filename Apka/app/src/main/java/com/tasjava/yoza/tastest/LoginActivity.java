package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import data.model.Post;
import data.model.remote.APIService;
import data.model.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private TextView dResponse;
    private APIService mAPIService;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText dUsername = (EditText) findViewById(R.id.dUsername);
        final EditText dPassword = (EditText) findViewById(R.id.dPassword);
        final TextView dRegisterLink = (TextView) findViewById(R.id.dRegisterLink);
        final Button loginBtn = (Button) findViewById(R.id.loginBtn);
        final Button nextActivitybtn = (Button) findViewById(R.id.nextActivitybtn);

        mAPIService = ApiUtils.getAPIService();
        dResponse = (TextView) findViewById(R.id.dResponse);

        dRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String login = dUsername.getText().toString().trim();
                String password = dPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
                    sendPost(login, password);
                    Intent loginSuccessIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(loginSuccessIntent);
                }
            }
        });

        nextActivitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivityIntent = new Intent(LoginActivity.this, TestActivity.class);
                LoginActivity.this.startActivity(nextActivityIntent);
            }
        });



    }

    public void sendPost(String login, String password) {
        mAPIService.savePost(login, password).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, retrofit2.Response<Post> response) {

                if (response.isSuccessful()) {
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

