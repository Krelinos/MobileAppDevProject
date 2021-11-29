package com.example.mobileappdevproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "CYOA_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CHOICE1 = "Choice1";
    private static final String CHOICE2 = "Choice2";
    private static final String ID = "Id";
    private static final String ACTIVITY = "Activity";
    // private static final String ANSWER          = "Answer";

    //create the table of activities the user will go through
    private static final String CREATE_TABLE_ACTIVITY = String.format("CREATE ACTIVITY %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%sTEXT,%sTEXT, %s TEXT, );", DATABASE_NAME, ID, ACTIVITY, CHOICE1, CHOICE2);


    public DatabaseManager(Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACTIVITY);//CREATING THE ACTIVITY FOR THE QUESTIONS
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop old table if it exists
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", CREATE_TABLE_ACTIVITY));
        // Re-create tables
        onCreate(db);
    }

    //details of each scenario
    public long addInitialActivity (Choice choice) {
        SQLiteDatabase database = this.getWritableDatabase();
        //creating the content values
        ContentValues values = new ContentValues();

        values.put(ACTIVITY, choice.getActivity());
        values.put(CHOICE1, choice.getChoice(0));
        values.put(CHOICE2, choice.getChoice(1));
      //values.put(ANSWER, choice.getAnswer());

        //insert row
        long insert = database.insert(ACTIVITY, null, values);
        return insert;
    }// end add initial

    //extract data and save in array list
    public List<Activity> getAllActivityList(){
        List<Choice> activityArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ACTIVITY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Choice activity = new Choice();

                @SuppressLint("Range") String activityText = c.getString(c.getColumnIndex(ACTIVITY));
                activity.setActivity(activityText);

                @SuppressLint("Range") String choice1Text = c.getString(c.getColumnIndex(ACTIVITY));
                activity.setChoice(0, choice1Text);

                @SuppressLint("Range") String choice2Text = c.getString(c.getColumnIndex(ACTIVITY));
                activity.setChoice(1, choice2Text);

                activityArrayList.add(activity);

            } while(c.moveToNext());
        }return activityArrayList;
    }
}
/*
    private static final String DATABASE_NAME   = "CYOA_DB";
    private static final int DATABASE_VERSION   = 1;
    private static final String TABLE           = "choice";
    private static final String ID              = "Id";
    private static final String PARENT_ID       = "ParentId";
    private static final String DIALOG          = "Dialog";
    private static final String BG_IMAGE        = "BGImage";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlQuery = String.format("Create Table %s ( %s Integer Primary Key Autoincrement, %s Integer, %s Text, %s Text )", TABLE, ID, PARENT_ID, DIALOG, BG_IMAGE );
        sqLiteDatabase.execSQL( sqlQuery );

    }

    public void insert(choice newChoice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sqlInsert = String.format("Insert Into %s Values (null, %d, '%s', '%s')", TABLE, newChoice.getParentId(), newChoice.getDialog(), newChoice.getBGImage());

        sqLiteDatabase.execSQL(sqlInsert);
        sqLiteDatabase.close();
    }

    public choice selectById(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery = String.format("Select * From %s Where %s = %d", TABLE, ID, id);

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        if ( cursor.moveToNext() ) {
            choice aChoice = new choice( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3) );
            return aChoice;
        }

        return null;
    }

    public ArrayList<choice> selectAllByParentId(int parentId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery = String.format("Select * From %s Where %s = %d", TABLE, PARENT_ID, parentId);

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        ArrayList<choice> choices = new ArrayList<>();

        while ( cursor.moveToNext() ) {
            choice aChoice = new choice( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3) );
            choices.add( aChoice );
        }

        sqLiteDatabase.close();
        return choices;
    }

    public ArrayList<choice> selectAll() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery = String.format("Select * From %s", TABLE);

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        ArrayList<choice> choices = new ArrayList<>();

        while ( cursor.moveToNext() ) {
            choice aChoice = new choice( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3) );
            choices.add( aChoice );
        }

        sqLiteDatabase.close();
        return choices;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE );
        // Re-create tables
        onCreate( db );
    }

}*/
