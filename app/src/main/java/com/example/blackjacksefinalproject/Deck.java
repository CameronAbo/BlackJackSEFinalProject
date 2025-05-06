package com.example.blackjacksefinalproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Deck {
    private List<Card> deck;


    public Deck() {
        deck = new ArrayList<>();
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (int s = 0; s < 4; s++) {
            for (String v : values) {
                deck.add(new Card(v, s));
            }
        }
        shuffle();
    }


    public void shuffle() {
        Collections.shuffle(deck, new Random());
    }


    public Card dealCard() {
        return deck.remove(deck.size() - 1);
    }
}

