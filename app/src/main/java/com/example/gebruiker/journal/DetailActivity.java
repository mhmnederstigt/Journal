package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        displayTitle.setText(clicked.getTitle());
        displayContent.setText(clicked.getContent());
        displayTimestamp.setText(clicked.getTimestamp());




    }
}
