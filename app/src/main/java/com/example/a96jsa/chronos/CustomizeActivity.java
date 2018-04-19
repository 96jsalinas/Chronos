package com.example.a96jsa.chronos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomizeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //EditText for categories when adding types of activities can be replaced by the spinner
    Button addCategoryButton;
    Button activityTypeButton;
    EditText addCategoryText;
    EditText addActivityTypeCategoryText;
    EditText addNewActivityType;

    //color spinner
    Spinner colorSpinner;
    ArrayList<Integer> colorList;
    ArrayList<String>colorListString;
    String selectedColor;




    private DatabaseHelper databaseHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
       colorList = new ArrayList<Integer>();
       colorListString = new ArrayList<>();
       colorList.add(Color.RED);
       colorList.add(Color.BLUE);
       colorList.add(Color.GREEN);
       colorList.add(Color.YELLOW);
       colorList.add(Color.MAGENTA);

       colorListString.add("RED");
        colorListString.add("BLUE");
        colorListString.add("GREEN");
        colorListString.add("YELLOW");
        colorListString.add("MAGENTA");

        //Fetching parts from the the XML file
        //addCategoryButton = findViewById(R.id.categoryButton);
        activityTypeButton = findViewById(R.id.actitvityTypeButton);
        //addCategoryText = findViewById(R.id.enterCategory);
        addActivityTypeCategoryText = findViewById(R.id.enterCategoryType);
        addNewActivityType = findViewById(R.id.enterActivityType);
        databaseHelper = new DatabaseHelper(this);

        colorSpinner = (Spinner)findViewById(R.id.colorSpinner);
        ArrayAdapter<String> colorSpinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,colorListString);
        colorSpinner.setAdapter(colorSpinnerArrayAdapter);
        colorSpinner.setOnItemSelectedListener(this);


//        addCategoryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean check = databaseHelper.checkCategory(addCategoryText.getText().toString());
//                if (check == true){
//                    Toast toast = Toast.makeText(getApplicationContext(), "Category " + addCategoryText.getText().toString() +" is created", Toast.LENGTH_LONG);
//                    toast.show();
//                } else {
//                    Toast toast = Toast.makeText(getApplicationContext(), "The category " + addCategoryText.getText().toString() +"  already exists", Toast.LENGTH_LONG);
//                    toast.show();
//                }
//            }
//        });

        activityTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryString = addActivityTypeCategoryText.getText().toString();
                String activityTypeString = addNewActivityType.getText().toString();
                String color = "GREEN";
                boolean checkCategory = databaseHelper.checkCategory(categoryString);
                if(checkCategory == true){
                    boolean checkActivity = databaseHelper.checkActivity(categoryString, activityTypeString);
                    if (checkActivity == true){
                        if(activityTypeString.isEmpty()){
                            Toast.makeText(getApplicationContext(), "The  category " + categoryString + "has been created already, insert an activity ", Toast.LENGTH_LONG).show();
                            return;
                        }else {
                            databaseHelper.insertCategoryTypes(categoryString, activityTypeString, color);
                            Toast toast = Toast.makeText(getApplicationContext(), "The activity type " + activityTypeString +" has been added to " + categoryString, Toast.LENGTH_LONG);
                            toast.show();
                        }

                    } else {
                       Toast.makeText(getApplicationContext(), "The activity " + activityTypeString+"  already exists for " + categoryString, Toast.LENGTH_LONG).show();
                       return;

                    }

                }else {
                    //databaseHelper.createCategoryTable(categoryString);
                    if(activityTypeString.isEmpty()){
                        if(databaseHelper.checkCategory(categoryString)){
                            Toast.makeText(getApplicationContext(), "The category " + categoryString+"  has been created already" , Toast.LENGTH_LONG).show();
                            return;
                        }else {
                            databaseHelper.createCategoryTable(categoryString);
                            databaseHelper.insertCategorytoCategoryTable(categoryString, selectedColor);
                            Toast.makeText(getApplicationContext(), "The category " + categoryString+"  is created " + selectedColor , Toast.LENGTH_LONG).show();
                            return;
                        }

                    }else {
                        databaseHelper.createCategoryTable(categoryString);
                        databaseHelper.insertCategorytoCategoryTable(categoryString, selectedColor);
                        databaseHelper.insertCategoryTypes(categoryString,activityTypeString, color);
                     Toast.makeText(getApplicationContext(), "The new activity " + activityTypeString+"  is created " + categoryString, Toast.LENGTH_LONG).show();

                    }

                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      selectedColor = adapterView.getSelectedItem().toString();
      if(selectedColor != "RED"){
          Toast.makeText(getApplicationContext(),selectedColor,Toast.LENGTH_SHORT).show();
      }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
