package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        final ImageView homebutton = (ImageView) findViewById(R.id.homebutton);
        final ImageView profilebtn = (ImageView) findViewById(R.id.profilebtn);
        final ImageView supportbtn = (ImageView) findViewById(R.id.supportbtn);
        final ImageView rankingbtn = (ImageView) findViewById(R.id.rankingbtn);
        Spinner ratespinner = (Spinner) findViewById(R.id.rewievratespinner);

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

        ratespinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        List<Integer> rates = new ArrayList<Integer>();
        rates.add(1);
        rates.add(2);
        rates.add(3);
        rates.add(4);
        rates.add(5);

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, rates);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ratespinner.setAdapter(dataAdapter);


    }
}
