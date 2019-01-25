package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import data.model.Games;
import data.model.remote.APIService;
import data.model.remote.ApiUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    ImageView gameicon;
    TextView gametitle, gamescore, gamegenre, gameplatform, gamepublisher, gamelaunchdate, gamedescription;

    private APIService mAPIService;
    private static final String TAG = "GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "onCreate: started.");

        final ImageView homebutton = (ImageView) findViewById(R.id.homebutton);
        final ImageView profilebtn = (ImageView) findViewById(R.id.profilebtn);
        final ImageView supportbtn = (ImageView) findViewById(R.id.supportbtn);
        final ImageView addreviewbtn = (ImageView) findViewById(R.id.addreviewbtn);


        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(GameActivity.this, MainActivity.class);
                GameActivity.this.startActivity(mainIntent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(GameActivity.this, ProfileActivity.class);
                GameActivity.this.startActivity(profileIntent);
            }
        });

        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(GameActivity.this, SupportActivity.class);
                GameActivity.this.startActivity(supportIntent);
            }
        });

        addreviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReviewIntent = new Intent(GameActivity.this, AddReviewActivity.class);
                GameActivity.this.startActivity(addReviewIntent);
            }
        });

        Intent i = getIntent();

        final String title = i.getExtras().getString("title");
        final String icon = i.getExtras().getString("icon");
        final Integer id = i.getExtras().getInt("id");

//        getGameDetail()


        gametitle = (TextView) findViewById(R.id.gametitle);
        gamedescription = (TextView) findViewById(R.id.gamedescription);
        gamegenre = (TextView) findViewById(R.id.gamegenre);
        gamelaunchdate = (TextView) findViewById(R.id.gamelaunchdate);
        gamescore = (TextView) findViewById(R.id.gamescore);
        gameplatform = (TextView) findViewById(R.id.gameplatform);
        gamepublisher = (TextView) findViewById(R.id.gamepublisher);

        gametitle.setText(title);

        mAPIService = ApiUtils.getAPIService();
        Call<Games> call = mAPIService.getGameDetail(id);

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Games>() {
            @Override
            public void onResponse(Call<Games> call, Response<Games> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        try{
                            gamedescription.setText(response.body().getDescription());
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
            public void onFailure(Call<Games> call, Throwable t) {
                    Toast.makeText(GameActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
        });

    }


}
