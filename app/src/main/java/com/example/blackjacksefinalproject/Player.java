package com.example.blackjacksefinalproject;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private double balance;
    private double bet;
    private List<Card> hand;


    public Player(double balance) {
        this.balance = balance;
        this.hand = new ArrayList<>();
    }


    public void placeBet(double amount) {
        this.bet = amount;
        balance -= bet;
    }


    public void winBet() {
        balance += bet * 2;
    }


    public void forfeit() {
        // No additional deduction needed; already deducted in placeBet()
    }


    public double getBalance() {
        return balance;
    }


    public void addCardToHand(Card card) {
        hand.add(card);
    }


    public void clearHand() {
        hand.clear();
    }


    public int handValue() {
        int total = 0;
        int aces = 0;
        for (Card card : hand) {
            total += card.getValue();
            if (card.getValueString().equals("Ace")) aces++;
        }
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }


    public List<Card> getHand() {
        return hand;
    }
}

