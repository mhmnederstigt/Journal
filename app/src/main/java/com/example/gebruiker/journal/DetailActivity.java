package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry clicked = (JournalEntry) intent.getSerializableExtra("clicked");

        TextView displayTitle = findViewById(R.id.title);
        TextView displayContent = findViewById(R.id.content);
        TextView displayTimestamp = findViewById(R.id.timestamp);
        TextView displayMoodText = findViewById(R.id.moodtext);

        displayTitle.setText(clicked.getTitle());
        displayContent.setText(clicked.getContent());
        displayTimestamp.setText(clicked.getTimestamp());

        // determine what image to display based on mood info from table
        switch (clicked.getMood()) {
            case 1:
                displayMoodText.setText("bad");
                break;
            case 2:
                displayMoodText.setText("odd");
                break;
            case 3:
                displayMoodText.setText("neutral");
                break;
            case 4:
                displayMoodText.setText("great");
                break;
        }
    }
}
