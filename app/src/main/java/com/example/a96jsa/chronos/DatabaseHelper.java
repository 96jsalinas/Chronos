package com.example.a96jsa.chronos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "Chronos.db";

   //Activity table
    public final static String ACTIVITY_TABLE = "Activity";
    public final static String Acitivity_COL1 = "ID";
    public final static String Activity_COL2 = "activityName";
    public final static String Activity_COL3 = "startTime";
    public final static String Activity_COL4 = "endTime";
    public final static String Activity_COL5 = "totalTime";
    public final static String Activity_COL6 = "date";


    //Sport table
    public final static String SPORT_TABLE = "Sport";
    public final static String Sport_COL1 = "ID";
    public final static String Sport_COL2 = "sportType";

    //Work table
    public final static String WORK_TABLE = "Work";
    public final static String Work_COL1 = "ID";
    public final static String Work_COL2 = "workType";

    //Housework table
    public final static String HOUSEWORK_TABLE = "Housework";
    public final static String Housework_COL1 = "ID";
    public final static String Housework_COL2 = "houseworkType";

    //Leisure table
    public final static String LEISURE_TABLE = "Leisure";
    public final static String Leisure_COL1 = "ID";
    public final static String Leisure_COL2 = "leisureType";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ ACTIVITY_TABLE +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, activityName TEXT, " +
                "startTime TEXT, endTime TEXT, totalTime TEXT, date TEXT)");

        sqLiteDatabase.execSQL("create table "+ SPORT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, sportType TEXT)");

        sqLiteDatabase.execSQL("create table "+ WORK_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, workType TEXT)");

        sqLiteDatabase.execSQL("create table "+ HOUSEWORK_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, houseworkType TEXT)");

        sqLiteDatabase.execSQL("create table "+ LEISURE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, leisureType TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_TABLE);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SPORT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WORK_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HOUSEWORK_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LEISURE_TABLE);

    }

    public void insertActivityData(String activityName, String starTime, String endTime, String totalTime, String date){
        SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Activity_COL2, activityName);
        contentValues.put(Activity_COL3, starTime);
        contentValues.put(Activity_COL4, endTime);
        contentValues.put(Activity_COL5, totalTime);
        contentValues.put(Activity_COL6, date);
        sqLiteOpenHelper.insert(ACTIVITY_TABLE, null, contentValues);
    }

    public void insertSportTypes (String sportType){
    SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(Sport_COL2, sportType);
    sqLiteOpenHelper.insert(SPORT_TABLE, null, contentValues);
    }

    public void insertWorkTypes (String workType){
        SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Work_COL2, workType);
        sqLiteOpenHelper.insert(WORK_TABLE, null, contentValues);
    }

    public void insertHouseworkTypes (String houseworkType){
        SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Housework_COL2, houseworkType);
        sqLiteOpenHelper.insert(HOUSEWORK_TABLE, null, contentValues);
    }

    public void insertLeisureTypes (String leisureType){
        SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Leisure_COL2, leisureType);
        sqLiteOpenHelper.insert(LEISURE_TABLE, null, contentValues);
    }
}

