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

   //ActivityTypeActivity table
    private final static String ACTIVITY_TABLE = "Activity";
    private final static String Acitivity_COL1 = "ID";
    private final static String Activity_COL2 = "activityName";
    private final static String Activity_COL3 = "totalTime";
    private final static String Activity_COL4 = "date";

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
                " totalTime TEXT, date TEXT)");

        sqLiteDatabase.execSQL("create table "+ CATEGORY_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ SPORT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ WORK_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ HOUSEWORK_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("create table "+ LEISURE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");

        sqLiteDatabase.execSQL("INSERT or replace INTO Category (Type) VALUES('Sport')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Category (Type) VALUES('Work')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Category (Type) VALUES('Housework')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Category (Type) VALUES('Leisure')");

        sqLiteDatabase.execSQL("INSERT or replace INTO Sport (Type) VALUES('Running')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Sport (Type) VALUES('Walking')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Sport (Type) VALUES('Swimming')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Sport (Type) VALUES('Gym')");

        sqLiteDatabase.execSQL("INSERT or replace INTO Work (Type) VALUES('Studying')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Work (Type) VALUES('Writing')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Work (Type) VALUES('Exercices')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Work (Type) VALUES('Lecture recap')");

        sqLiteDatabase.execSQL("INSERT or replace INTO Housework (Type) VALUES('Cleaning')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Housework (Type) VALUES('Cooking')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Housework (Type) VALUES('Laundry')");

        sqLiteDatabase.execSQL("INSERT or replace INTO Leisure (Type) VALUES('TV')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Leisure (Type) VALUES('Reading')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Leisure (Type) VALUES('Gaming')");
        sqLiteDatabase.execSQL("INSERT or replace INTO Leisure (Type) VALUES('Sleeping')");
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

    public ArrayList<String> getActivities(String category){
        return this.showPossibleActivities(category);
    }

    //Insert activity_activity_type values
    public boolean insertActivityData(String activityName, String totalTime, String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Activity_COL2, activityName);
        contentValues.put(Activity_COL3, totalTime);
        contentValues.put(Activity_COL4, date);
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
    //Check if category exists already exists
    public boolean checkCategory (String categoryName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM sqlite_master", null);
        ArrayList<String> categories = new ArrayList<String>();
        boolean isCategoryPresent = false;
        while (res.moveToNext()){
            categories.add(res.getString(1));
        }
        if (categories.contains(categoryName)){
            isCategoryPresent = true;

        }
        else {
//            createCategoryTable(categoryName);
//            insertCategoryTypes(CATEGORY_TABLE, categoryName);
            isCategoryPresent = false;
        }
        return isCategoryPresent;

    }
    //Check if activity_activity_type for the specific category exists
    public boolean checkActivity (String tableName, String activityName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + tableName, null);
        ArrayList<String> activities = new ArrayList<String>();
        while (res.moveToNext()){
            activities.add(res.getString(1));
        }

        if (activities.contains(activityName)){
            return false;
           }
//          else {
//            insertCategoryTypes(tableName, activityName);
//        }
        return true;
    }

    //Generate table for new category
    public boolean createCategoryTable(String categoryName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("create table "+ categoryName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT)");
        sqLiteDatabase.close();
        return true;
    }

//    public ArrayList<String> getCategories(){
//        return this.showPossibleActivities(CATEGORY_TABLE);
//    }

    public ArrayList<String> getCategories(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Get results from query and save them in a cursor
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name!='android_metadata' AND name!='sqlite_sequence'", null);

        //Transform Cursor into ArrayList with type String
        ArrayList<String> possibleActivityResultList = new ArrayList<String>();
        while (res.moveToNext()){
            //Cursor starts counting at 0, since the name of the activity_activity_type is saved at the
            // second position of the table it has to be 1
            possibleActivityResultList.add(res.getString(1));
        }
        return possibleActivityResultList;
    }
    //Show possible activities or categories
   public ArrayList<String> showPossibleActivities (String tableName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Get results from query and save them in a cursor
        Cursor res = sqLiteDatabase.rawQuery("select * from " + tableName, null);

        //Transform Cursor into ArrayList with type String
        ArrayList<String> possibleActivityResultList = new ArrayList<String>();
        while (res.moveToNext()){
            //Cursor starts counting at 0, since the name of the activity_activity_type is saved at the
            // second position of the table it has to be 1
            possibleActivityResultList.add(res.getString(1));
        }
        return possibleActivityResultList;
    }



    //Update types for specific category
    public boolean updateTypeData (String tableName, String oldName, String newName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String id = new String();
        //Get id from activity_activity_type type so it can be updated
        Cursor res = sqLiteDatabase.rawQuery("select ID from " + tableName + " where Type = ?", new String[]{oldName});
        while (res.moveToNext()){
             id = res.getString(0);
        }

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

