package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView gameslist;

        final ImageView homebutton = (ImageView) findViewById(R.id.homebutton);
        final ImageView profilebtn = (ImageView) findViewById(R.id.profilebtn);
        final ImageView rankingbtn = (ImageView) findViewById(R.id.rankingbtn);
        final ImageView supportbtn = (ImageView) findViewById(R.id.supportbtn);

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

    }
}
