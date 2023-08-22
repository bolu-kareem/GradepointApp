package com.example.gradepointcalculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GPAResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activity);

        TextView textViewResult = findViewById(R.id.textViewResult);

        // Retrieve the GPA value passed from the GPAActivity
        float gpa = getIntent().getFloatExtra("GPA", 0.0f);

        // Display the GPA result
        textViewResult.setText("Your GPA: " + String.format("%.2f", gpa));

        // Perform any other operations or display additional information as needed
    }
}
