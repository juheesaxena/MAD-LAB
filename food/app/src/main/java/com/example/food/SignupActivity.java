package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class SignupActivity extends AppCompatActivity {

    EditText email, password;
    Button signupBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signupBtn);

        db = new DBHelper(this);

        signupBtn.setOnClickListener(v -> {

            String e = email.getText().toString();
            String p = password.getText().toString();

            if(e.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            } else {

                db.insertUser(e, p);

                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();

                email.setText("");
                password.setText("");
            }
        });
    }
}