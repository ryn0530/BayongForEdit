package com.example.bayong;

public class Details {

    String title, itemCount, date;

    public Details() {
    }

    public Details(String title, String itemCount, String date) {
        this.title = title;
        this.itemCount = itemCount;
        this.date = date;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
