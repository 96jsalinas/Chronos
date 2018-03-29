package com.example.a96jsa.chronos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class MainActivity extends AppCompatActivity {

    private boolean recording = false;
    private DrawerLayout mDrawerLayout;
    private SQLiteDatabase db;
    private int recordedTime;


    @BindView(R.id.category) EditText category;
    @BindView(R.id.add_activity) Button add_activity;
    @BindView(R.id.activity) EditText activity;
   // final EditText category = findViewById(R.id.category);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp);

        ButterKnife.bind(this);


        Dbhelper dbhelper = new Dbhelper(getApplicationContext());
         db = dbhelper.getWritableDatabase();

        if(db != null){
            Toast.makeText(this, "database created", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "database not created", Toast.LENGTH_LONG).show();
        }

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
                    //data type int
                    recordedTime = (int)elapsedMillis/1000;
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

    @OnClick(R.id.add_activity)
    public void addActivity(){

        String activityName = category.getText().toString().toLowerCase();
        String categoryName = activity.getText().toString().toLowerCase();
        Activity query = cupboard().withDatabase(db).query(Activity.class).withSelection("activityName = ?", activityName).get();

        if(query == null){
            Toast.makeText(getApplicationContext(),activityName,Toast.LENGTH_SHORT).show();
            Activity activity = new Activity(activityName,categoryName,recordedTime);
            cupboard().withDatabase(db).put(activity);
            Toast.makeText(getApplicationContext(),"data written in database",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"category already exists in database",Toast.LENGTH_SHORT).show();
        }


    }
    @OnClick(R.id.get_activity)
    public void getActivity(){
        try{
            Activity result = cupboard().withDatabase(db).query(Activity.class).get();
            Toast.makeText(getApplicationContext(),result.getActivityName(),Toast.LENGTH_LONG).show();
        }catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"no cateogry added",Toast.LENGTH_LONG).show();
        }




    }
    @OnClick(R.id.remove_activity)
    public void removeActivity(){
        String query = activity.getText().toString();
        if(query.isEmpty()){
            Toast.makeText(getApplicationContext(),query + " specify which category you want to remove",Toast.LENGTH_LONG).show();
        }else {
            cupboard().withDatabase(db).delete(Activity.class,"activityName = ?", query);
            Toast.makeText(getApplicationContext(),query + " removed from database",Toast.LENGTH_LONG).show();
        }

    }



}
