package com.taj.smd.a1.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity
{
    TextView tvDisplay, tvResult;

    // State
    int PreviousNumber = 0; // previous number
    int number = 0; // current number
    void setNumber(int num)
    {
        number = number * 10 + num;

        if (PreviousNumber != 0)
            tvDisplay.setText(PreviousNumber + " " + operator);
        tvResult.setText(String.valueOf(number));
    }
    String operator = ""; // current operator
    void setOperator(String op)
    {
        if(PreviousNumber != 0 && number != 0)
            handleEqualClick();

        operator = op;
        if (PreviousNumber == 0)
            PreviousNumber = number;
        number = 0;
        tvDisplay.setText(PreviousNumber + " " + operator);
    }

    void handleEqualClick()
    {
        int result = 0;
        switch (operator)
        {
            case "+":
                result = PreviousNumber + number;
                break;
            case "-":
                result = PreviousNumber - number;
                break;
            case "*":
                result = PreviousNumber * number;
                break;
            case "/":
                result = PreviousNumber / number;
                break;
        }
        tvDisplay.setText(PreviousNumber + " " + operator + " " + number + " =");
        tvResult.setText(String.valueOf(result));
        number = result;
        PreviousNumber = 0;
        operator = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDisplay = findViewById(R.id.tvDisplay);
        tvResult = findViewById(R.id.tvResult);

        // Set onClickListener for number buttons
        findViewById(R.id.num0).setOnClickListener(v -> setNumber(0));
        findViewById(R.id.num1).setOnClickListener(v -> setNumber(1));
        findViewById(R.id.num2).setOnClickListener(v -> setNumber(2));
        findViewById(R.id.num3).setOnClickListener(v -> setNumber(3));
        findViewById(R.id.num4).setOnClickListener(v -> setNumber(4));
        findViewById(R.id.num5).setOnClickListener(v -> setNumber(5));
        findViewById(R.id.num6).setOnClickListener(v -> setNumber(6));
        findViewById(R.id.num7).setOnClickListener(v -> setNumber(7));
        findViewById(R.id.num8).setOnClickListener(v -> setNumber(8));
        findViewById(R.id.num9).setOnClickListener(v -> setNumber(9));

        // Set onClickListener for operator buttons
        findViewById(R.id.plus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.minus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.multiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.divide).setOnClickListener(v -> setOperator("/"));

        // Set onClickListener for clear button
        findViewById(R.id.clear).setOnClickListener(v -> {
            tvDisplay.setText("");
            number = 0;
            operator = "";
            PreviousNumber = 0;
            tvResult.setText("");
        });

        // Set onClickListener for backspace button
        findViewById(R.id.backspace).setOnClickListener(v ->
        {
            if (number != 0)
            {
                number /= 10;
                tvDisplay.setText(String.valueOf(number));
                tvResult.setText(String.valueOf(number));
            }
        });

        // Set onClickListener for equal button
        findViewById(R.id.equal).setOnClickListener(v -> handleEqualClick());

    }
}