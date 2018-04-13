package net.johanneslink.scoreboard.gui;

import net.johanneslink.scoreboard.core.ScoreboardPresenter;

public class Main {

    public static void main(String[] args) {
        ScoreboardPresenter sc = new ScoreboardPresenter();
        new ScoreboardSwingApp(sc).setVisible(true);
    }
}
