package com.example.todolist;

public class Task {
    private String text;
    private String date;
    private String time;
    private boolean isCompleted;

    public Task(String text, String date, String time) {
        this.text = text;
        this.date = date;
        this.time = time;
        this.isCompleted = false;
    }

    public String getText() { return text; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
