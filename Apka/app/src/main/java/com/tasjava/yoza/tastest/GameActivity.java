package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

    }
}
