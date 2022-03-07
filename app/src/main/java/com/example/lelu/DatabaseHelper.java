package com.example.lelu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.os.Build.ID;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String workoutTable = "Workout_table";//name of the table

    //rows in the table
    public static final String workoutName = "Workout_name";
    public static final int set = 0;
    public static final int repetitions = 0;

    //something, idk what
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    //executing when starting app, new base is crated whenever app runs, old data base is deleted
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement;
    }

    //used for changing table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
