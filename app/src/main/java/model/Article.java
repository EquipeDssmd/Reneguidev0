package model;

import android.util.Log;

public class Article {
    private String title, infographic;
    public Article() {}
    public Article(String title, String infographic){
        this.title = title;
        this.infographic = infographic;
        Log.d("TAG", "onCreate: ARTICLE TITLE = " + title);

    }

    public String getTitle() {
        return this.title;
    }
}
