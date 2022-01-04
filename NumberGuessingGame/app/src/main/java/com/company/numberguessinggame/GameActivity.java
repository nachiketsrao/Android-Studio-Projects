package com.company.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast, textViewRight, textViewHint;
    private Button buttonConfirm;
    private EditText editTextGuess;

    boolean twoDigit, threeDigit, fourDigit;

    Random r = new Random();
    int random;
    int remainingRight = 0;

    ArrayList<Integer> guessesList = new ArrayList<>();
    int userAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewHint = findViewById(R.id.textViewHint);
        textViewRight = findViewById(R.id.textViewRight);
        textViewLast = findViewById(R.id.textViewLast);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        editTextGuess = findViewById(R.id.editTextGuess);

        twoDigit = getIntent().getBooleanExtra("two", false);
        threeDigit = getIntent().getBooleanExtra("three", false);
        fourDigit = getIntent().getBooleanExtra("four", false);

        if (twoDigit)
        {
            random = r.nextInt(90)+10;
        }
        if (threeDigit)
        {
            random = r.nextInt(900)+100;
        }
        if (fourDigit)
        {
            random = r.nextInt(9000)+1000;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = editTextGuess.getText().toString();

                if (guess.equals(""))
                {
                    Toast.makeText(GameActivity.this, "Please enter a guess", Toast.LENGTH_LONG).show();
                }
                else
                {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);

                    userAttempts++;
                    remainingRight--;

                    int userGuess = Integer.parseInt(guess); //parseInt converts String object into integer type
                    guessesList.add(userGuess);

                    textViewLast.setText("Your previous guess was : "+guess);
                    textViewRight.setText("Your remaining chances : "+remainingRight);

                    if (random == userGuess)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations. The answer was "+random+
                                "\n \n You guessed the number in "+userAttempts
                        + " attempts. \n \n Your guesses : "+guessesList
                        + "\n \n Would you like to play again?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                //above kills all activities in the stack I started
                                System.exit(1); //terminates the current running JVM
                            }
                        });

                        builder.create().show();

                    }
                    if (random < userGuess)
                    {
                        textViewHint.setText("Decrease your Guess");
                    }
                    if (random > userGuess)
                    {
                        textViewHint.setText("Increase your Guess");
                    }

                    if (remainingRight == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry, you have exhausted all your chances "+
                                " \n \n The correct number was "+random
                                + " \n \n Your guesses : "+guessesList
                                + " \n \n Would you like to play again?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                //above kills all activities in the stack I started
                                System.exit(1); //terminates the current running JVM
                            }
                        });

                        builder.create().show();
                    }

                    editTextGuess.setText("");
                }
            }
        });
    }
}