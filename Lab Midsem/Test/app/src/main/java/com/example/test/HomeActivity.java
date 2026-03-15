package com.example.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnRegister, btnViewStudents, btnCourses, btnDashboard, btnQuickActions, btnOpenWeb;
    private EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnRegister = findViewById(R.id.btnRegister);
        btnViewStudents = findViewById(R.id.btnViewStudents);
        btnCourses = findViewById(R.id.btnCourses);
        btnDashboard = findViewById(R.id.btnDashboard);
        btnQuickActions = findViewById(R.id.btnQuickActions);
        btnOpenWeb = findViewById(R.id.btnOpenWeb);
        etUrl = findViewById(R.id.etUrl);

        // Explicit Intent to RegisterStudentActivity
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, RegisterStudentActivity.class));
        });

        // Explicit Intent to StudentListActivity
        btnViewStudents.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, StudentListActivity.class));
        });

        // Explicit Intent to CoursesActivity
        btnCourses.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, CoursesActivity.class));
        });

        // Explicit Intent to DashboardTabsActivity
        btnDashboard.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, DashboardTabsActivity.class));
        });

        // Popup Menu Demonstration
        btnQuickActions.setOnClickListener(this::showPopupMenu);

        // Implicit Intent to open Website
        btnOpenWeb.setOnClickListener(v -> {
            String url = etUrl.getText().toString();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
        popup.show();
    }

    // Options Menu (App Bar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Menu Item Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
