package com.example.game;

import android.util.Log;
import android.widget.TextView;

public class ScoreManager {

    private int maxLevelReached;
    private int points;
    private PlayerMovement playerMovement;
    private TextView pointDetermination;
    private GameScreen screen;

    public ScoreManager(PlayerMovement playerMovement,
                        TextView pointDetermination, GameScreen screen) {
        this.playerMovement = playerMovement;
        this.pointDetermination = pointDetermination;
        maxLevelReached = 15;
        points = 0;
        this.screen = screen;
    } // ScoreManager

    public int getScore() {
        return points;
    } // getPoints

    public void setMaxLevelReached(int maxLevelReached) {
        this.maxLevelReached = maxLevelReached;
    } // setMaxLevelReached

    public void resetScore() {
        maxLevelReached = 15;
        points = 0;
        pointDetermination.setText("0 Pts");
    } // setupPoints

    public void updateScore() {
        if (playerMovement.getPlayerY() < maxLevelReached) {
            Log.d("Player Y", String.valueOf(playerMovement.getPlayerY()));
            if (playerMovement.getPlayerY() + 1 < 2) {
                points++;
            } else if (playerMovement.getPlayerY() + 1 < 9 && playerMovement.getPlayerY() != 1) {
                points += 2;
            } else if (playerMovement.getPlayerY() + 1 < 15
                    && playerMovement.getPlayerY() + 1 > 9) {
                points += 3;
            } else if (playerMovement.getPlayerY() == 1) {
                points += 10;
                screen.winGame();
            } else {
                points++;
            }
            maxLevelReached--;
            pointDetermination.setText(points + " Pts");
        }
    } // updateScore
}