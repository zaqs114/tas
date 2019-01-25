package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.GamesAdapter;
import data.model.Games;
import data.model.GamesList;
import data.model.remote.APIService;
import data.model.remote.ApiUtils;
import data.model.remote.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIService mAPIService;
    private GamesAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAPIService = ApiUtils.getAPIService();

        final ImageView homebutton = (ImageView) findViewById(R.id.homebutton);
        final ImageView profilebtn = (ImageView) findViewById(R.id.profilebtn);
        final ImageView rankingbtn = (ImageView) findViewById(R.id.rankingbtn);
        final ImageView supportbtn = (ImageView) findViewById(R.id.supportbtn);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_games_list);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(homeIntent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                MainActivity.this.startActivity(profileIntent);
            }
        });

        rankingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingIntent = new Intent(MainActivity.this, RankingActivity.class);
                MainActivity.this.startActivity(rankingIntent);
            }
        });

        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(MainActivity.this, SupportActivity.class);
                MainActivity.this.startActivity(supportIntent);
            }
        });

        Call<List<Games>> call = mAPIService.getGamesData();

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Games>>() {
            @Override
            public void onResponse(Call<List<Games>> call, Response<List<Games>> response) {
                ArrayList<Games> gamesList = (ArrayList<Games>) response.body();
                generateGamesList(gamesList);
            }

            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    /** Method to generate List of games using RecyclerView with custom adapter*/
    private void generateGamesList(ArrayList<Games> gamesArrayList) {
        recyclerView = findViewById(R.id.recycler_view_games_list);
        adapter = new GamesAdapter(this, gamesArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}

