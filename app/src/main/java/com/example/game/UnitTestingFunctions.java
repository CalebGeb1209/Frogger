package com.example.game;
public class UnitTestingFunctions {

    public static int increaseScoreByOne(int currentScore) {
        currentScore = currentScore + 1;
        return currentScore;
    }

    public static int decreaseLivesWithWaterTile(int currentLives, int playerXCoordinate,
                                                 int playerYCoordinate) {

        /*
        * This function tests whether our code correctly decreases the amount of lives to 2
        * if the player is in a water tile at full health
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
        if (gridArray[playerXCoordinate][playerYCoordinate]) {
            currentLives -= 1;
        }
        return currentLives;
    }

    public static int changeScore(int currentScore, int playerXCoordinate, int playerYCoordinate) {
        /*
        * This function resets the score back to zero if we go into a water tile
        * if we have 3 or 2 lives and keeps current score if we have 1 life (game over)
         */
        if (decreaseLivesWithWaterTile(3, playerXCoordinate, playerYCoordinate) == 2
                || decreaseLivesWithWaterTile(2, playerXCoordinate,
                    playerYCoordinate) == 1) {
            currentScore = 0;
        } else if (decreaseLivesWithWaterTile(1, playerXCoordinate, playerYCoordinate) == 0) {
            return currentScore;
        }
        return currentScore;
    }

    public static boolean goToGameOverScreen(int currentLives, int playerXCoordinate,
                                             int playerYCoordinate) {

        if (decreaseLivesWithWaterTile(currentLives, playerXCoordinate, playerYCoordinate) == 2
                || decreaseLivesWithWaterTile(currentLives, playerXCoordinate,
                    playerYCoordinate) == 1) {
            return false;
        }
        return true;
    }

    public static int decreaseLivesWithCollision(int currentLives, int playerXCoordinate,
                                                 int playerYCoordinate, int vehicleXCoordinate,
                                                 int vehicleYCoordinate) {

        if (playerXCoordinate == vehicleXCoordinate && vehicleYCoordinate == playerYCoordinate) {
            currentLives -= 1;
        }
        return currentLives;
    }

    public static boolean respawnFrogFromWaterCollision(int currentLives, int playerXCoordinate,
                                                        int playerYCoordinate) {
        if ((decreaseLivesWithWaterTile(currentLives, playerXCoordinate, playerYCoordinate)
                == currentLives - 1)) {
            playerXCoordinate = 3;
            playerYCoordinate = 15;
        }
        if (playerXCoordinate == 3 && playerYCoordinate == 15) {
            return true;
        }
        return false;
    }

    public static boolean respawnFrogFromVehicleCollision(int currentLives, int playerXCoordinate,
                                                          int playerYCoordinate,
                                                          int vehicleXCoordinate,
                                                          int vehicleYCoordinate) {
        if ((decreaseLivesWithCollision(currentLives, playerXCoordinate, playerYCoordinate,
                vehicleXCoordinate, vehicleYCoordinate)) == currentLives - 1) {
            playerXCoordinate = 3;
            playerYCoordinate = 15;
        }
        if (playerXCoordinate == 3 && playerYCoordinate == 15) {
            return true;
        }
        return false;
    }






}
