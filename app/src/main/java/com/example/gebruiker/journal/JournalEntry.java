package com.example.gebruiker.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private int id;
    private String title;
    private String content;
    private int mood;
    private String timestamp;

    public JournalEntry() {
        title = "";
        content = "";
        mood = 0;
        timestamp = "";
    }

    public void setId(int Id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
