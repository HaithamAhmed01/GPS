package com.example.gps.Model;

public class NoteItem {
    String Title;
    String Content;
    String Time;

    public NoteItem(String title, String content, String time) {
        Title = title;
        Content = content;
        Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
