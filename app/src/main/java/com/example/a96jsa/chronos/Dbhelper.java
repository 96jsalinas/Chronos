package com.example.a96jsa.chronos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by kevin on 3/27/2018.
 */

public class Dbhelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "chronos_database";
    private static int DATABASE_VERSION = 1;
    public Dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    static {
        cupboard().register(Activity.class);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
