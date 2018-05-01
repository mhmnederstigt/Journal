package com.example.gebruiker.journal;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry clicked = (JournalEntry) intent.getSerializableExtra("clicked");

        TextView displayTitle = (TextView) findViewById(R.id.title);
        TextView displayContent = (TextView) findViewById(R.id.content);
        TextView displayTimestamp = (TextView) findViewById(R.id.timestamp);
        TextView displayMoodtext = (TextView) findViewById(R.id.moodtext);

        displayTitle.setText(clicked.getTitle());
        displayContent.setText(clicked.getContent());
        displayTimestamp.setText(clicked.getTimestamp());

        // Determine what image to display based on mood info from table
        switch (clicked.getMood()) {
            case 1:
                displayMoodtext.setText("bad");
                break;
            case 2:
                displayMoodtext.setText("odd");
                break;
            case 3:
                displayMoodtext.setText("neutral");
                break;
            case 4:
                displayMoodtext.setText("great");
                break;
        }

     }
}
