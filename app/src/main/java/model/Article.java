package model;

import android.util.Log;

public class Article {
    private String title;
    public Article() {}
    public Article(String title){
        this.title = title;
        Log.d("TAG", "onCreate: ARTICLE TITLE = " + title);

    }

    public String getTitle() {
        return this.title;
    }
}
