package com.example.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SprintFourUnitTests {

    @Test
    public void decreaseLivesFromWaterTileThreeLives() {

        assertEquals(2, UnitTestingFunctions.DecreaseLivesWithWaterTile(3, 5, 6));


        assertEquals(3, UnitTestingFunctions.DecreaseLivesWithWaterTile(3, 3, 6));


        assertEquals(2, UnitTestingFunctions.DecreaseLivesWithWaterTile(3, 6, 6));

        assertEquals(3, UnitTestingFunctions.DecreaseLivesWithWaterTile(3, 7, 6));
    }

    @Test
    public void decreaseLivesFromWaterTileTwoLives() {

        assertEquals(1, UnitTestingFunctions.DecreaseLivesWithWaterTile(2, 5, 6));


        assertEquals(2, UnitTestingFunctions.DecreaseLivesWithWaterTile(2, 3, 6));

        assertEquals(1, UnitTestingFunctions.DecreaseLivesWithWaterTile(2, 6, 6));

        assertEquals(2, UnitTestingFunctions.DecreaseLivesWithWaterTile(2, 7, 6));
    }
    @Test
    public void decreaseLivesFromWaterTileOneLife() {

        assertEquals(0, UnitTestingFunctions.DecreaseLivesWithWaterTile(1, 5, 6));


        assertEquals(1, UnitTestingFunctions.DecreaseLivesWithWaterTile(1, 3, 6));


        assertEquals(0, UnitTestingFunctions.DecreaseLivesWithWaterTile(1, 6, 6));


        assertEquals(1, UnitTestingFunctions.DecreaseLivesWithWaterTile(1, 7, 6));
    }

    @Test
    public void reportScore() {

        assertEquals(0, UnitTestingFunctions.changeScore(15, 5, 6));

        assertEquals(20, UnitTestingFunctions.changeScore(20, 3, 6));

        assertEquals(0, UnitTestingFunctions.changeScore(30, 6, 6));

        assertEquals(3, UnitTestingFunctions.changeScore(3, 7, 6));
    }

    @Test
    public void gameOverTest() {
        assertEquals(true, UnitTestingFunctions.goToGameOverScreen( 1, 5, 6));
        assertEquals(false, UnitTestingFunctions.goToGameOverScreen( 2, 5, 6));
        assertEquals(false, UnitTestingFunctions.goToGameOverScreen(3, 5, 6));
    }
    @Test
    public void decreaseLivesFromCollisionThreeLives() {

        assertEquals(2, UnitTestingFunctions.decreaseLivesWithCollision(3, 5, 6, 5, 6));


        assertEquals(3, UnitTestingFunctions.decreaseLivesWithCollision(3, 5, 6, 7 ,8));

        assertEquals(2, UnitTestingFunctions.decreaseLivesWithCollision(3, 7, 8, 7, 8));

        assertEquals(3, UnitTestingFunctions.decreaseLivesWithCollision(3, 5, 6, 9, 10));
    }
    @Test
    public void decreaseLivesFromCollisionTwoLives() {
        assertEquals(1, UnitTestingFunctions.decreaseLivesWithCollision(2, 5, 6, 5, 6));


        assertEquals(2, UnitTestingFunctions.decreaseLivesWithCollision(2, 5, 6, 7 ,8));

        assertEquals(1, UnitTestingFunctions.decreaseLivesWithCollision(2, 7, 8, 7, 8));

        assertEquals(2, UnitTestingFunctions.decreaseLivesWithCollision(2, 5, 6, 9, 10));
    }

    @Test
    public void decreaseLivesFromCollisionOneLife() {

        assertEquals(0, UnitTestingFunctions.decreaseLivesWithCollision(1, 5, 6, 5, 6));


        assertEquals(1, UnitTestingFunctions.decreaseLivesWithCollision(1, 5, 6, 7 ,8));

        assertEquals(0, UnitTestingFunctions.decreaseLivesWithCollision(1, 7, 8, 7, 8));

        assertEquals(1, UnitTestingFunctions.decreaseLivesWithCollision(1, 5, 6, 9, 10));
    }


}