package com.example.blackjacksefinalproject;

public class RuleSet
{
    // Attributes
    int     playerNumber;
    int     difficulty;
    int     deckCount;
    float  initialBalance;
    boolean reshuffleDeck;

    //Constructor
    public RuleSet()
    {
        playerNumber = 0;
        difficulty = 0;
        deckCount = 1;
        initialBalance = 10000.00F;
        reshuffleDeck = false;
    }

    public RuleSet(int playerNumber,
                   int difficulty,
                   int deckCount,
                   int initialBalance,
                   boolean reshuffleDeck)
    {
        this.playerNumber = playerNumber;
        this.difficulty = difficulty;
        this.deckCount = deckCount;
        this.initialBalance = initialBalance;
        this.reshuffleDeck = reshuffleDeck;
    }

    // Accessors
    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public boolean isReshuffleDeck() {
        return  reshuffleDeck;
    }

    // Mutators
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setInitialBalance(float initialBalance) {
        this.initialBalance = initialBalance;
    }

    public void setReshuffleDeck(boolean reshuffleDeck) {
        this.reshuffleDeck = reshuffleDeck;
    }
}
