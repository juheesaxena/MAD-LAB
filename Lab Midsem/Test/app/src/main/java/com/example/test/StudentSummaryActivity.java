package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentSummaryActivity extends AppCompatActivity {

    private TextView tvName, tvID, tvGender, tvCourse, tvSkills, tvExp, tvDate;
    private Button btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_summary);

        tvName = findViewById(R.id.tvNameSummary);
        tvID = findViewById(R.id.tvIDSummary);
        tvGender = findViewById(R.id.tvGenderSummary);
        tvCourse = findViewById(R.id.tvCourseSummary);
        tvSkills = findViewById(R.id.tvSkillsSummary);
        tvExp = findViewById(R.id.tvExpSummary);
        tvDate = findViewById(R.id.tvDateSummary);
        btnBackHome = findViewById(R.id.btnBackHome);

        // Receive Student object from Intent
        Student student = (Student) getIntent().getSerializableExtra("student");
        
        if (student != null) {
            tvName.setText(student.getName());
            tvID.setText(student.getId());
            tvGender.setText(student.getGender());
            tvCourse.setText(student.getCourse());
            tvSkills.setText(student.getSkills());
            tvExp.setText(student.getExperience());
            tvDate.setText(student.getDate());
        }

        btnBackHome.setOnClickListener(v -> {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        });
    }
}
