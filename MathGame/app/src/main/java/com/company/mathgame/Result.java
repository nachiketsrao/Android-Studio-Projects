package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView result;
    Button playAgain;
    Button exit;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.textViewResult);
        playAgain = findViewById(R.id.buttonPlayAgain);
        exit = findViewById(R.id.buttonExit);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        String userScore = String.valueOf(score);
        result.setText("Your Score : "+userScore);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish(); //it is necessary to close this activity after starting a new one, otherwise many activities will be running parallelly

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}