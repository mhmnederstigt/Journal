package com.example.gebruiker.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import android.widget.ResourceCursorAdapter;

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor c) {
        super(context, R.layout.entry_row, c);

    }

    public void bindView(View view, Context context, Cursor c){
        // get the title and timestamp from the DB
        String title = c.getString(c.getColumnIndex("title"));
        int mood = c.getInt(c.getColumnIndex("mood"));
        //String timestamp = c.getString(c.getColumnIndex("timestamp"));

        // get the views in which it has to be displayed
        TextView displayTitle = view.findViewById(R.id.title);
        TextView displayMood = view.findViewById(R.id.mood);
        TextView displayTimestamp = view.findViewById(R.id.timestamp);

        // set info in display
        displayTitle.setText(title);
        displayMood.setText(Integer.toString(mood));
        // displayTimestamp.setText(timestamp);

    }
}
