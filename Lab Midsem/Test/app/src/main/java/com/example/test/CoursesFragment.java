package com.example.test;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        
        GridView gv = view.findViewById(R.id.gvFragmentCourses);
        EditText etNewCourse = view.findViewById(R.id.etNewCourse);
        Button btnAddCourse = view.findViewById(R.id.btnAddCourse);

        // Adapter for GridView
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, DataManager.courses);
        gv.setAdapter(adapter);

        // Add Course Logic
        btnAddCourse.setOnClickListener(v -> {
            String newCourse = etNewCourse.getText().toString().trim();
            if (!newCourse.isEmpty()) {
                if (!DataManager.courses.contains(newCourse)) {
                    DataManager.courses.add(newCourse);
                    adapter.notifyDataSetChanged();
                    etNewCourse.setText("");
                    Toast.makeText(getContext(), "Course Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Course already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Click on Course to show Registered Students
        gv.setOnItemClickListener((parent, view1, position, id) -> {
            String courseName = DataManager.courses.get(position);
            ArrayList<Student> registeredStudents = DataManager.getStudentsForCourse(courseName);

            showStudentsDialog(courseName, registeredStudents);
        });
        
        return view;
    }

    private void showStudentsDialog(String courseName, ArrayList<Student> students) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Students in " + courseName);

        if (students.isEmpty()) {
            builder.setMessage("No students registered for this course.");
        } else {
            // Create a simple list of names to display
            ArrayList<String> names = new ArrayList<>();
            for (Student s : students) {
                names.add(s.getName() + " (ID: " + s.getId() + ")");
            }

            ListView listView = new ListView(getContext());
            ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, names);
            listView.setAdapter(nameAdapter);
            builder.setView(listView);
        }

        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
