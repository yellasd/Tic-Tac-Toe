package com.example.tic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int player1wins = 0, player2wins = 0, rounds = 0;
    ArrayList<String> scores;
    BoardView boardView;
    int players = 0;
    private String player1, player2;
    MediaPlayer one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardView = findViewById(R.id.boardView);
        boardView.setMainActivity(this);

        Intent intent = getIntent();
        players = intent.getIntExtra("PLAYERS", 1);
        player1 = intent.getStringExtra("PLAYER1");
        player2 = intent.getStringExtra("PLAYER2");

        scores = new ArrayList<>();
        scores.add("Total number of games : " + rounds);
        scores.add(player1 + " wins " + player1wins + " times.");
        scores.add(player2 + " wins " + player2wins + " times.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        RvAdapter rvAdapter = new RvAdapter(scores);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapter);

    }

    public void displayWinner(int t) {
        one = MediaPlayer.create(getApplicationContext(), R.raw.applause);
        two = MediaPlayer.create(getApplicationContext(), R.raw.p);
        three = MediaPlayer.create(getApplicationContext(), R.raw.tie);
        if (players == 2) {
            String text = "";
            if (t == 1) {
                rounds++;
                player1wins++;
                text = player1 + " won!";
                one.start();
            }
            if (t == 2) {
                rounds++;
                player2wins++;
                text = player2 + " won!";
                two.start();
            }
            if (t == 0) {
                rounds++;
                text = "I see, it's a tie!";
                three.start();
            }

            new AlertDialog.Builder(this).setTitle("Tic Tac Toe").setMessage(text).setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (players == 2) {
                                one.release();
                                two.release();
                                three.release();
                            }
                            for (int i = 0; i < 3; i++)
                                for (int j = 0; j < 3; j++) {
                                    boardView.board.boxes[i][j] = 0;
                                }
                            boardView.invalidate();

                            scores.set(0, "Total number of games : " + rounds);
                            scores.set(1, player1 + " wins " + player1wins + " times.");
                            scores.set(2, player2 + " wins " + player2wins + " times.");

                            RecyclerView recyclerView = findViewById(R.id.recycler_view);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                            recyclerView.setLayoutManager(layoutManager);

                            RvAdapter rvAdapter = new RvAdapter(scores);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(rvAdapter);

                        }
                    }, 500);
                }
            }).show();
        }
        if (players == 1) {
            String text = "";
            if (t == 1) {
                text = player1 + " won!";
                rounds++;
                player1wins++;
            }
            if (t == 2) {
                text = "Computer won!!";
                rounds++;
                player2wins++;
            }
            if (t == 0) {
                text = "I see, it's a tie!";
                rounds++;
            }

            new AlertDialog.Builder(this).setTitle("Tic Tac Toe").setMessage(text).setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 3; j++) {
                            boardView.board.boxes[i][j] = 0;
                        }
                    boardView.invalidate();
                    scores.set(0, "Total number of games : " + rounds);
                    scores.set(1, player1 + " wins " + player1wins + " times.");
                    scores.set(2, player2 + " wins " + player2wins + " times.");

                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    recyclerView.setLayoutManager(layoutManager);

                    RvAdapter rvAdapter = new RvAdapter(scores);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(rvAdapter);
                }
            }).show();
        }
    }

}

