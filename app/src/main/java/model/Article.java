package model;

import android.util.Log;

public class Article {
    private String title, infographic, color;
    public Article() {}
    public Article(String title, String infographic){
        this.title = title;
        this.infographic = infographic;
        this.color="#212121";
        Log.d("TAG", "onCreate: ARTICLE TITLE = " + title);

    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }

    public String getTitle() {
        return this.title;
    }
    public String getInfographic() {
        return this.infographic;
    }
}
