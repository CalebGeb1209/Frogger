package com.example.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SprintFourUnitTests {

    @Test
    public void decreaseLivesFromWaterTileThreeLives() {

        assertEquals(2, UnitTestingFunctions.decreaseLivesWithWaterTile(3, 5, 6));


        assertEquals(3, UnitTestingFunctions.decreaseLivesWithWaterTile(3, 3, 6));


        assertEquals(2, UnitTestingFunctions.decreaseLivesWithWaterTile(3, 6, 6));

        assertEquals(3, UnitTestingFunctions.decreaseLivesWithWaterTile(3, 7, 6));
    }

    @Test
    public void decreaseLivesFromWaterTileTwoLives() {

        assertEquals(1, UnitTestingFunctions.decreaseLivesWithWaterTile(2, 5, 6));


        assertEquals(2, UnitTestingFunctions.decreaseLivesWithWaterTile(2, 3, 6));

        assertEquals(1, UnitTestingFunctions.decreaseLivesWithWaterTile(2, 6, 6));

        assertEquals(2, UnitTestingFunctions.decreaseLivesWithWaterTile(2, 7, 6));
    }
    @Test
    public void decreaseLivesFromWaterTileOneLife() {

        assertEquals(0, UnitTestingFunctions.decreaseLivesWithWaterTile(1, 5, 6));


        assertEquals(1, UnitTestingFunctions.decreaseLivesWithWaterTile(1, 3, 6));


        assertEquals(0, UnitTestingFunctions.decreaseLivesWithWaterTile(1, 6, 6));


        assertEquals(1, UnitTestingFunctions.decreaseLivesWithWaterTile(1, 7, 6));
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

    @Test
    public void respawnFromWaterCollision() {
        assertEquals(true, UnitTestingFunctions.respawnFrogFromWaterCollision(3, 5, 6));


        assertEquals(false, UnitTestingFunctions.respawnFrogFromWaterCollision(3, 3, 6));


        assertEquals(true, UnitTestingFunctions.respawnFrogFromWaterCollision(3, 6, 6));

        assertEquals(false, UnitTestingFunctions.respawnFrogFromWaterCollision(3, 7, 6));
    }

    @Test
    public void respawnFromVehicleCollision() {
        assertEquals(true, UnitTestingFunctions.respawnFrogFromVehicleCollision(3, 5, 6, 5, 6));

        assertEquals(false, UnitTestingFunctions.respawnFrogFromVehicleCollision(2, 5, 6, 7 ,8));

        assertEquals(true, UnitTestingFunctions.respawnFrogFromVehicleCollision(2, 7, 8, 7, 8));

        assertEquals(false, UnitTestingFunctions.respawnFrogFromVehicleCollision(1, 5, 6, 9, 10));
    }

}