package com.example.a96jsa.chronos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomizeActivity extends AppCompatActivity {

    //EditText for categories when adding types of activities can be replaced by the spinner
    Button addCategoryButton;
    Button activityTypeButton;
    EditText addCategoryText;
    EditText addActivityTypeCategoryText;
    EditText addNewActivityType;
    private DatabaseHelper databaseHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        //Fetching parts from the the XML file
        addCategoryButton = findViewById(R.id.categoryButton);
        activityTypeButton = findViewById(R.id.actitvityTypeButton);
        addCategoryText = findViewById(R.id.enterCategory);
        addActivityTypeCategoryText = findViewById(R.id.enterCategoryType);
        addNewActivityType = findViewById(R.id.enterActivityType);
        databaseHelper = new DatabaseHelper(this);

        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = databaseHelper.checkCategory(addCategoryText.getText().toString());
                if (check == true){
                    Toast toast = Toast.makeText(getApplicationContext(), "Category " + addCategoryText.getText().toString() +" is created", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "The category " + addCategoryText.getText().toString() +"  already exists", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        activityTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryString = addActivityTypeCategoryText.getText().toString();
                String activityTypeString = addNewActivityType.getText().toString();
                boolean checkActivity = databaseHelper.checkActivity(categoryString, activityTypeString);
                if (checkActivity == true){
                    Toast toast = Toast.makeText(getApplicationContext(), "The activity type " + activityTypeString +" has been added to " + categoryString, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "The activity " + activityTypeString+"  already exists for " + categoryString, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
