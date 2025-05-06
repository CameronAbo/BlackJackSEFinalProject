package com.example.blackjacksefinalproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    EditText editTextBalance;
    SwitchCompat switchReshuffle;
    RuleSet ruleSet;

    Button buttonIncreasePlayer, buttonDecreasePlayer, buttonSave;
    TextView textViewPlayerCount;
    int playerCount = 1;
    final int MIN_PLAYERS = 1;
    final int MAX_PLAYERS = 6;

    Button buttonIncreaseDeck, buttonDecreaseDeck;
    TextView textViewDeckCount;
    int deckCount = 1;
    final int MIN_DECKS = 1;
    final int MAX_DECKS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Player count controls
        buttonIncreasePlayer = findViewById(R.id.button_increase_player);
        buttonDecreasePlayer = findViewById(R.id.button_decrease_player);
        buttonSave = findViewById(R.id.button_save);
        textViewPlayerCount = findViewById(R.id.textview_player_count);

        // Initial balance and reshuffle
        editTextBalance = findViewById(R.id.edittext_balance);
        switchReshuffle = findViewById(R.id.switch_reshuffle);

        buttonIncreasePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerCount < MAX_PLAYERS) {
                    playerCount++;
                    textViewPlayerCount.setText(String.valueOf(playerCount));
                }
            }
        });

        buttonDecreasePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerCount > MIN_PLAYERS) {
                    playerCount--;
                    textViewPlayerCount.setText(String.valueOf(playerCount));
                }
            }
        });

        // Deck count controls
        buttonIncreaseDeck = findViewById(R.id.button_increase_deck);
        buttonDecreaseDeck = findViewById(R.id.button_decrease_deck);
        textViewDeckCount = findViewById(R.id.textview_deck_count);

        buttonIncreaseDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deckCount < MAX_DECKS) {
                    deckCount++;
                    textViewDeckCount.setText(String.valueOf(deckCount));
                }
            }
        });

        buttonDecreaseDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deckCount > MIN_DECKS) {
                    deckCount--;
                    textViewDeckCount.setText(String.valueOf(deckCount));
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });

        loadSettings();

    }

    private void saveSettings() {
        SharedPreferences prefs = getSharedPreferences("GameSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        float balance = 0;
        try {
            balance = Float.parseFloat(editTextBalance.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number for balance", Toast.LENGTH_SHORT).show();
            return;
        }

        editor.putInt("playerCount", playerCount);
        editor.putInt("deckCount", deckCount);
        editor.putFloat("initialBalance", balance);
        editor.putBoolean("reshuffle", switchReshuffle.isChecked());
        editor.apply();




        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();

        // ✅ Close SettingsActivity and return to MainActivity
        finish();
    }

    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences("GameSettings", MODE_PRIVATE);

        playerCount = prefs.getInt("playerCount", 1);
        deckCount = prefs.getInt("deckCount", 1);
        float balance = prefs.getFloat("initialBalance", 10000.00f);
        boolean reshuffle = prefs.getBoolean("reshuffle", false);

        // Update UI
        textViewPlayerCount.setText(String.valueOf(playerCount));
        textViewDeckCount.setText(String.valueOf(deckCount));
        editTextBalance.setText(String.valueOf(balance));
        switchReshuffle.setChecked(reshuffle);
    }
}


