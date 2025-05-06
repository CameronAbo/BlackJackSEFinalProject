package com.example.blackjacksefinalproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    int playerCount;
    int deckCount;
    float initialBalance;
    boolean reshuffle;

    TextView textViewGameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        textViewGameInfo = findViewById(R.id.textView_game_info);

        // Load game settings
        SharedPreferences prefs = getSharedPreferences("GameSettings", MODE_PRIVATE);
        playerCount = prefs.getInt("playerCount", 1);
        deckCount = prefs.getInt("deckCount", 1);
        initialBalance = prefs.getFloat("initialBalance", 10000f);
        reshuffle = prefs.getBoolean("reshuffle", true);

        // Show settings as a placeholder
        textViewGameInfo.setText(
                "Game started with:\n" +
                        "Players: " + playerCount + "\n" +
                        "Decks: " + deckCount + "\n" +
                        "Initial Balance: $" + initialBalance + "\n" +
                        "Reshuffle: " + (reshuffle ? "Yes" : "No")
        );

        // TODO: Hook up your actual game logic here
    }
}
