package com.example.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SprintFourUnitTests {

    @Test
    public void decreaseLivesFromThreeLives() {
        int playerXCoordinate = 5;
        int playerYCoordinate = 6;
        assertEquals(2, UnitTestingFunctions.DecreaseLives(3, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 3;
        playerYCoordinate = 6;
        assertEquals(3, UnitTestingFunctions.DecreaseLives(3, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 6;
        playerYCoordinate = 6;
        assertEquals(2, UnitTestingFunctions.DecreaseLives(3, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 7;
        playerYCoordinate = 6;
        assertEquals(3, UnitTestingFunctions.DecreaseLives(3, playerXCoordinate, playerYCoordinate));
    }

    @Test
    public void decreaseLivesFromTwoLives() {
        int playerXCoordinate = 5;
        int playerYCoordinate = 6;
        assertEquals(1, UnitTestingFunctions.DecreaseLives(2, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 3;
        playerYCoordinate = 6;
        assertEquals(2, UnitTestingFunctions.DecreaseLives(2, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 6;
        playerYCoordinate = 6;
        assertEquals(1, UnitTestingFunctions.DecreaseLives(2, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 7;
        playerYCoordinate = 6;
        assertEquals(2, UnitTestingFunctions.DecreaseLives(2, playerXCoordinate, playerYCoordinate));
    }
    @Test
    public void decreaseLivesFromOneLife() {
        int playerXCoordinate = 5;
        int playerYCoordinate = 6;
        assertEquals(0, UnitTestingFunctions.DecreaseLives(1, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 3;
        playerYCoordinate = 6;
        assertEquals(1, UnitTestingFunctions.DecreaseLives(1, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 6;
        playerYCoordinate = 6;
        assertEquals(0, UnitTestingFunctions.DecreaseLives(1, playerXCoordinate, playerYCoordinate));

        playerXCoordinate = 7;
        playerYCoordinate = 6;
        assertEquals(1, UnitTestingFunctions.DecreaseLives(1, playerXCoordinate, playerYCoordinate));
    }

    @Test
    public void reportScore() {

        assertEquals(0, UnitTestingFunctions.changeScore(15, 5, 6));

        assertEquals(20, UnitTestingFunctions.changeScore(20, 3, 6));

        assertEquals(0, UnitTestingFunctions.changeScore(30, 6, 6));

        assertEquals(3, UnitTestingFunctions.changeScore(3, 7, 6));
    }

}