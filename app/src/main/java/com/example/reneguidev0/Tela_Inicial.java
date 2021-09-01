package com.example.reneguidev0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Content;


public class Tela_Inicial  extends AppCompatActivity {

    FirebaseFirestore storage;
    ArrayList<Content> contentList = new ArrayList<Content>();
    ArrayList<Article> allArticlesList = new ArrayList<Article>();
    RecyclerView recyclerview;
    RecyclerView recycler_search_view;
    ContentRecyclerAdapter adapter;
    ArticleSearchRecyclerAdapter search_adapter;

    SearchView search_button;


    ImageView bt_home, bt_help;
    CardView btSobre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        search_button = findViewById(R.id.searchbar);
        search_button.setOnQueryTextListener(queryTextListener);

        // recyclerview dos conteúdos
        recyclerview = findViewById(R.id.recycleview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        // recyclerview dos conteúdos da pesquisa
        recycler_search_view = findViewById(R.id.recycler_search_view);
        recycler_search_view.setHasFixedSize(true);



        recycler_search_view.setLayoutManager(new GridLayoutManager(this, 2));
        storage = FirebaseFirestore.getInstance();
        fetchData();




        bt_home = findViewById(R.id.homebutton);
        bt_home.setPressed(true);
        bt_home.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Inicial.class);
            startActivity(intent);


        });

        btSobre = findViewById(R.id.bt_sobre);
        btSobre.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Sobre.class);
            startActivity(intent);


        });

        bt_help = findViewById(R.id.helpbutton);
        bt_help.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Help.class);
            startActivity(intent);


        });



    }

    SearchView.OnQueryTextListener queryTextListener= new SearchView.OnQueryTextListener(){

        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            search_adapter.getFilter().filter(newText);
            return true;
        }
    };

    public void setContentList(List<Content> contents){
        Toast.makeText(getApplicationContext(), "conteúdos carregados!", Toast.LENGTH_LONG).show();

        contentList.addAll(contents);
        for (int i = 0; i < contentList.size(); i++) {
            ArrayList<Article> articles = contentList.get(i).getArticles();
            for (Article article : articles) {
                article.setColor(contentList.get(i).getColor());
            }
            allArticlesList.addAll(contentList.get(i).getArticles());
        }
        adapter = new ContentRecyclerAdapter(this, contentList);
        search_adapter = new ArticleSearchRecyclerAdapter(this, allArticlesList);
        recyclerview.setAdapter(adapter);
        recycler_search_view.setAdapter(search_adapter);

    }

    public void fetchData(){
        storage.collection("contents").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("TAG", "onSuccess: LIST EMPTY");
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            List<Content> contents = documentSnapshots.toObjects(Content.class);
                            setContentList(contents);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error getting data from server!", Toast.LENGTH_LONG).show();
                    }
                });
    }



}
