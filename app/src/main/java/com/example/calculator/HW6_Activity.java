package com.example.calculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HW6_Activity extends AppCompatActivity {
    private TextView resultTextView;
    private Button nextButton;
    private Spinner spinner;
    private ImageButton heartButton;
    private boolean isHeartPressed = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hw6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultTextView = findViewById(R.id.textResult);
        String result = getIntent().getStringExtra("result");
        resultTextView.setText(result);

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> finishAffinity());

        spinner = findViewById(R.id.categorySpinner);

        String[] items = {"Item 1", "Item 2", "Item 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                System.out.println("Selected: " + selectedItem);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        heartButton = findViewById(R.id.heartButton);
        heartButton.setOnClickListener(v -> {
            isHeartPressed = !isHeartPressed;

            if (isHeartPressed) {
                heartButton.setImageResource(R.drawable.heart_pressed);
            } else {
                heartButton.setImageResource(R.drawable.heart);
            }
        });
    }
}