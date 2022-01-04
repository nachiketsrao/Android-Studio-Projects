package com.company.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private RadioButton radio1, radio2, radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);

                if (!radio1.isChecked() && !radio2.isChecked() && !radio3.isChecked())
                {
                    // show a snackbar message
                    Snackbar.make(v, "Please select the number of digits", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if (radio1.isChecked())
                    {
                        intent.putExtra("one", true);
                    }
                    if (radio2.isChecked())
                    {
                        intent.putExtra("two", true);
                    }
                    if (radio3.isChecked())
                    {
                        intent.putExtra("three", true);
                    }

                    startActivity(intent);
                }
            }
        });
    }
}