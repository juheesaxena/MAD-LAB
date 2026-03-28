package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StudentsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        
        ListView lv = view.findViewById(R.id.lvFragmentStudents);
        
        // Since we changed the studentList in DataManager to contain Student objects, 
        // the fragment's ArrayAdapter must be updated to match.
        if (getActivity() != null) {
            ArrayAdapter<Student> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, DataManager.studentList);
            lv.setAdapter(adapter);

            // Added click listener so names in the fragment tab also show details
            lv.setOnItemClickListener((parent, view1, position, id) -> {
                Student selectedStudent = DataManager.studentList.get(position);
                Intent intent = new Intent(getActivity(), StudentSummaryActivity.class);
                intent.putExtra("student", selectedStudent);
                startActivity(intent);
            });
        }
        
        return view;
    }
}
