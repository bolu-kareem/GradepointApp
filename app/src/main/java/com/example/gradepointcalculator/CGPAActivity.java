package com.example.gradepointcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CGPAActivity extends AppCompatActivity {
    private EditText editTextSemesters;
    private Button buttonGenerateTable;
    private Button buttonCalculate;
    private TableLayout tableLayoutGPA;
    private List<EditText> gpaEditTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        editTextSemesters = findViewById(R.id.editTextSemesters);
        buttonGenerateTable = findViewById(R.id.buttonGenerateTable);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        tableLayoutGPA = findViewById(R.id.tableLayoutGPA);

        buttonGenerateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int semesters = Integer.parseInt(editTextSemesters.getText().toString());
                generateTable(semesters);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCGPA();
            }
        });
    }

    private void generateTable(int semesters) {
        tableLayoutGPA.removeAllViews();
        gpaEditTextList = new ArrayList<>();

        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 1; i <= semesters; i++) {
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.item_gpa, tableLayoutGPA, false);
            TextView textViewSemester = tableRow.findViewById(R.id.textViewSemester);
            EditText editTextGPA = tableRow.findViewById(R.id.editTextGPA);

            textViewSemester.setText("Semester " + i);
            gpaEditTextList.add(editTextGPA);

            tableLayoutGPA.addView(tableRow);
        }
    }

    private void calculateCGPA() {
        int totalSemesters = Integer.parseInt(editTextSemesters.getText().toString());
        float totalGPA = 0.0f;

        for (EditText editTextGPA : gpaEditTextList) {
            String gpaInput = editTextGPA.getText().toString().trim();

            if (!gpaInput.isEmpty()) {
                float gpa = Float.parseFloat(gpaInput);
                totalGPA += gpa;
            }
        }

        float cgpa = totalGPA / totalSemesters;

        Intent intent = new Intent(CGPAActivity.this, CGPAResultActivity.class);
        intent.putExtra("CGPA", cgpa);
        startActivity(intent);
    }
}
