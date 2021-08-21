package com.example.reneguidev0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import model.Content;

public class ContentRecyclerAdapter extends RecyclerView.Adapter<ContentRecyclerAdapter.ContentViewHolder> {

    Context context;
    ArticleRecyclerAdapter adapter;
    public ContentRecyclerAdapter(Context context, ArrayList<Content> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<Content> list;

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        return new ContentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        Content content = list.get(position);
        holder.title.setText(content.getTitle());


        holder.articlesview.setHasFixedSize(true);
        holder.articlesview.setLayoutManager(new LinearLayoutManager(this.context));
        adapter = new ArticleRecyclerAdapter(this.context, content.getArticles(), content.getColor());
        holder.articlesview.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        RecyclerView articlesview;
        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.contentTitle);
            articlesview = itemView.findViewById(R.id.articlesview);

        }
    }
}