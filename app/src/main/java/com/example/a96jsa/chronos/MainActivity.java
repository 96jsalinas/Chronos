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

public class MainActivity extends AppCompatActivity {

    private boolean recording = false;
    private DrawerLayout mDrawerLayout;
    private DatabaseHelper databaseHelper;
    private Button storeButton;
    private String buffer;
    private Button showCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

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
                        int id = menuItem.getItemId();
                        switch(id){
                            case R.id.dynamic_text_example:
                                Intent dynIntent = new Intent(getBaseContext(),ExampleDynamicText.class);
                                startActivity(dynIntent);
                            case R.id.categories:
                                Intent catIntent = new Intent(getBaseContext(), CategoryActivity.class);
                                startActivity(catIntent);
                                break;
                            case R.id.customize:
                                Intent custIntent = new Intent(getBaseContext(), CustomizeActivity.class);
                                startActivity(custIntent);
                                break;
                            default:
                                Intent homeIntent = new Intent(getBaseContext(),MainActivity.class);
                                startActivity(homeIntent);
                                break;
                        }
//                        if(id == R.id.dynamic_text_example){
//                            Intent dynIntent = new Intent(getBaseContext(),ExampleDynamicText.class);
//                            startActivity(dynIntent);
//                        }else {
//                            Intent homeIntent = new Intent(getBaseContext(),MainScreen.class);
//                            startActivity(homeIntent);
//                        }
//                        if (id == R.id.categories){
//                            Intent catIntent = new Intent(getBaseContext(), CategoryActivity.class);
//                            startActivity(catIntent);
//                        }
//                        if (id == R.id.customize){
//                            Intent custIntent = new Intent(getBaseContext(), CustomizeActivity.class);
//                            startActivity(custIntent);
//                        }

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

                    //Stuff to enter into activity table, will be extracted into separate java class soon
                    String totalTime = Long.toString(elapsedMillis);
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);
                   // Toast toast = Toast.makeText(getApplicationContext(), "Elapsed time (ms): " + elapsedMillis, Toast.LENGTH_SHORT);
                   // toast.show();
                    storeData(totalTime, formattedDate);

                    recording = false;
                    button.setText("Start recording");
                }
            }
        });

        storeButton = findViewById(R.id.storeData);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activity = "babla";
               boolean check = databaseHelper.checkCategory(activity);
               // databaseHelper.insertCategoryTypes("Category", category.getText().toString());
               // databaseHelper.deleteCategory(category.getText().toString());
               // databaseHelper.updateTypeData("Category", "Work", "Random");
                //databaseHelper.insertActivityData("Swimming", "17:00", "18:00", "45", "12.04.18");
                if (check == true){
                    Toast toast = Toast.makeText(getApplicationContext(), "Category " +activity+" is created", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "The category " +activity+"  already exists", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        showCategories = findViewById(R.id.showCategories);
        showCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ArrayList <String> arrayList = databaseHelper.getCategories();
                buffer = arrayList.toString();
                showMessage(buffer);
            }
        });

    }

    private void storeData(String totalTime, String formattedDate) {
        Intent intent = getIntent();
        String activityType = intent.getExtras().getString("Activity");

        final TextView textView = findViewById(R.id.textViewToRecord);
        textView.setText(intent.getExtras().getString("Activity"));
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

