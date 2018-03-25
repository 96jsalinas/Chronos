package com.example.a96jsa.chronos;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



}
