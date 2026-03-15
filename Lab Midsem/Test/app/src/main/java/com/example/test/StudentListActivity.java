package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class StudentListActivity extends AppCompatActivity {

    private ListView lvStudents;
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        lvStudents = findViewById(R.id.lvStudents);

        // Using Student object adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DataManager.studentList);
        lvStudents.setAdapter(adapter);

        if (DataManager.studentList.isEmpty()) {
            Toast.makeText(this, "No students registered yet", Toast.LENGTH_SHORT).show();
        }

        // Click Listener: When a student is clicked, show their full details in StudentSummaryActivity
        lvStudents.setOnItemClickListener((parent, view, position, id) -> {
            Student selectedStudent = DataManager.studentList.get(position);
            Intent intent = new Intent(StudentListActivity.this, StudentSummaryActivity.class);
            intent.putExtra("student", selectedStudent);
            startActivity(intent);
        });

        // Register for Context Menu
        registerForContextMenu(lvStudents);
    }

    // Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Action");
        menu.add(0, v.getId(), 0, "Delete Student");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle().equals("Delete Student")) {
            DataManager.studentList.remove(info.position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
