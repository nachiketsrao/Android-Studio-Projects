package com.company.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9
                   , btnAC, btnDel, btnPlus, btnMinus, btnDivide, btnMulti, btnEquals, btnDot;

    private TextView textViewResult, textViewHistory;

    private String number = null; //we can check with this whether the textViewResult is empty or not

    double firstNumber = 0;
    double lastNumber = 0;

    String status = null;
    boolean operator = false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String history, currentResult;

    boolean dot = true;
    boolean btnEqualsControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnMulti = findViewById(R.id.btnMulti);

        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnDot = findViewById(R.id.btnDot);
        btnEquals = findViewById(R.id.btnEquals);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("9");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = null;
                status = null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNumber = 0;
                lastNumber = 0;
                dot = true;

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number != null && number.length() > 0)
                {
                    if (number.charAt(number.length()-1) == ".".charAt(0))
                    {
                        dot = true;
                    }
                    number = number.substring(0, number.length()-1);

                    if (number.length() == 0)
                    {
                        textViewResult.setText("0");
                    }
                    else
                    {
                        textViewResult.setText(number);
                    }

                }


            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"+");

                if (operator)
                {
                    if (status == "multiplication")
                    {
                        multiply();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else
                    {
                        plus();
                    }
                }

                status = "sum";
                operator = false;
                number = null;

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"-");

                if (operator)
                {
                    if (status == "multiplication")
                    {
                        multiply();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else if (status == "sum")
                    {
                        plus();
                    }
                    else
                    {
                        minus();
                    }
                }

                status = "subtraction";
                operator = false;
                number = null;

            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"X");

                if (operator)
                {
                    if (status == "sum")
                    {
                        plus();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else
                    {
                        multiply();
                    }
                }

                status = "multiplication";
                operator = false;
                number = null;

            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"/");

                if (operator)
                {
                    if (status == "multiplication")
                    {
                        multiply();
                    }
                    else if (status == "sum")
                    {
                        plus();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else
                    {
                        divide();
                    }
                }

                status = "division";
                operator = false;
                number = null;

            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (operator)
                {
                    if (status == "sum")
                    {
                        plus();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else if (status == "multiplication")
                    {
                        multiply();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else
                    {
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                    }
                }

                operator = false;
                btnEqualsControl = true;

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dot)
                {
                    if (number == null)
                    {
                        number = "0.";
                    }
                    else
                    {
                        number = number + ".";
                    }
                }

                textViewResult.setText(number);
                dot = false;

            }
        });



    }

    public void numberClick(String view)
    {
        if (number == null)
        {
            number = view;
        }
        else if (btnEqualsControl)
        {
            firstNumber = 0;
            lastNumber = 0;
            number = view;
        }
        else
        {
            number = number + view;
        }

        textViewResult.setText(number);
        operator = true;
        btnEqualsControl = false;
    }

    public void plus()
    {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber + lastNumber;

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;

    }

    public void minus()
    {
        if (firstNumber == 0)
        {
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        }
        else
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber - lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void multiply()
    {
        if (firstNumber == 0)
        {
            firstNumber = 1;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        else
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void divide()
    {
        if (firstNumber == 0)
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = lastNumber / 1;
        }
        else
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }



}