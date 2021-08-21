package com.example.reneguidev0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import model.Article;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder> {

    Context context;
    ArrayList<Article> list;
    String color;
    public ArticleRecyclerAdapter(Context context, ArrayList<Article> list, String color) {
        this.context = context;
        this.list = list;
        this.color = color;
    }

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
        holder.card.setCardBackgroundColor(Color.parseColor(color));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        CardView card;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.articleTitle);
            card = itemView.findViewById(R.id.card);

            card.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), Tela_Pdf.class);
                itemView.getContext().startActivity(intent);

            });
        }
    }
}