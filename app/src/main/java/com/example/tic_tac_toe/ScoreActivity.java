package com.example.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ScoreActivity extends AppCompatActivity {

    int rounds, player1wins, player2wins;
    String player1, player2;
    ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_score);

        strings=new ArrayList<>();

        Intent intent = getIntent();
        rounds = intent.getIntExtra("rounds", rounds);
        player1= intent.getStringExtra("PLAYER1");
        player2= intent.getStringExtra("PLAYER2");
        player1wins= intent.getIntExtra("player1score",player1wins);
        player2wins= intent.getIntExtra("player2score",player2wins);

        strings.add("Total number of games : "+rounds);
        strings.add(player1+" wins "+player1wins+" times.");
        strings.add(player2+" wins "+player2wins+" times.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RvAdapter rvAdapter = new RvAdapter( strings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapter);
    }
}
