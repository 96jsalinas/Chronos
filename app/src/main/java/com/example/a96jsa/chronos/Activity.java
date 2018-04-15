package com.example.a96jsa.chronos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> activityList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listview);

        Intent intent = getIntent();
        activityList = intent.getStringArrayListExtra("Activities");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                activityList);

        listView.setAdapter(arrayAdapter);
    }
}