package com.example.jonelezhang.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonelezhang on 5/10/16.
 */
public class NotesDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME ="notes"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database
//  constructor of class
    NotesDatabaseHelper(Context context){
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTE(" +
                "_id INTEGER PRIMARY KEY " +
                "TITLE TEXT" +
                "CONTENT TEXT" +
                "IMAGE_RESOURCE_ID INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
