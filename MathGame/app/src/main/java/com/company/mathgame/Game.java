package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;

    int number1;
    int number2;

    int userAnswer;
    int realAnswer;

    Random random = new Random();

    int userScore = 0;
    int userLife = 3;

    //==========================================================================

    CountDownTimer timer; //countdown object defined
    private static final long START_TIMER_IN_MILIS = 15000; //we define the total timer length, 'long' is used for long integers
    Boolean timer_running; //boolean to keep track whether the timer is running
    long time_left_in_milis = START_TIMER_IN_MILIS; //time left for timer to finish is initially the total time itself

    //==========================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonNext);

        gameContinue(); //calling this method that generates random numbers

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAnswer = Integer.valueOf(answer.getText().toString());

                pauseTimer();

                if (userAnswer == realAnswer)
                {

                    userScore = userScore + 10;
                    score.setText(""+userScore);

                    question.setText("Correct !"); //make it give an exclamation randomly - eg: that's awesome, that's right, good job

                }
                else
                {

                    userLife--;
                    life.setText(""+userLife);

                    question.setText("Oops, that's wrong..");

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer.setText("");

                resetTimer();

                if (userLife <= 0)
                {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Game.this, Result.class);
                    intent.putExtra("score", userScore);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    gameContinue();
                }

            }
        });

    }

    public void gameContinue()
    {
        number1 = random.nextInt(90) + 10;
        number2 = random.nextInt(90) + 10;

        realAnswer = number1 + number2;

        question.setText(number1 + " + " + number2);

        startTimer();
    }

    public void startTimer()
    {
        timer = new CountDownTimer(time_left_in_milis, 1000) { //this counts down by 1 second
            @Override
            public void onTick(long millisUntilFinished) {

                time_left_in_milis = millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {

                timer_running = false;
                pauseTimer();
                //when timer is finished, it must reset
                resetTimer();
                updateText();
                userLife--;
                life.setText(""+userLife);
                question.setText("Time's Up!");

            }
        }.start();

        timer_running = true;
    }

    public void updateText()
    {
        int second = (int)(time_left_in_milis/1000) % 60;
        String time_left = String.format(Locale.getDefault(),"%02d", second);
        time.setText(time_left);
    }

    public void pauseTimer()
    {
        timer.cancel();
        timer_running = false;
    }

    public void resetTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS; // set the timer back to 60seconds
        updateText(); //this can be removed right?
    }

}