package com.example.jonelezhang.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonelezhang on 5/10/16.
 */
public class NotesDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME ="notes"; // the name of our database
    private static final int DATABASE_VERSION = 1;// database version
    private static final String  TABLE_NAME="NOTE";
    private static final String  COLUMN_ID="ID";
    private static final String  COLUMN_TITLE="TITLE";
    private static final String  COLUMN_CONTENT="CONTENT";
    private static final String  COLUMN_IMAGE_ID="IMAGE_RESOURCE_ID";

//  constructor of class
    NotesDatabaseHelper(Context context)
    {
        super(context, DB_NAME,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_CONTENT + " TEXT,"
                + COLUMN_IMAGE_ID + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS "+ TABLE_NAME );
        onCreate(db);
    }
    // add a new row into database
    public void addNote(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getTitle());
        values.put(COLUMN_CONTENT,note.getContent());
        values.put(COLUMN_IMAGE_ID,note.getImageResourceId());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    // get all note list from database
    public List<Note> getAllNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "Select * from " + TABLE_NAME;
        Cursor c  = db.rawQuery(selectQuery,null);
        List<Note> noteList = new ArrayList<Note>();
        try{
        if(c.moveToFirst()){
            do{
                Note note = new Note("");
                note.setTitle(c.getString(1));
                note.setContent(c.getString(2));
                note.setImageResourceId(c.getString(3));
                noteList.add(note);
            }while(c.moveToNext());
        }
        }finally{
            c.close();
        }
        return noteList;
    }
}
