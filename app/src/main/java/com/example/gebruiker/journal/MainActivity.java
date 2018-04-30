package com.example.gebruiker.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    EntryDatabase db;
    EntryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set listener on floating action button
        FloatingActionButton floatAction = (FloatingActionButton) findViewById(R.id.action);
        floatAction.setOnClickListener(new ActionButtonClickListener());

        // display rows of database table as items in listview
        // Get the current database
        db = EntryDatabase.getInstance(getApplicationContext());
        // select all entries (rows)
        Cursor c = db.selectAll();
        // connect list of entries with the listview through adapter
        ListView lv = (ListView) findViewById(R.id.listofentries);
        adapter = new EntryAdapter(this, c);
        lv.setAdapter(adapter);

        // set listeners to items
        lv.setOnItemClickListener(new ListItemClickListener());
        lv.setOnItemLongClickListener(new ListItemLongClickListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private class ActionButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("hi", "listitemclicklistener");
            // find the entry which was clicked
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            // get data from db and put them in a journal entry
            JournalEntry clicked = new JournalEntry();

            clicked.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            clicked.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            clicked.setContent(cursor.getString(cursor.getColumnIndex("content")));
            clicked.setTimestamp(cursor.getString(cursor.getColumnIndex("timestamp")));

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("clicked", clicked);
            startActivity(intent);
        }
    }

    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor c = (Cursor) parent.getItemAtPosition(position);
            int idT = c.getInt(c.getColumnIndex("_id"));
            db.delete(idT);

            // update the view
            updateData();
            return false;
        }
    }

    private void updateData() {
        //db = EntryDatabase.getInstance(getApplicationContext()); (QUESTION!)
        Cursor c = db.selectAll();
        adapter.swapCursor(c);
    }
}
