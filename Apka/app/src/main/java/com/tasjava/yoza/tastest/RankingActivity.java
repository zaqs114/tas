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
import adapter.RankingAdapter;
import data.model.Games;
import data.model.remote.APIService;
import data.model.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {

    private APIService mAPIService;
    private RankingAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        mAPIService = ApiUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_games_list_ranking);

        final ImageView homebutton = (ImageView) findViewById(R.id.homebutton);
        final ImageView profilebtn = (ImageView) findViewById(R.id.profilebtn);
        final ImageView supportbtn = (ImageView) findViewById(R.id.supportbtn);
        final ImageView addgamebtn = (ImageView) findViewById(R.id.addgamebtn);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(RankingActivity.this, MainActivity.class);
                RankingActivity.this.startActivity(mainIntent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(RankingActivity.this, ProfileActivity.class);
                RankingActivity.this.startActivity(profileIntent);
            }
        });

        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(RankingActivity.this, SupportActivity.class);
                RankingActivity.this.startActivity(supportIntent);
            }
        });

        addgamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addGameIntent = new Intent(RankingActivity.this, AddGameActivity.class);
                RankingActivity.this.startActivity(addGameIntent);
            }
        });
        Call<List<Games>> call = mAPIService.getGamesRanking();

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Games>>() {
            @Override
            public void onResponse(Call<List<Games>> call, Response<List<Games>> response) {
                ArrayList<Games> gamesList = (ArrayList<Games>) response.body();
                generateGamesList(gamesList);
            }

            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                Toast.makeText(RankingActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        /** Method to generate List of games using RecyclerView with custom adapter*/

    }
    private void generateGamesList(ArrayList<Games> gamesArrayList) {
        recyclerView = findViewById(R.id.recycler_view_games_list_ranking);
        adapter = new RankingAdapter(gamesArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RankingActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
