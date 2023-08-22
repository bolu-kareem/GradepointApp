package com.example.gradepointcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GPAActivity extends AppCompatActivity {
    private EditText editTextCourses;
    private EditText editTextUnits;
    private TableLayout tableLayoutCourses;
    private Button buttonGenerateTable;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        // Initialize the views
        editTextCourses = findViewById(R.id.editTextCourses);
        editTextUnits = findViewById(R.id.editTextUnits);
        tableLayoutCourses = findViewById(R.id.tableLayoutCourses);
        buttonGenerateTable = findViewById(R.id.buttonGenerateTable);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        // Set a click listener for the Generate Table button
        buttonGenerateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateTable();
            }
        });

        // Set a click listener for the Calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGPA();
            }
        });
    }

    private void generateTable() {
        // Retrieve the number of courses entered by the user
        int numCourses = Integer.parseInt(editTextCourses.getText().toString());

        // Clear the existing table rows
        tableLayoutCourses.removeAllViews();

        // Create table rows dynamically based on the number of courses
        for (int i = 0; i < numCourses; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            row.setLayoutParams(params);

            TextView textViewCourseGradeLabel = new TextView(this);
            textViewCourseGradeLabel.setText("Course Grade:");
            TextView textViewCourseUnitLabel = new TextView(this);
            textViewCourseUnitLabel.setText("Course Unit:");

            EditText editTextCourseGrade = new EditText(this);
            editTextCourseGrade.setHint("Enter Grade");
            EditText editTextCourseUnit = new EditText(this);
            editTextCourseUnit.setHint("Enter Units");

            // Add the TextViews and EditTexts to the table row
            row.addView(textViewCourseGradeLabel);
            row.addView(editTextCourseGrade);
            row.addView(textViewCourseUnitLabel);
            row.addView(editTextCourseUnit);

            // Add the table row to the table layout
            tableLayoutCourses.addView(row);
        }
    }

    private void calculateGPA() {
        // Retrieve the number of courses entered by the user
        int numCourses = Integer.parseInt(editTextCourses.getText().toString());

        // Variables to hold the total grade points and total units
        int totalGradePoints = 0;
        int totalUnits = 0;

        // Iterate through each table row and calculate the grade points and units
        for (int i = 0; i < numCourses; i++) {
            TableRow row = (TableRow) tableLayoutCourses.getChildAt(i);

            EditText editTextCourseGrade = (EditText) row.getChildAt(1);
            EditText editTextCourseUnit = (EditText) row.getChildAt(3);

            String courseGrade = editTextCourseGrade.getText().toString().trim();
            int courseUnit = Integer.parseInt(editTextCourseUnit.getText().toString().trim());

            // Calculate grade points based on the grade entered
            int gradePoints = calculateGradePoints(courseGrade);

            // Add the grade points and units to the totals
            totalGradePoints += (gradePoints * courseUnit);
            totalUnits += courseUnit;
        }

        // Calculate the GPA
        float gpa = (float) totalGradePoints / totalUnits;

        // Create an Intent to start the GPAResultActivity and pass the GPA value
        Intent intent = new Intent(GPAActivity.this, GPAResultActivity.class);
        intent.putExtra("GPA", gpa);
        startActivity(intent);
    }

    private int calculateGradePoints(String grade) {
        int gradePoints = 0;
        switch (grade) {
            case "A":
                gradePoints = 5;
                break;
            case "B":
                gradePoints = 4;
                break;
            case "C":
                gradePoints = 3;
                break;
            case "D":
                gradePoints = 2;
                break;
            case "F":
                gradePoints = 0;
                break;
        }
        return gradePoints;
    }
}
