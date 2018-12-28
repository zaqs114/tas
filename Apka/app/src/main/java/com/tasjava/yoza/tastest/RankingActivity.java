package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

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
    }
}
