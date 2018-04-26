package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new SubmitClickListener());
    }

    private class SubmitClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            addEntry(view);

            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);


            Log.d("hi", "add entry");
            // code to run when the button gets clicked

        }
    }

    public void addEntry (View view){
        EntryDatabase db = EntryDatabase.getInstance(this);

        JournalEntry entry = new JournalEntry();

        EditText title = findViewById(R.id.title);
        EditText content = findViewById(R.id.content);
        EditText mood = findViewById(R.id.mood);

        entry.setTitle(title.getText().toString());
        entry.setContent(content.getText().toString());
        entry.setMood(5);
        db.insert(entry);



    }

}
