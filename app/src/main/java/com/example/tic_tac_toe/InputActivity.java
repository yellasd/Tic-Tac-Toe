package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InputActivity extends AppCompatActivity {
    EditText player1Text, player2Text;
    String player1, player2;
    Button button;
    int players = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        player1 = "Player 1";
        player2 = "Player 2";

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                player1Text = findViewById(R.id.singlePlayerText);
                player2Text = findViewById(R.id.otherplayerText);
                button = findViewById(R.id.go);

                if (checkedId == R.id.single) {
                    players = 1;
                    button.setVisibility(View.VISIBLE);
                    player2Text.setText("Computer");
                    player2Text.setEnabled(false);
                    player1Text.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            player1 = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    players=2;
                    button.setVisibility(View.VISIBLE);
                    player2Text.setEnabled(true);
                    player2Text.setText("");
                    player1Text.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            player1 = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    player2Text.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            player2 = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });
    }

    public void go(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("PLAYERS", players);
        intent.putExtra("PLAYER1", player1);
        intent.putExtra("PLAYER2", player2);
        startActivity(intent);
    }
}
