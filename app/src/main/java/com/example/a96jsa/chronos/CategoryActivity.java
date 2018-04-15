package com.example.a96jsa.chronos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listview);

        final ArrayList<String> categoryList = databaseHelper.getCategories();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                categoryList);
        listView.setAdapter(arrayAdapter);
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


             ArrayList<String> activitiyList = databaseHelper.showPossibleActivities((String) listView.getItemAtPosition(i));

                 showActivities(activitiyList);
             }


     });


    }

    public void showActivities (ArrayList<String> arrayList){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                arrayList);

        listView.setAdapter(arrayAdapter);


    }

}
