package model;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Content {
    private String title;
    private ArrayList<Article> articles;
    public Content() {}
    public Content(String title, QuerySnapshot articles){
        this.title = title;
        this.articles = (ArrayList<Article>) articles.toObjects(Article.class);

    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<Article> getArticles() {
        return this.articles;
    }
}
