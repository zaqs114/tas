package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        final ImageView homebutton = (ImageView) findViewById(R.id.homebutton);
        final ImageView profilebtn = (ImageView) findViewById(R.id.profilebtn);
        final ImageView supportbtn = (ImageView) findViewById(R.id.supportbtn);
        final ImageView rankingbtn = (ImageView) findViewById(R.id.rankingbtn);

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
    }
}
