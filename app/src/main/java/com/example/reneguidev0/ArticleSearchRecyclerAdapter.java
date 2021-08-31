package com.example.reneguidev0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import model.Article;

public class ArticleSearchRecyclerAdapter extends RecyclerView.Adapter<ArticleSearchRecyclerAdapter.ArticleViewHolder> implements Filterable {

    Context context;
    ArrayList<Article> list;
    ArrayList<Article> list_filtered;
    String color;

    public ArticleSearchRecyclerAdapter(Context context, ArrayList<Article> list, String color) {
        this.context = context;
        this.list = list;
        this.list_filtered = new ArrayList<>(list);
        this.color = color;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.article_search, parent, false);
        return new ArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = list_filtered.get(position);
        holder.title.setText(article.getTitle());

        holder.card.setCardBackgroundColor(Color.parseColor(color));

        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), Tela_Pdf.class);
            intent.putExtra("pdfUrl", list_filtered.get(position).getInfographic());
            holder.itemView.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        if (list_filtered.size() > 4) {
            return 4;
        } else {
            return list_filtered.size();
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Article> filteredList = new ArrayList<>();
            if (constraint.toString().isEmpty()){
                filteredList.addAll(list);
            } else {
                for (Article article : list){
                    if(article.getTitle().toLowerCase(Locale.ROOT).contains(constraint.toString().toLowerCase(Locale.ROOT))){
                        filteredList.add(article);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list_filtered.clear();
            list_filtered.addAll((Collection<? extends Article>) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        CardView card;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.articleTitle);
            card = itemView.findViewById(R.id.card);

        }
    }
}