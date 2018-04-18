package com.example.a96jsa.chronos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityTypeActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> activityList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_type);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listview);

        Intent intent = getIntent();
        activityList = intent.getStringArrayListExtra("Activities");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                activityList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                backToHome((String) listView.getItemAtPosition(i));
            }
        });

    }

    private void backToHome(String activity) {
        Intent intent = new Intent(this, RecordingActivity.class);
        intent.putExtra("Activity", activity);
        startActivity(intent);
    }
}
