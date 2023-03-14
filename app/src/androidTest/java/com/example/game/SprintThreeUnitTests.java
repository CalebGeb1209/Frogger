package com.example.game;

import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class SprintThreeUnitTests {

    @Mock
    private TextView pointDeterminationMock;

    @Mock
    private TextView levelDetermination;
    @Mock
    private TextView nameDetermination;
    @Mock
    private TextView pointDetermination;

    private GameScreen gameScreen;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gameScreen = new GameScreen();
        gameScreen.pointDetermination = pointDeterminationMock;
        gameScreen.grid = new GameGrid(gameScreen, 120);
        gameScreen.grid.setPlayerY(10);
        gameScreen.setLevelDetermination(levelDetermination);
        gameScreen.setNameDetermination(nameDetermination);
        gameScreen.setPointDetermination(pointDetermination);
    }

    @Test
    public void testUpdateScore() {
        gameScreen.updateScore();
        verify(pointDeterminationMock).setText("2 Pts");
        gameScreen.grid.setPlayerY(5);
        gameScreen.updateScore();
        verify(pointDeterminationMock).setText("4 Pts");
        gameScreen.grid.setPlayerY(2);
        gameScreen.updateScore();
        verify(pointDeterminationMock).setText("7 Pts");
        gameScreen.grid.setPlayerY(1);
        gameScreen.updateScore();
        verify(pointDeterminationMock).setText("8 Pts");
        gameScreen.grid.setPlayerY(0);
        gameScreen.updateScore();
        verify(pointDeterminationMock, times(1)).setText("9 Pts");
    }
}