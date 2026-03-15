package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatisticsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        
        TextView tvTotalCount = view.findViewById(R.id.tvTotalStudentsCount);
        TextView tvActiveCourses = view.findViewById(R.id.tvActiveCoursesCount);
        TextView tvNewAdmissions = view.findViewById(R.id.tvNewAdmissionsCount);
        
        // Dynamically set the student count from the global DataManager list
        int studentCount = DataManager.studentList.size();
        tvTotalCount.setText(String.valueOf(studentCount));
        tvNewAdmissions.setText(String.valueOf(studentCount));
        
        // Dynamically set the course count from the global DataManager list
        int courseCount = DataManager.courses.size();
        tvActiveCourses.setText(String.valueOf(courseCount));
        
        return view;
    }
}
