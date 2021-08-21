package com.example.reneguidev0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import model.Article;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder> {

    Context context;

    public ArticleRecyclerAdapter(Context context, ArrayList<Article> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<Article> list;

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.article, parent, false);
        return new ArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = list.get(position);
        holder.title.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.articleTitle);
        }
    }
}