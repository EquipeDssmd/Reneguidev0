package com.example.reneguidev0;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import model.Content;


public class Tela_Inicial  extends AppCompatActivity {

    FirebaseFirestore storage;
    ArrayList<Content> contentList = new ArrayList<Content>();
    RecyclerView recyclerview;
    CustomRecyclerAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        recyclerview = findViewById(R.id.recycleview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        storage = FirebaseFirestore.getInstance();
        fetchData();
    }

    public void setContentList(List<Content> contents){
        contentList.addAll(contents);
        Toast.makeText(getApplicationContext(), contentList.get(0).getTitle(), Toast.LENGTH_LONG).show();
        adapter = new CustomRecyclerAdapter(this, contentList);
        recyclerview.setAdapter(adapter);

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
