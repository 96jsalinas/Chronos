package com.example.a96jsa.chronos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText category = findViewById(R.id.category);
        final Button button = findViewById(R.id.record);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Category: "+category.getText().toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }



}
