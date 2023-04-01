package com.example.game;

import android.widget.TextView;

public class ScoreManager {

    private int maxLevelReached;
    private int points;
    private PlayerMovement playerMovement;
    TextView pointDetermination;

    public ScoreManager(PlayerMovement playerMovement, TextView pointDetermination) {
        this.playerMovement = playerMovement;
        this.pointDetermination = pointDetermination;
        maxLevelReached = 15;
        points = 0;
    }

    public int getScore() {
        return points;
    } // getPoints

    public void setMaxLevelReached(int maxLevelReached) {
        this.maxLevelReached = maxLevelReached;
    } // setMaxLevelReached

    public void resetScore() {
        maxLevelReached = 15;
        points = 0;
        playerMovement.resetCoords();
        pointDetermination.setText("0 Pts");
    } // setupPoints

    public void updateScore() {
        if (playerMovement.getPlayerY() < maxLevelReached) {
            if (playerMovement.getPlayerY() + 1 < 2) {
                points++;
            } else if (playerMovement.getPlayerY() + 1 < 9) {
                points += 2;
            } else if (playerMovement.getPlayerY() + 1 < 15 && playerMovement.getPlayerY() + 1 > 9) {
                points += 3;
            } else {
                points++;
            }
            maxLevelReached--;
            pointDetermination.setText(points + " Pts");
        }
    } // updateScore
}