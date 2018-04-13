package com.example.a96jsa.chronos;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    /*


    * The methods will return the Boolean value true in order to make it possible to make
    * Toast notifications to indicate for the user if the requested task was successful


     */

    private final static String DATABASE_NAME = "Chronos.db";

   //Activity table
    private final static String ACTIVITY_TABLE = "Activity";
    private final static String Acitivity_COL1 = "ID";
    private final static String Activity_COL2 = "activityName";
    private final static String Activity_COL3 = "startTime";
    private final static String Activity_COL4 = "endTime";
    private final static String Activity_COL5 = "totalTime";
    private final static String Activity_COL6 = "date";

    //Category table
    private final static String CATEGORY_TABLE = "Category";

    //Sport table
    private final static String SPORT_TABLE = "Sport";

    //Work table
    private final static String WORK_TABLE = "Work";

    //Housework table
    private final static String HOUSEWORK_TABLE = "Housework";

    //Leisure table
    private final static String LEISURE_TABLE = "Leisure";



    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ ACTIVITY_TABLE +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, activityName TEXT, " +
                "startTime TEXT, endTime TEXT, totalTime TEXT, date TEXT)");

        sqLiteDatabase.execSQL("create table "+ CATEGORY_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ SPORT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ WORK_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ HOUSEWORK_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ LEISURE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

       // sqLiteDatabase.execSQL("insert into " + CATEGORY_TABLE + "( Type ) values (Sport, Work, Housework, Leisure)" );
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SPORT_TABLE);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + WORK_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HOUSEWORK_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LEISURE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<String> getCategories(){
        return this.showPossibleActivities(CATEGORY_TABLE);
    }

    public ArrayList<String> getActivities(String category){
        return this.showPossibleActivities(category);
    }

    //Insert activity values
    public boolean insertActivityData(String activityName, String starTime, String endTime, String totalTime, String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Activity_COL2, activityName);
        contentValues.put(Activity_COL3, starTime);
        contentValues.put(Activity_COL4, endTime);
        contentValues.put(Activity_COL5, totalTime);
        contentValues.put(Activity_COL6, date);
        //insert returns -1 if it failed, so it is possible to check this way if it did work
        long result = sqLiteDatabase.insert(ACTIVITY_TABLE, null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }

    }

    //Insert category specific types, this methods needs also be called when a new category is created
    public boolean insertCategoryTypes (String tableName, String typeName){
        SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Type", typeName);
        long result = sqLiteOpenHelper.insert(tableName, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    //Generate table for new category
    public boolean createCategoryTable(String categoryName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("create table "+ categoryName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");
        sqLiteDatabase.close();
        return true;
    }

    public ArrayList<String> getCategories(){
        return this.showPossibleActivities(CATEGORY_TABLE);
    }
    //Show possible activities or categories
   public ArrayList<String> showPossibleActivities (String tableName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Get results from query and save them in a cursor
        Cursor res = sqLiteDatabase.rawQuery("select * from " + tableName, null);

        //Transform Cursor into ArrayList with type String
        ArrayList<String> possibleActivityResultList = new ArrayList<String>();
        while (res.moveToNext()){
            //Cursor starts counting at 0, since the name of the activity is saved at the
            // second position of the table it has to be 1
            possibleActivityResultList.add(res.toString());
        }
        return possibleActivityResultList;
    }



    //Update types for specific category
    public boolean updateTypeData (String tableName, String oldName, String newName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //Get id from activity type so it can be updated
        String id = String.valueOf(sqLiteDatabase.rawQuery("select ID from" + tableName + "where Type = ?", new String[] {oldName} ));

        //Update name
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("Type", newName);
        sqLiteDatabase.update(tableName, contentValues, "ID = ?", new String[]{id});


        return true;
    }

    //Delete types of activities / Category
    public boolean deleteData (String tableName, String Name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(tableName, "type = ?", new String[] {Name});

        return true;
    }

    //Delete category table
    public boolean deleteCategory(String tableName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + tableName);

        return true;
    }
}

