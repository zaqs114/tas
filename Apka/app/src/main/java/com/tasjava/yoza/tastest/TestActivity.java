package com.tasjava.yoza.tastest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final ImageView rankingbtn = (ImageView) findViewById(R.id.rankingbtn);

        rankingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingIntent = new Intent(TestActivity.this, MainActivity.class);
                TestActivity.this.startActivity(rankingIntent);
            }
        });
    }
}
