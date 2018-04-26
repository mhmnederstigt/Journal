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
        // create new table in database
        db.execSQL("CREATE TABLE diary (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood INTEGER, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE diary");
        onCreate(db);
    }

    // Method to select database
    public Cursor selectAll(){
        // select all rows in table
        return this.getWritableDatabase().rawQuery("SELECT * FROM diary", null, null);
    }

    public void insert(JournalEntry entry) {
        SQLiteDatabase wdb = getWritableDatabase();

        // A shortcut functionality from SQLiteOpenHelper to insert into database without the use of an SQL statement (QUESTION!)
        ContentValues cv = new ContentValues();

        // Insert data (as received by UI and saved in entry) into table
        cv.put("title", entry.getTitle());
        cv.put("content", entry.getContent());
        cv.put("mood", entry.getMood());
        wdb.insert("diary",null, cv);
    }

    public void delete(int id){
        SQLiteDatabase wdb = getWritableDatabase();
        wdb.execSQL("DELETE FROM diary WHERE _id = " + id);
    }

}
