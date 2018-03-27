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
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean recording = false;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        Intent homeIntent = new Intent(getBaseContext(),MainScreen.class);
                        startActivity(homeIntent);
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


        final EditText category = findViewById(R.id.category);
        final Button button = findViewById(R.id.record);
        final Chronometer simpleChronometer = findViewById(R.id.simpleChronometer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!recording) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Starting recording in category: " + category.getText().toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    recording = true;
                    simpleChronometer.setBase(SystemClock.elapsedRealtime());
                    simpleChronometer.start();
                    button.setText("Stop recording");

                }else {
                    simpleChronometer.stop();
                    long elapsedMillis = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
                    Toast toast = Toast.makeText(getApplicationContext(), "Elapsed time (ms): " + elapsedMillis, Toast.LENGTH_SHORT);
                    toast.show();
                    recording = false;
                    button.setText("Start recording");
                }
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
