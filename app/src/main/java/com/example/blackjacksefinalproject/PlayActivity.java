package com.example.blackjacksefinalproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    int playerCount;
    int deckCount;
    float initialBalance;
    boolean reshuffle;

    TextView textViewGameInfo;

    // Game Logic Vars
    private TextView playerHandText, dealerHandText, statusText, balanceText;
    private EditText betInput;
    private Button hitButton, standButton, placeBetButton;


    private Player player = new Player(1000);
    private Player dealer = new Player(0);
    private Deck deck = new Deck();
    private double currentBet = 0;

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

        playerHandText = findViewById(R.id.playerHandText);
        dealerHandText = findViewById(R.id.dealerHandText);
        statusText = findViewById(R.id.statusText);
        balanceText = findViewById(R.id.balanceText);
        betInput = findViewById(R.id.betInput);
        hitButton = findViewById(R.id.hitButton);
        standButton = findViewById(R.id.standButton);
        placeBetButton = findViewById(R.id.placeBetButton);


        placeBetButton.setOnClickListener(view -> startNewRound());
        hitButton.setOnClickListener(view -> playerHit());
        standButton.setOnClickListener(view -> playerStand());


        updateBalanceDisplay();

    }

    private void startNewRound() {
        try {
            currentBet = Double.parseDouble(betInput.getText().toString());
        } catch (NumberFormatException e) {
            statusText.setText("Enter a valid bet.");
            return;
        }


        if (currentBet > player.getBalance()) {
            statusText.setText("Insufficient balance.");
            return;
        }


        player.clearHand();
        dealer.clearHand();
        deck.shuffle();


        player.placeBet(currentBet);
        updateBalanceDisplay();
        placeBetButton.setVisibility(View.GONE); // HIDE after bet placed


        player.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());


        updateHands();


        hitButton.setVisibility(View.VISIBLE);
        standButton.setVisibility(View.VISIBLE);
        statusText.setText("Game started. Choose hit or stand.");
    }


    private void playerHit() {
        player.addCardToHand(deck.dealCard());
        updateHands();


        if (player.handValue() > 21) {
            statusText.setText("You busted! Dealer wins.");
            hitButton.setVisibility(View.GONE);
            standButton.setVisibility(View.GONE);
            placeBetButton.setVisibility(View.VISIBLE); // SHOW for next round
            updateBalanceDisplay();
        }
    }


    private void playerStand() {
        dealer.addCardToHand(deck.dealCard());
        while (dealer.handValue() < 17) {
            dealer.addCardToHand(deck.dealCard());
        }


        updateHands();
        determineWinner();


        hitButton.setVisibility(View.GONE);
        standButton.setVisibility(View.GONE);
        placeBetButton.setVisibility(View.VISIBLE); // SHOW for next round
        updateBalanceDisplay();
    }


    private void updateHands() {
        playerHandText.setText("Player Hand: " + getHandDescription(player));
        dealerHandText.setText("Dealer Hand: " + getHandDescription(dealer));
    }


    private String getHandDescription(Player p) {
        StringBuilder desc = new StringBuilder();
        for (Card card : p.getHand()) {
            desc.append(card.getCardName()).append(", ");
        }
        desc.append("Total: ").append(p.handValue());
        return desc.toString();
    }


    private void determineWinner() {
        int playerTotal = player.handValue();
        int dealerTotal = dealer.handValue();


        if (playerTotal > 21) {
            player.forfeit();
            statusText.setText("You busted! Dealer wins.");
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            player.winBet();
            statusText.setText("You win!");
        } else if (playerTotal < dealerTotal) {
            player.forfeit();
            statusText.setText("Dealer wins.");
        } else {
            statusText.setText("It's a tie!");
        }
    }


    private void updateBalanceDisplay() {
        balanceText.setText("Balance: $" + String.format("%.2f", player.getBalance()));
    }

}
