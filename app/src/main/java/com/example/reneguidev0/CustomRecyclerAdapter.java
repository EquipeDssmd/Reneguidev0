package com.example.reneguidev0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import model.Content;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MyViewwHolder> {

    Context context;

    public CustomRecyclerAdapter(Context context, ArrayList<Content> list) {
        this.context = context;
        this.list = list;
        //this.list.add(new Content("a", null));
    }

    ArrayList<Content> list;

    @NonNull
    @Override
    public MyViewwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        return new MyViewwHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewwHolder holder, int position) {
        Content content = list.get(position);
        holder.title.setText(content.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewwHolder extends RecyclerView.ViewHolder{
        TextView title;
        public MyViewwHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.contentTitle);
        }
    }
}