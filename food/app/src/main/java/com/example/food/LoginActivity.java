package com.example.food;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        db = new DBHelper(this);

        loginBtn.setOnClickListener(v -> {

            String e = email.getText().toString();
            String p = password.getText().toString();

            if(db.checkUser(e, p)) {

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, MenuActivity.class));

            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}