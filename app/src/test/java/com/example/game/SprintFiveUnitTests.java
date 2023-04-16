package com.example.game;

import static org.junit.Assert.*;

import org.junit.Test;

import kotlin.Unit;


public class SprintFiveUnitTests {

    @Test
    public void transitionToGameWinWithPoints() {
        int score = UnitTestingFunctions.getScore(5,15);

        assertEquals(true, UnitTestingFunctions.hasWon(score, 5, 15));


        assertEquals(true, UnitTestingFunctions.hasWon(score, 5, 11));

        assertEquals(true, UnitTestingFunctions.hasWon(score, 5, 12));

    }

    @Test
    public void transitionToGameWinWithPosition() {


        int playerXCoordinate = 10;
        int playerYCoordinate = 15;

        assertEquals(true, UnitTestingFunctions.hasWon(33, playerXCoordinate, playerYCoordinate));


        assertEquals(true, UnitTestingFunctions.hasWon(33, playerXCoordinate, playerYCoordinate));

        assertEquals(true, UnitTestingFunctions.hasWon(33, playerXCoordinate, playerYCoordinate));

    }

    @Test
    public void updateLogCoordinatesLeft() {
        int currentLogXCoordinate = 3;
        int currentLogYCoordinate = 12;

        int[][] updatedCoords = UnitTestingFunctions.updateCoordinates("left", currentLogXCoordinate,
                currentLogYCoordinate, 5,5);

        assertEquals(currentLogXCoordinate - 1, updatedCoords[0][0]);

        assertEquals(currentLogYCoordinate, updatedCoords[0][1]);

        assertEquals(4, updatedCoords[1][0]);

        assertEquals(5, updatedCoords[1][1]);



    }

    @Test
    public void updateLogCoordinatesRight() {

        int currentLogXCoordinate = 3;
        int currentLogYCoordinate = 14;

        int[][] updatedCoords = UnitTestingFunctions.updateCoordinates("right", currentLogXCoordinate,
                currentLogYCoordinate, 5,5);

        assertEquals(currentLogXCoordinate + 1, updatedCoords[0][0]);

        assertEquals(currentLogYCoordinate, updatedCoords[0][1]);

        assertEquals(6, updatedCoords[1][0]);

        assertEquals(5, updatedCoords[1][1]);


    }


    @Test
    public void moveFrogWithLogLeft() {
        int currentLogXCoordinate = 3;
        int currentLogYCoordinate = 12;

        int currentPlayerXCoordinate = 3;
        int currentPlayerYCoordinate = 12;

        int[][] updatedCoords = UnitTestingFunctions.updateCoordinates("left", currentLogXCoordinate,
                currentLogYCoordinate, currentPlayerXCoordinate,currentPlayerYCoordinate);



        assertEquals(updatedCoords[0][0], updatedCoords[1][0]);
        assertEquals(updatedCoords[0][1], updatedCoords[1][1]);

        assertEquals(currentPlayerXCoordinate - 1, updatedCoords[1][0]);
        assertEquals(currentPlayerYCoordinate, updatedCoords[1][1]);



    }

    @Test
    public void moveFrogWithLogRight() {
        int currentLogXCoordinate = 3;
        int currentLogYCoordinate = 12;

        int currentPlayerXCoordinate = 3;
        int currentPlayerYCoordinate = 12;

        int[][] updatedCoords = UnitTestingFunctions.updateCoordinates("right", currentLogXCoordinate,
                currentLogYCoordinate, currentPlayerXCoordinate,currentPlayerYCoordinate);



        assertEquals(updatedCoords[0][0], updatedCoords[1][0]);
        assertEquals(updatedCoords[0][1], updatedCoords[1][1]);

        assertEquals(currentPlayerXCoordinate + 1, updatedCoords[1][0]);
        assertEquals(currentPlayerYCoordinate, updatedCoords[1][1]);
    }

    @Test
    public void TestLogDeathThreeLives() {
        int currentLogXCoordinate = 3;
        int currentLogYCoordinate = 12;

        int currentPlayerXCoordinate = 3;
        int currentPlayerYCoordinate = 12;

        int[] newPlayerCoords = UnitTestingFunctions.updatePlayer("left", currentPlayerXCoordinate, currentPlayerYCoordinate);

        assertEquals(2, UnitTestingFunctions.decreaseLivesWithLogCollision(3,
                newPlayerCoords[0], newPlayerCoords[1], currentLogXCoordinate, currentLogYCoordinate));


        assertEquals(3, UnitTestingFunctions.decreaseLivesWithLogCollision(3,
                currentPlayerXCoordinate, currentPlayerYCoordinate, currentLogXCoordinate, currentLogYCoordinate));
    }

    @Test
    public void TestLogDeathTwoLives() {


        int currentLogXCoordinate = 5;
        int currentLogYCoordinate = 13;

        int currentPlayerXCoordinate = 5;
        int currentPlayerYCoordinate = 13;

        int[] newPlayerCoords = UnitTestingFunctions.updatePlayer("right", currentPlayerXCoordinate, currentPlayerYCoordinate);

        assertEquals(1, UnitTestingFunctions.decreaseLivesWithLogCollision(2,
                newPlayerCoords[0], newPlayerCoords[1], currentLogXCoordinate, currentLogYCoordinate));


        assertEquals(2, UnitTestingFunctions.decreaseLivesWithLogCollision(2,
                currentPlayerXCoordinate, currentPlayerYCoordinate, currentLogXCoordinate, currentLogYCoordinate));


    }

    @Test
    public void TestLogDeathOneLife() {


        int currentLogXCoordinate = 10;
        int currentLogYCoordinate = 14;

        int currentPlayerXCoordinate = 10;
        int currentPlayerYCoordinate = 14;

        int[] newPlayerCoords = UnitTestingFunctions.updatePlayer("left", currentPlayerXCoordinate, currentPlayerYCoordinate);

        assertEquals(0, UnitTestingFunctions.decreaseLivesWithLogCollision(1,
                newPlayerCoords[0], newPlayerCoords[1], currentLogXCoordinate, currentLogYCoordinate));


        assertEquals(1, UnitTestingFunctions.decreaseLivesWithLogCollision(1,
                currentPlayerXCoordinate, currentPlayerYCoordinate, currentLogXCoordinate, currentLogYCoordinate));

    }

    @Test
    public void respawnFromLogCollision() {
        assertEquals(true, UnitTestingFunctions.respawnFrogFromLogCollision(3, 5, 6, 5, 6));

        assertEquals(false, UnitTestingFunctions.respawnFrogFromLogCollision(2, 5, 6, 7 ,8));

        assertEquals(true, UnitTestingFunctions.respawnFrogFromLogCollision(2, 7, 8, 7, 8));

        assertEquals(false, UnitTestingFunctions.respawnFrogFromLogCollision(1, 5, 6, 9, 10));
    }

}
