package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {
    private EditText taskEditText;
    private TextView dateText, timeText;
    private Button saveBtn;

    private String selectedDate = "", selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskEditText = findViewById(R.id.taskEditText);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        saveBtn = findViewById(R.id.saveTaskButton);

        dateText.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, y, m, d) -> {
                selectedDate = d + "/" + (m + 1) + "/" + y;
                dateText.setText(selectedDate);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        timeText.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(this, (view, hour, min) -> {
                selectedTime = String.format("%02d:%02d", hour, min);
                timeText.setText(selectedTime);
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        saveBtn.setOnClickListener(v -> {
            String task = taskEditText.getText().toString().trim();
            if (!task.isEmpty()) {
                Intent result = new Intent();
                result.putExtra("task", task);
                result.putExtra("date", selectedDate);
                result.putExtra("time", selectedTime);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
