package com.example.a96jsa.chronos;

import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private boolean recording = false;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp);

        //spinner
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<String> categoriesArrayList = databaseHelper.getCategories();
        Spinner categoriesSpinner = (Spinner)findViewById(R.id.categories);
        ArrayAdapter<String> categoriesSpinnerAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,categoriesArrayList);
        categoriesSpinner.setAdapter(categoriesSpinnerAdapter);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();
                        if(id == R.id.dynamic_text_example){
                            Intent dynIntent = new Intent(getBaseContext(),ExampleDynamicText.class);
                            startActivity(dynIntent);
                        }else{
                            Intent homeIntent = new Intent(getBaseContext(),MainScreen.class);
                            startActivity(homeIntent);
                        }

                        mDrawerLayout.closeDrawers();
                        //Intent homeIntent = new Intent(getBaseContext(),MainScreen.class);
                        //startActivity(homeIntent);
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
