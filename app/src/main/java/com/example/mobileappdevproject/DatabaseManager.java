package com.example.mobileappdevproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME   = "CYOA_DB";
    private static final int DATABASE_VERSION   = 1;
    private static final String TABLE           = "Choice";
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

    public void insert(Choice newChoice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sqlInsert = String.format("Insert Into %s Values (null, %d, '%s', '%s')", TABLE, newChoice.getParentId(), newChoice.getDialog(), newChoice.getBGImage());

        sqLiteDatabase.execSQL(sqlInsert);
        sqLiteDatabase.close();
    }

    public Choice selectById(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery = String.format("Select * From %s Where %s = %d", TABLE, ID, id);

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        if ( cursor.moveToNext() ) {
            Choice aChoice = new Choice( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3) );
            return aChoice;
        }

        return null;
    }

    public ArrayList<Choice> selectAllByParentId(int parentId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery = String.format("Select * From %s Where %s = %d", TABLE, PARENT_ID, parentId);

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        ArrayList<Choice> choices = new ArrayList<>();

        while ( cursor.moveToNext() ) {
            Choice aChoice = new Choice( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3) );
            choices.add( aChoice );
        }

        sqLiteDatabase.close();
        return choices;
    }

    public ArrayList<Choice> selectAll() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery = String.format("Select * From %s", TABLE);

        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        ArrayList<Choice> choices = new ArrayList<>();

        while ( cursor.moveToNext() ) {
            Choice aChoice = new Choice( Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3) );
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

}
