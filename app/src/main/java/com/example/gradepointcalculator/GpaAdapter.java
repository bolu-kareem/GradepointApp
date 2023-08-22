package com.example.gradepointcalculator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GpaAdapter extends RecyclerView.Adapter<GpaAdapter.GpaViewHolder> {
    private List<Double> gpaList;

    public GpaAdapter(List<Double> gpaList) {
        this.gpaList = gpaList;
    }

    @NonNull
    @Override
    public GpaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gpa, parent, false);
        return new GpaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GpaViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return gpaList.size();
    }

    public class GpaViewHolder extends RecyclerView.ViewHolder {
        private TextView semesterTextView;
        private EditText gpaEditText;

        public GpaViewHolder(@NonNull View itemView) {
            super(itemView);
            semesterTextView = itemView.findViewById(R.id.textViewSemester);
            gpaEditText = itemView.findViewById(R.id.editTextGPA);
        }

        public void bindData(int position) {
            int semesterNumber = position + 1;
            semesterTextView.setText("Semester " + semesterNumber);

            // Set the initial GPA value for each EditText
            Double gpa = gpaList.get(position);
            gpaEditText.setText(String.valueOf(gpa));

            // Update the GPA value in the list when EditText value changes
            gpaEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String gpaInput = gpaEditText.getText().toString().trim();
                    if (!gpaInput.isEmpty()) {
                        Double newGpa = Double.parseDouble(gpaInput);
                        gpaList.set(position, newGpa);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }
}
