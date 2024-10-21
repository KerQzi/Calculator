package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView inputField;
    private double num1, num2, result;
    private String operator;
    private boolean isCalculated = false;
    private Button buttonToNewActivity;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputField = findViewById(R.id.textView);
        buttonToNewActivity = findViewById(R.id.btToNewActivity);

        buttonToNewActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HW6_Activity.class);
            intent.putExtra("result", doubleOrInt(result));
            startActivity(intent);
        });
    }


    public void onClick(View v) {
        String text = ((MaterialButton)v).getText().toString();
        buttonToNewActivity.setVisibility(View.GONE);

        switch (text) {
            case "AC":
                inputField.setText("0");
                num1 = 0;
                num2 = 0;
                break;
            case "+/-":
                toggleSign();
                break;
            case "%":
                percent();
                break;
            case "=":
                calculate();
                break;
            case "X":
            case "+":
            case "-":
            case "/":
                String currentText = inputField.getText().toString();
                if (!currentText.isEmpty()) {
                    num1 = Double.parseDouble(inputField.getText().toString());
                    operator = text;
                    inputField.setText("");
                    isCalculated = false;
                } else {
                    inputField.setText("0");
                }
                break;
            case ".":
                if (!inputField.getText().toString().contains(".")) {
                    inputField.append(".");
                }
                break;
//            case "BUTTON":
//                Intent intent = new Intent(MainActivity.this, HW6_Activity.class);
//                intent.putExtra("result", result);
//                startActivity(intent);
            default:
                if (!isOperator(text.charAt(text.length() - 1))){
                    if (inputField.getText().toString().equals("0")) {
                        inputField.setText(text);
                    } else {
                        inputField.append(text);
                }
            }
        }
    }

    private boolean isOperator(char character) {
        return character == '+' || character == '-' || character == 'X' || character == '/' || character == '=' ||
                character == '%';
    }

    private void calculate() {
        String currentText = inputField.getText().toString();
        if (!currentText.isEmpty() && num1 > 0) {
            if (!isCalculated) {
                num2 = Double.parseDouble(currentText);

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "X":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            inputField.setText("Error");
                            return;
                        }
                        break;
                }

                inputField.setText(doubleOrInt(result));
                isCalculated = true;
                buttonToNewActivity.setVisibility(View.VISIBLE);
            }
        }
    }

    private void percent() {
        String currentText = inputField.getText().toString();
        if (!currentText.equals("0")) {
            double currentValue = Double.parseDouble(currentText);
            currentValue = currentValue / 100;
            inputField.setText(String.valueOf(currentValue));
        }
    }

    private void toggleSign() {
        String currentText = inputField.getText().toString();
            double currentValue = Double.parseDouble(currentText);
            currentValue = currentValue * -1;
            inputField.setText(doubleOrInt(currentValue));
    }

    private String doubleOrInt(double number){
        if (number == (long) number) {
            return String.valueOf((long) number);
        } else {
            return String.valueOf(number);
        }
    }
}