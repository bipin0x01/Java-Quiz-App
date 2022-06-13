package com.example.dktpool;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView rInfo = findViewById(R.id.resultInfo);
        TextView rDetails = findViewById(R.id.resultdetail);

        Bundle bundle = getIntent().getExtras();

// getting the string back
        String score = bundle.getString("totalScore", "0");
        int finalScore = Integer.parseInt(score);

        if (finalScore >= 6){
            rInfo.setTextColor(Color.GREEN);
            rDetails.setTextColor(Color.GREEN);
            rInfo.setText("Congratulations!");
            rDetails.setText("You have successfully passed the test.");
        } else  {

            rInfo.setTextColor(Color.RED);
            rDetails.setTextColor(Color.RED);
            rInfo.setText("Sorry!");
            rDetails.setText("You need some more practice.");
        }

        Button retake;
        retake = findViewById(R.id.btnRetake);
        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Go to main page
                Intent retake = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(retake);
            }
        });

    }



}