package com.example.gebruiker.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ResourceCursorAdapter;

import java.sql.Timestamp;

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor c) {
        super(context, R.layout.entry_row, c);
    }

    public void bindView(View view, Context context, Cursor bindViewCursor){
        // get the title and timestamp from the DB
        String title = bindViewCursor.getString(bindViewCursor.getColumnIndex("title"));
        int mood = bindViewCursor.getInt(bindViewCursor.getColumnIndex("mood"));
//        String timestamp = bindViewCursor.getString(bindViewCursor.getColumnIndex("timestamp"));

        // get the views in which it has to be displayed
        TextView displayTitle = view.findViewById(R.id.title);
        ImageView displayMood = view.findViewById(R.id.mood);
        TextView displayMoodtext = view.findViewById(R.id.moodtext);
//        TextView displayTimestamp = view.findViewById(R.id.timestamp);

        // set info in display
        displayTitle.setText(title);
//        displayTimestamp.setText(timestamp);

        // Determine what image to display based on mood info from table
        switch (mood) {
            case 1:
                displayMood.setImageResource(R.drawable.smileybad);
                displayMoodtext.setText("bad");
                break;
            case 2:
                displayMood.setImageResource(R.drawable.smileyodd);
                displayMoodtext.setText("odd");
                break;
            case 3:
                displayMood.setImageResource(R.drawable.smileyneutral);
                displayMoodtext.setText("neutral");
                break;
            case 4:
                displayMood.setImageResource(R.drawable.smileygreat);
                displayMoodtext.setText("great");
                break;
        }
    }
}
