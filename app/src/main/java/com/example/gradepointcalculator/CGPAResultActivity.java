package com.example.gradepointcalculator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CGPAResultActivity extends AppCompatActivity {
    private TextView textViewCGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_result);

        // Retrieve the calculated CGPA from the intent
        float cgpa = getIntent().getFloatExtra("CGPA", 0.0f);

        textViewCGPA = findViewById(R.id.textViewCGPA);
        textViewCGPA.setText(String.format("Your CGPA: %.2f", cgpa));
    }
}
