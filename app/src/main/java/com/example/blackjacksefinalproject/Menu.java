package com.example.blackjacksefinalproject;

public class Menu {
    // Attributes
    RuleSet gameRules;

    // Methods
    public RuleSet getGameRules() {
        return gameRules;
    }

    public void changeSetting() {

    }

    public void saveSettings(RuleSet newRules) {
        this.gameRules = newRules;
    }

    public boolean play() {
        return false;
    }
}
