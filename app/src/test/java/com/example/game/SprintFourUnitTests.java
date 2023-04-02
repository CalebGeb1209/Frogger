package com.example.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SprintFourUnitTests {

    @Test
    public void increaseScoreByOne() {
        int score = 5;
        assertEquals(6, UnitTestingFunctions.increaseScoreByOne(score));
    }
}