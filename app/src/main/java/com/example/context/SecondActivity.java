package com.example.context;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView result = findViewById(R.id.resultText);

        // Get data from intent
        String data = getIntent().getStringExtra("option");

        result.setText("Selected Option: " + data);
    }
}