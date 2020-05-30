package com.example.tic_tac_toe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private ArrayList<String> scores;

    RvAdapter( ArrayList<String> scores){
        this.scores=scores;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText(scores.get(position));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

     static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.whoWon);
        }
    }
}
