package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import data.model.remote.APIService;
import data.model.remote.ApiUtils;
import data.model.remote.Review;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReviewActivity extends AppCompatActivity {

    private APIService mAPIService;
    private APIService mAPIService2;
    private Spinner spinner;
//    private String username;
    private static final String TAG = "AddReviewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        addItemsToSpinner();

        final EditText reviewtitle = (EditText) findViewById(R.id.reviewtitle);
        final EditText reviewdescription = (EditText) findViewById(R.id.reviewcontent);
        final ImageView homebutton = (ImageView) findViewById(R.id.homebutton);
        final ImageView profilebtn = (ImageView) findViewById(R.id.profilebtn);
        final ImageView supportbtn = (ImageView) findViewById(R.id.supportbtn);
        final ImageView rankingbtn = (ImageView) findViewById(R.id.rankingbtn);
        final Button addreview = (Button)  findViewById(R.id.addreview);
//        final String username;
//        final Spinner ratespinner = (Spinner) findViewById(R.id.rewievratespinner);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(AddReviewActivity.this, MainActivity.class);
                AddReviewActivity.this.startActivity(homeIntent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(AddReviewActivity.this, ProfileActivity.class);
                AddReviewActivity.this.startActivity(profileIntent);
            }
        });
        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(AddReviewActivity.this, SupportActivity.class);
                AddReviewActivity.this.startActivity(supportIntent);
            }
        });

        rankingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingIntent = new Intent(AddReviewActivity.this, RankingActivity.class);
                AddReviewActivity.this.startActivity(rankingIntent);
            }
        });

        Intent i = getIntent();



//        final Integer id = i.getExtras().getInt("id");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Integer data = prefs.getInt("id", 1); //no id: default value
        mAPIService2 = ApiUtils.getAPIService();
        Call<ResponseBody> call = mAPIService2.getLoggedUsername();

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("Response", response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        try{
                            final String username = response.body().string();
                            mAPIService = ApiUtils.getAPIService();
                            addreview.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String title = reviewtitle.getText().toString().trim();
                                    String description = reviewdescription.getText().toString().trim();
                                    RequestBody title2 = RequestBody.create(MediaType.parse("text/plain"), title);
                                    RequestBody username2 = RequestBody.create(MediaType.parse("text/plain"), username);
                                    RequestBody description2 = RequestBody.create(MediaType.parse("text/plain"), description);
                                    Integer spinnerate = Integer.parseInt(spinner.getSelectedItem().toString());

                                    if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)){
                                        sendReview(title2, description2, spinnerate, username2, data);

                                    }
                                }
                            });
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
                Toast.makeText(AddReviewActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




//        addreview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = reviewtitle.getText().toString().trim();
//                String description = reviewdescription.getText().toString().trim();
//                RequestBody title2 = RequestBody.create(MediaType.parse("text/plain"), title);
//                RequestBody description2 = RequestBody.create(MediaType.parse("text/plain"), description);
//
//                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)){
//                    sendReview(title2, description2, username, data, spinner);
//
//                }
//            }
//        });

    }
    public void sendReview(RequestBody title, RequestBody description, Integer rate, RequestBody username, Integer gameid){
        mAPIService.addReview(title, description, rate, username, gameid).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                Log.i(TAG, "post sent to API" + response.body().toString());
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addItemsToSpinner(){
        spinner = (Spinner) findViewById(R.id.rewievratespinner);
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
//    Integer.valueOf(spinner.getSelectedItem());
}
