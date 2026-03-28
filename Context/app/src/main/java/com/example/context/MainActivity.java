package com.example.context;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button btn;

    String selectedOption = "Nothing Selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text1);
        btn = findViewById(R.id.btnContinue);

        registerForContextMenu(text);

        btn.setOnClickListener(v -> {

            // Pass data to next activity
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            i.putExtra("option", selectedOption);
            startActivity(i);
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.edit){
            selectedOption = "Edit";
            Toast.makeText(this,"Edit Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.delete){
            selectedOption = "Delete";
            Toast.makeText(this,"Delete Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId() == R.id.share){
            selectedOption = "Share";
            Toast.makeText(this,"Share Selected",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}