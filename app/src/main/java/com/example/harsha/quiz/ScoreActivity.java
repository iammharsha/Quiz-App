package com.example.harsha.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0);
        TextView sc=(TextView) findViewById(R.id.score);

        sc.setText((score+""));

    }
}
