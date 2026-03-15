package com.example.test;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CoursesActivity extends AppCompatActivity {

    private GridView gvCourses;
    private String[] courses = {"CSE", "IT", "ECE", "MECH", "AI", "DS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        gvCourses = findViewById(R.id.gvCourses);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        gvCourses.setAdapter(adapter);

        gvCourses.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Course: " + courses[position], Toast.LENGTH_SHORT).show();
        });
    }
}
