package com.example.blackjacksefinalproject;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;


    public Game() {
        deck = new Deck();
        player = new Player(1000);
        dealer = new Player(0);
    }


    public void resetHands() {
        player.clearHand();
        dealer.clearHand();
        deck.shuffle();
    }


    public void dealInitialCards() {
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());
    }


    public void dealerDrawUntil17() {
        while (dealer.handValue() < 17) {
            dealer.addCardToHand(deck.dealCard());
        }
    }


    public void playerHits() {
        player.addCardToHand(deck.dealCard());
    }


    public void dealerHits() {
        dealer.addCardToHand(deck.dealCard());
    }


    public Player getPlayer() {
        return player;
    }


    public Player getDealer() {
        return dealer;
    }


    public void placeBet(double amount) {
        player.placeBet(amount);
    }


    public String getResult() {
        int playerTotal = player.handValue();
        int dealerTotal = dealer.handValue();


        if (playerTotal > 21) {
            player.forfeit();
            return "You busted! Dealer wins.";
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            player.winBet();
            return "You win!";
        } else if (playerTotal < dealerTotal) {
            player.forfeit();
            return "Dealer wins!";
        } else {
            return "It's a tie!";
        }
    }
}

