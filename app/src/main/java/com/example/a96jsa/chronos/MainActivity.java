package com.example.a96jsa.chronos;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean recording = false;
    private DrawerLayout mDrawerLayout;
    private DatabaseHelper databaseHelper;
    private Button storeButton;
    private Button totalTimeButton;
    private String buffer;
    private Button showCategories;
    private Context context;
    long totalTime;
    String totalTimeString = "0";

    private Date date;
    private String selectedCategory;
    private String activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //application context
        context = getApplicationContext();
        databaseHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp);

        //spinner
        databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<String> categoriesArrayList = databaseHelper.getCategories();
        Spinner categoriesSpinner = (Spinner)findViewById(R.id.categories);
        ArrayAdapter<String> categoriesSpinnerAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,categoriesArrayList);
        categoriesSpinner.setAdapter(categoriesSpinnerAdapter);
        categoriesSpinner.setOnItemSelectedListener(this);








        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        switch (id){
                            case R.id.dynamic_text_example:
                                Intent dynIntent = new Intent(getBaseContext(),ExampleDynamicText.class);
                                startActivity(dynIntent);
                                break;
                            case R.id.stadistics_activity:
                                Intent statisticsIntent = new Intent(getBaseContext(),StatisticsActivity.class);
                                startActivity(statisticsIntent);
                                break;
                            default:
                                Intent homeIntent = new Intent(getBaseContext(),MainScreen.class);
                                startActivity(homeIntent);
                                break;
                        }


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


        final EditText activity_et = (EditText)findViewById(R.id.activity_et);
        final Button button = findViewById(R.id.record);
        final Chronometer simpleChronometer = findViewById(R.id.simpleChronometer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!recording) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Starting recording in category: " + activity_et.getText().toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    recording = true;
                    simpleChronometer.setBase(SystemClock.elapsedRealtime());
                    simpleChronometer.start();
                    button.setText("Stop recording");

                }else {
                    simpleChronometer.stop();
                    totalTime = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
                    totalTimeString = Long.toString(totalTime);
                    Toast toast = Toast.makeText(getApplicationContext(), "Elapsed time (ms): " + totalTime, Toast.LENGTH_SHORT);
                    toast.show();
                    recording = false;
                    button.setText("Start recording");
                }
            }
        });
        totalTimeButton = findViewById(R.id.totalTimeButton);
        totalTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalCategoryTime = databaseHelper.getCategoryTotalTime(selectedCategory);
                Toast.makeText(context,"total time:" +Integer.toString(totalCategoryTime),Toast.LENGTH_SHORT).show();

            }
        });
        storeButton = findViewById(R.id.storeData);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // databaseHelper.createCategoryTable(category.getText().toString());
               // databaseHelper.insertCategoryTypes("Category", category.getText().toString());
               // databaseHelper.deleteCategory(category.getText().toString());
                Toast.makeText(context,"data stored",Toast.LENGTH_LONG);
                activityName = activity_et.getText().toString();
                date = new Date();
                SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");


                String dateString = date.toString();
                String dateStringFormat = dt.format(date);
                //dateString= date.toString();

                //databaseHelper.updateTypeData("Category", "Work", "Random");



                    databaseHelper.insertActivityData(selectedCategory,activityName, "17:00", "18:00", totalTimeString, dateStringFormat);


                    ArrayList<String> List = databaseHelper.getActivities(selectedCategory);

                    for(String item : List){
                        Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCategory = adapterView.getSelectedItem().toString();
       Toast.makeText(context,selectedCategory + "selected",Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
