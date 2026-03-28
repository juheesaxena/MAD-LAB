package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name,email;
    RadioGroup gender;
    CheckBox java,python;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        gender=findViewById(R.id.GenderGroup);
        java=findViewById(R.id.java);
        python=findViewById(R.id.python);

        Button next;
        next=findViewById(R.id.button1);

        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"Button clicked", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });

        Button t = findViewById(R.id.text1);
        registerForContextMenu(t);
    }

    // ✅ MOVE THIS OUTSIDE onCreate()
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.edit){
            Toast.makeText(this,"Edit clicked",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId()==R.id.Delete){
            Toast.makeText(this,"Delete clicked",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}