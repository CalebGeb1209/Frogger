package com.example.game;
import java.util.Random;
public class UnitTestingFunctions {

    public static int increaseScoreByOne(int currentScore) {
        currentScore = currentScore + 1;
        return currentScore;
    }

    public static int DecreaseLivesWithWaterTile(int currentLives, int playerXCoordinate, int playerYCoordinate) {

        /*
        * This function tests whether our code correctly decreases the amount of lives to 2 if the player is in a water tile at full health
        *
         */
        boolean[][] gridArray = new boolean[15][15];

        // make the 6th and 7th rows true (equivalent to water tiles)
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i == 5 || i == 6) {
                    gridArray[i][j] = true;
                } else {
                    gridArray[i][j] = false;
                }

            }
        }
        if (gridArray[playerXCoordinate][playerYCoordinate] == true) {
            currentLives -= 1;
        }
        return currentLives;
    }

    public static int changeScore(int currentScore, int playerXCoordinate, int playerYCoordinate) {
        /*
        * This function resets the score back to zero if we go into a water tile if we have 3 or 2 lives and keeps current score if we have 1 life (game over)
         */
        if (DecreaseLivesWithWaterTile(3, playerXCoordinate, playerYCoordinate) == 2 || DecreaseLivesWithWaterTile(2, playerXCoordinate, playerYCoordinate) == 1) {
            currentScore = 0;
        } else if (DecreaseLivesWithWaterTile(1, playerXCoordinate, playerYCoordinate) == 0) {
            return currentScore;
        }
        return currentScore;
    }

    public static boolean goToGameOverScreen(int currentLives, int playerXCoordinate, int playerYCoordinate) {

        if (DecreaseLivesWithWaterTile(currentLives, playerXCoordinate, playerYCoordinate) == 2 || DecreaseLivesWithWaterTile(currentLives, playerXCoordinate, playerYCoordinate) == 1) {
            return false;
        }
        return true;
    }

    public static int decreaseLivesWithCollision(int currentLives, int playerXCoordinate, int playerYCoordinate, int vehicleXCoordinate, int vehicleYCoordinate) {

        if (playerXCoordinate == vehicleXCoordinate && vehicleYCoordinate == playerYCoordinate) {
            currentLives -= 1;
        }
        return currentLives;
    }






}
