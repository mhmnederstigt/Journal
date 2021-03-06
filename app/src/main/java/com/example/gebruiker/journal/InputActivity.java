package com.example.gebruiker.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class InputActivity extends AppCompatActivity {
    final int PADDING = 16;
    int mood;
    ImageView bad;
    ImageView odd;
    ImageView neutral;
    ImageView great;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new SubmitClickListener());

        bad = findViewById(R.id.smileybad);
        bad.setOnClickListener(new SmileyClickListener());
        odd = findViewById(R.id.smileyodd);
        odd.setOnClickListener(new SmileyClickListener());
        neutral = findViewById(R.id.smileyneutral);
        neutral.setOnClickListener(new SmileyClickListener());
        great = findViewById(R.id.smileygreat);
        great.setOnClickListener(new SmileyClickListener());

        // set padding of smileys as it was previously
        if (savedInstanceState != null){
            int paddingBad = savedInstanceState.getInt("paddingBad");
            bad.setPadding(paddingBad,paddingBad, paddingBad, paddingBad);
            int paddingOdd = savedInstanceState.getInt("paddingOdd");
            odd.setPadding(paddingOdd,paddingOdd, paddingOdd, paddingOdd);
            int paddingNeutral = savedInstanceState.getInt("paddingNeutral");
            neutral.setPadding(paddingNeutral,paddingNeutral, paddingNeutral, paddingNeutral);
            int paddingGreat = savedInstanceState.getInt("paddingGreat");
            great.setPadding(paddingGreat,paddingGreat, paddingGreat, paddingGreat);
        }
        else {
            bad.setPadding(PADDING, PADDING, PADDING, PADDING);
            odd.setPadding(PADDING, PADDING, PADDING, PADDING);
            neutral.setPadding(PADDING, PADDING, PADDING, PADDING);
            great.setPadding(PADDING, PADDING, PADDING, PADDING);
        }
    }

    public class SmileyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.smileybad:
                    morph(view);
                    mood = 1;
                    break;
                case R.id.smileyodd:
                    morph(view);
                    mood = 2;
                    break;
                case R.id.smileyneutral:
                    morph(view);
                    mood = 3;
                    break;
                case R.id.smileygreat:
                    morph(view);
                    mood = 4;
                    break;
            }
        }
    }

    private void morph(View view) {
        // deflate all smileys, except for the one that is clicked
        bad.setPadding(PADDING,PADDING,PADDING,PADDING);
        odd.setPadding(PADDING,PADDING,PADDING,PADDING);
        neutral.setPadding(PADDING,PADDING,PADDING,PADDING);
        great.setPadding(PADDING,PADDING,PADDING,PADDING);

        // inflate clicked smiley
        view.setPadding(0,0,0,0);
    }

    private class SubmitClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            addEntry(view);
            finish();
        }
    }

    public void addEntry (View view){
        // create a new entry in database and insert this
        EntryDatabase db = EntryDatabase.getInstance(this);

        JournalEntry entry = new JournalEntry();

        EditText title = findViewById(R.id.title);
        EditText content = findViewById(R.id.content);
        entry.setMood(mood);
        entry.setTitle(title.getText().toString());
        entry.setContent(content.getText().toString());
        db.insert(entry);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // save state of padding/clicked or not, upon saving
        savedInstanceState.putInt("paddingBad", bad.getPaddingBottom());
        savedInstanceState.putInt("paddingOdd", odd.getPaddingBottom());
        savedInstanceState.putInt("paddingNeutral", neutral.getPaddingBottom());
        savedInstanceState.putInt("paddingGreat", great.getPaddingBottom());
    }
}
