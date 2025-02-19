package com.taj.smd.a1.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;


@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity
{
    DecimalFormat df = new DecimalFormat("0.##########");

    TextView tvDisplay, tvResult;

    // State
    double PreviousNumber = 0; // previous number
    double number = 0; // current number
    void setNumber(double num)
    {
        number = num;
        numberStr = df.format(number);
        tvResult.setText(numberStr);
    }
    String numberStr = ""; // current number as string
    void appendNum(int num)
    {
        numberStr += num;
        setNumber(Double.parseDouble(numberStr));

        if (PreviousNumber != 0)
            tvDisplay.setText(df.format(PreviousNumber) + " " + operator);
    }
    String operator = ""; // current operator
    void setOperator(String op)
    {
//         If there is a previous number and current number, calculate the result first
        if(PreviousNumber != 0 && number != 0)
            handleEqualClick();

        operator = op;
        if (PreviousNumber == 0)
            PreviousNumber = number;

        setNumber(0);
        tvResult.setText("");
        tvDisplay.setText(df.format(PreviousNumber )+ " " + operator);
    }

    void handleEqualClick()
    {
        if (operator.isEmpty())
            return;

        if(PreviousNumber == 0 || number == 0)
            return;


        double result = 0;
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
        tvDisplay.setText(df.format(PreviousNumber) + " " + operator + " " + df.format(number) + " =");
        setNumber(result);
        PreviousNumber = 0;
        operator = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
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
        findViewById(R.id.num0).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(0);});
        findViewById(R.id.num1).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(1);});
        findViewById(R.id.num2).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(2);});
        findViewById(R.id.num3).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(3);});
        findViewById(R.id.num4).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(4);});
        findViewById(R.id.num5).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(5);});
        findViewById(R.id.num6).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(6);});
        findViewById(R.id.num7).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(7);});
        findViewById(R.id.num8).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(8);});
        findViewById(R.id.num9).setOnClickListener(v -> {v.performHapticFeedback(1);appendNum(9);});

        // Set onClickListener for operator buttons
        findViewById(R.id.plus).setOnClickListener(v -> {v.performHapticFeedback(1);setOperator("+");});
        findViewById(R.id.minus).setOnClickListener(v -> {v.performHapticFeedback(1);setOperator("-");});
        findViewById(R.id.multiply).setOnClickListener(v -> {v.performHapticFeedback(1);setOperator("*");});
        findViewById(R.id.divide).setOnClickListener(v -> {v.performHapticFeedback(1);setOperator("/");});

        // Set onClickListener for Advanced buttons
        findViewById(R.id.square).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            if (number != 0)
            {
                number *= number;
                tvResult.setText(df.format(number));
            }
        });

        findViewById(R.id.sqrt).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            if (number != 0)
            {
                setNumber(Math.sqrt(number));
            }
        });

        findViewById(R.id.percent).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            if (number != 0)
            {
                setNumber(PreviousNumber * number / 100);
            }
        });

        findViewById(R.id.negate).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            if (number != 0)
            {
                setNumber(-number);
            }
        });

        findViewById(R.id.inverse).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            if (number != 0)
            {
                setNumber(1 / number);
            }
        });

        findViewById(R.id.dot).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            if (!numberStr.contains("."))
            {
                numberStr += ".";
                tvResult.setText(numberStr);
            }
        });

        // Set onClickListener for clear button
        findViewById(R.id.clear).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            tvDisplay.setText("");
            setNumber(0);
            operator = "";
            PreviousNumber = 0;
        });

        findViewById(R.id.clearEntry).setOnClickListener(v -> {
            v.performHapticFeedback(1);
            setNumber(0);
        });

        // Set onClickListener for backspace button
        findViewById(R.id.backspace).setOnClickListener(v ->
        {
            v.performHapticFeedback(1);
            if (numberStr.length() > 0)
            {
                numberStr = numberStr.substring(0, numberStr.length() - 1);
                if (numberStr.length() > 0)
                    setNumber(Double.parseDouble(numberStr));
                else
                    setNumber(0);
            }
        });

        // Set onClickListener for equal button
        findViewById(R.id.equal).setOnClickListener(v -> {v.performHapticFeedback(1);handleEqualClick();});

    }
}