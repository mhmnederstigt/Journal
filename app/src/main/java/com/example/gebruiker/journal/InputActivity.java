package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class InputActivity extends AppCompatActivity {
    int mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new SubmitClickListener());

        ImageView bad = (ImageView) findViewById(R.id.smileybad);
        bad.setOnClickListener(new SmileyClickListener());
        ImageView odd = (ImageView) findViewById(R.id.smileyodd);
        odd.setOnClickListener(new SmileyClickListener());
        ImageView neutral = (ImageView) findViewById(R.id.smileyneutral);
        neutral.setOnClickListener(new SmileyClickListener());
        ImageView great = (ImageView) findViewById(R.id.smileygreat);
        great.setOnClickListener(new SmileyClickListener());
    }

    public class SmileyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // if view is bad produce int is 1 etc.
            Log.d("hi", "hola!");
            switch (view.getId()) {
                case R.id.smileybad:
                    mood = 1;
                    morph(view);
                    break;
                case R.id.smileyodd:
                    mood = 2;
                    morph(view);
                    break;
                case R.id.smileyneutral:
                    mood = 3;
                    morph(view);
                    break;
                case R.id.smileygreat:
                    mood = 4;
                    morph(view);
                    break;
            }
        }
    }

    private void morph(View view) {
        int padding = view.getPaddingTop();
        if (padding > 0){
            view.setPadding(0,0,0,0);
        }
        else {
            view.setPadding(8,8,8,8);
        }
    }

    private class SubmitClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            addEntry(view);

            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void addEntry (View view){
        EntryDatabase db = EntryDatabase.getInstance(this);

        JournalEntry entry = new JournalEntry();

        EditText title = findViewById(R.id.title);
        EditText content = findViewById(R.id.content);
        entry.setMood(mood);
        entry.setTitle(title.getText().toString());
        entry.setContent(content.getText().toString());
        db.insert(entry);
    }

}
