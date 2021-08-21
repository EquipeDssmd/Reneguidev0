package model;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Content {
    private String title, color;
    private ArrayList<Article> articles;
    public Content() {}
    public Content(String title, String color, QuerySnapshot articles){
        this.title = title;
        this.color = color;
        this.articles = (ArrayList<Article>) articles.toObjects(Article.class);

    }

    public String getTitle() {
        return this.title;
    }

    public String getColor() {
        return this.color;
    }


    public ArrayList<Article> getArticles() {
        return this.articles;
    }
}
