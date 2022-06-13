package com.example.dktpool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnStart);

        btn.setOnClickListener(view -> {
            Intent startQuiz = new Intent(MainActivity.this, QuizActivity.class);
           startActivity(startQuiz);

        });
    }
}