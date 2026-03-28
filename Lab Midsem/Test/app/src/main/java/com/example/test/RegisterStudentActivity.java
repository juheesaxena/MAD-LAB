package com.example.test;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class RegisterStudentActivity extends AppCompatActivity {

    private EditText etName, etID, etEmail;
    private RadioGroup rgGender;
    private Spinner spinnerCourse;
    private CheckBox cbJava, cbPython, cbAndroid;
    private SeekBar seekBarExp;
    private TextView tvExperience;
    private ToggleButton toggleMode;
    private Switch switchHostel;
    private Button btnPickDate, btnPickTime, btnSubmit, btnReset;

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        etEmail = findViewById(R.id.etEmail);
        rgGender = findViewById(R.id.rgGender);
        spinnerCourse = findViewById(R.id.spinnerCourse);
        cbJava = findViewById(R.id.cbJava);
        cbPython = findViewById(R.id.cbPython);
        cbAndroid = findViewById(R.id.cbAndroid);
        seekBarExp = findViewById(R.id.seekBarExp);
        tvExperience = findViewById(R.id.tvExperience);
        toggleMode = findViewById(R.id.toggleMode);
        switchHostel = findViewById(R.id.switchHostel);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnPickTime = findViewById(R.id.btnPickTime);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);

        // Spinner Setup using the dynamic DataManager.courses list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DataManager.courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapter);
        
        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RegisterStudentActivity.this, "Course: " + DataManager.courses.get(position), Toast.LENGTH_SHORT).show();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        seekBarExp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvExperience.setText("Programming Experience (0-10): " + progress);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnPickDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, y, m, d) -> {
                selectedDate = d + "/" + (m + 1) + "/" + y;
                Toast.makeText(this, "Date: " + selectedDate, Toast.LENGTH_SHORT).show();
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnPickTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(this, (view, h, min) -> {
                selectedTime = h + ":" + min;
                Toast.makeText(this, "Time: " + selectedTime, Toast.LENGTH_SHORT).show();
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        btnSubmit.setOnClickListener(v -> submitData());
        btnReset.setOnClickListener(v -> resetForm());
    }

    private void submitData() {
        String name = etName.getText().toString();
        String id = etID.getText().toString();
        if (name.isEmpty() || id.isEmpty()) {
            Toast.makeText(this, "Please enter Name and ID", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = rgGender.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selectedId);
        String gender = (rb != null) ? rb.getText().toString() : "Not Selected";

        StringBuilder skills = new StringBuilder();
        if (cbJava.isChecked()) skills.append("Java ");
        if (cbPython.isChecked()) skills.append("Python ");
        if (cbAndroid.isChecked()) skills.append("Android");

        String expStr = String.valueOf(seekBarExp.getProgress());
        String dateStr = selectedDate.isEmpty() ? "Not Set" : selectedDate;
        String courseStr = spinnerCourse.getSelectedItem().toString();

        Student newStudent = new Student(name, id, gender, courseStr, skills.toString(), expStr, dateStr);
        DataManager.studentList.add(newStudent);

        Toast.makeText(this, "Student Registered Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, StudentSummaryActivity.class);
        intent.putExtra("student", newStudent);
        startActivity(intent);
    }

    private void resetForm() {
        etName.setText("");
        etID.setText("");
        etEmail.setText("");
        rgGender.clearCheck();
        cbJava.setChecked(false);
        cbPython.setChecked(false);
        cbAndroid.setChecked(false);
        seekBarExp.setProgress(0);
        switchHostel.setChecked(false);
        Toast.makeText(this, "Form Reset", Toast.LENGTH_SHORT).show();
    }
}
