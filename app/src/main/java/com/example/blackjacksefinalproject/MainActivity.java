package com.example.blackjacksefinalproject;

import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonPlay, buttonSettings;

    int playerCount, deckCount;
    float        initialBalance;
    boolean reshuffle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        buttonPlay = findViewById(R.id.button_play);
        buttonSettings = findViewById(R.id.button_settings);

        loadSettings(); // load on startup

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use settings to start the game
                Toast.makeText(MainActivity.this,
                        "Players: " + playerCount + ", Decks: " + deckCount +
                                ", Balance: " + initialBalance +
                                ", Reshuffle: " + reshuffle,
                        Toast.LENGTH_LONG).show();

                 Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                 //intent.putExtra(...) // pass values if needed
                 startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSettings(); // Reload settings in case they changed
    }

    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences("GameSettings", MODE_PRIVATE);

        playerCount = prefs.getInt("playerCount", 1);
        deckCount = prefs.getInt("deckCount", 1);
        initialBalance = prefs.getFloat("initialBalance", 10000f);
        reshuffle = prefs.getBoolean("reshuffle", false);
    }
}