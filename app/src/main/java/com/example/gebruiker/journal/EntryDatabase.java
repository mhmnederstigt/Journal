package com.example.gebruiker.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Locale;

public class EntryDatabase extends SQLiteOpenHelper {
    private static EntryDatabase instance;
    private static final String dbName = "journalEntries";
    private static final int dbVersion = 5;

    // If database exists, use that one, otherwise create new
    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
           instance = new EntryDatabase(context, dbName, null, dbVersion);
        }
        return instance;

    }

    // Constructor of EntryDatabase
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create DB tables
        db.execSQL("CREATE TABLE diary (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood INTEGER, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
        // fill with a test entry
        db.execSQL("INSERT INTO diary (title, content, mood) VALUES ('Test', 'Testing', 2)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE diary");
        onCreate(db);

    }

    // Method to select database
    public Cursor selectAll(){
        // select all the entries in the DB
        return this.getWritableDatabase().rawQuery("SELECT * FROM diary", null, null);
    }

    public void insert(JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        // put
        cv.put("title", entry.getTitle());
        cv.put("content", entry.getContent());
        cv.put("mood", entry.getMood());

        // call insert
        db.insert("diary",null,cv);
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String Q = "DELETE FROM diary WHERE _id = " + id;
        db.execSQL(Q);
    }

}
