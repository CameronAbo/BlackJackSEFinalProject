package com.example.blackjacksefinalproject;

public class Card {
    public static final int HEARTS = 0;
    public static final int DIAMONDS = 1;
    public static final int CLUBS = 2;
    public static final int SPADES = 3;


    private String value;
    private int suit;


    public Card(String value, int suit) {
        this.value = value;
        this.suit = suit;
    }


    public int getValue() {
        if (value.equals("Jack") || value.equals("Queen") || value.equals("King")) {
            return 10;
        } else if (value.equals("Ace")) {
            return 11;
        }
        return Integer.parseInt(value);
    }


    public String getCardName() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        return value + " of " + suits[suit];
    }


    public String getValueString() {
        return value;
    }
}

