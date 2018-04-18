package com.example.a96jsa.chronos;

import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecordingActivity extends AppCompatActivity {

    private boolean recording = false;
    private DrawerLayout mDrawerLayout;
    private DatabaseHelper databaseHelper;
    private Button storeButton;
    private String buffer;
    private Button showCategories;
    public String starTime;
    public String endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        databaseHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp);


        mDrawerLayout = findViewById(R.id.drawer_layout);


        Intent intent = getIntent();
        final String activityType = intent.getExtras().getString("Activity");
        final TextView textView = findViewById(R.id.textViewToRecord);
        textView.setText(intent.getExtras().getString("Activity"));

        final Button button = findViewById(R.id.record);
        final Chronometer simpleChronometer = findViewById(R.id.simpleChronometer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!recording) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Starting recording: " + activityType, Toast.LENGTH_SHORT);
                    toast.show();
                    recording = true;
                    simpleChronometer.setBase(SystemClock.elapsedRealtime());
                    simpleChronometer.start();
                    button.setText("Stop recording");
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss");
                    starTime = timeDateFormat.format(calendar.getTime());

                }else {
                    simpleChronometer.stop();
                    long elapsedMillis = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss");
                    endTime = timeDateFormat.format(calendar.getTime());
                    //Stuff to enter into activity table, will be extracted into separate java class soon
                    String totalTime = Long.toString(elapsedMillis);
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);
                    // Toast toast = Toast.makeText(getApplicationContext(), "Elapsed time (ms): " + elapsedMillis, Toast.LENGTH_SHORT);
                    // toast.show();

                    boolean check = databaseHelper.insertActivityData(activityType, totalTime, starTime, endTime, formattedDate, "RED");
                    if (check == true){
                        Toast toast = Toast.makeText(getApplicationContext(), "Stored activity details: " + activityType+" "+totalTime+" "+formattedDate + " Start time: "+starTime+" End time: "+endTime, Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Upps, there went something wrong!", Toast.LENGTH_LONG);
                        toast.show();
                    }

                    recording = false;
                    button.setText("Start recording");
                }
            }
        });





    }

    private void storeData(String totalTime, String formattedDate) {
        Intent intent = getIntent();
        String activityType = intent.getExtras().getString("Activity");


        boolean check = databaseHelper.insertActivityData(activityType, totalTime, "10:00", "11:00", formattedDate, "RED");
        if (check == true){
            Toast toast = Toast.makeText(getApplicationContext(), "Stored activity details: " + activityType+" "+totalTime+" "+formattedDate, Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Upps, there went something wrong!", Toast.LENGTH_LONG);
            toast.show();
        }


    }

    public void showMessage(String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setMessage(Message);
        builder.show();
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
