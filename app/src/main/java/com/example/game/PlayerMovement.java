package com.example.game;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayerMovement {
    private int[] playerCoord;
    private GameGrid grid;
    private View game;
    private ConstraintLayout.LayoutParams spriteParams;
    private ImageButton activeSprite;

    public PlayerMovement(GameGrid grid, View game, ImageButton activeSprite) {
        this.playerCoord = new int[]{(grid.getGrid()[0].length / 2) - 1, grid.getGrid().length - 2};
        this.spriteParams = (ConstraintLayout.LayoutParams) activeSprite.getLayoutParams();
        this.activeSprite = activeSprite;
        this.grid = grid;
        this.game = game;
    } // playerMovement

    public int getPlayerX() {
        return playerCoord[0];
    } // getPlayerX

    public int getPlayerY() {
        return playerCoord[1];
    } // getPlayerY

    public void setPlayerX(int x) {
        playerCoord[0] = x;
    } // setPlayerX

    public void setPlayerY(int y) {
        playerCoord[1] = y;
    } // setPlayerY

    public int getPlayerXCoordinate() {
        return playerCoord[0] * grid.getTilePxFactor();
    } // getPlayerXCoordinate

    public int getPlayerYCoordinate() {
        return playerCoord[1] * grid.getTilePxFactor();
    } // getPlayerYCoordinate

    public boolean isAtXBoundary(String direction) {
        return (direction.equals("L") && getPlayerX() == 0)
                || (direction.equals("R") && getPlayerX() == grid.getGrid()[0].length - 2);
    } // isAtXBoundary

    public boolean isAtYBoundary(String direction) {
        return (direction.equals("U") && getPlayerY() == 1)
                || (direction.equals("D") && getPlayerY() == grid.getGrid().length - 2);
    } // isAtYBoundary

    public void resetCoords() {
        setPlayerX((grid.getGrid()[0].length / 2) - 1);
        setPlayerY(grid.getGrid().length - 2);
        spriteParams.topMargin = getPlayerYCoordinate();
        spriteParams.leftMargin = getPlayerXCoordinate();
        activeSprite.setLayoutParams(spriteParams);
    } // resetCoords

    @SuppressLint("ClickableViewAccessibility")
    public void setupNavigation() {
        spriteParams.leftMargin = getPlayerXCoordinate();
        spriteParams.topMargin = getPlayerYCoordinate();

        game.setOnTouchListener((View.OnTouchListener) (view, event) -> {
            int xCoordinate = (int) event.getX();
            int yCoordinate = (int) event.getY();

            if (xCoordinate < (grid.getWidth() / 3)) {
                if (!isAtXBoundary("L")) {
                    spriteParams.leftMargin = getPlayerXCoordinate() - grid.getTilePxFactor();
                    setPlayerX(getPlayerX() - 1);
                }
            } else if (xCoordinate < (grid.getWidth() / 3 * 2)) {
                if (yCoordinate <= (grid.getHeight()) / 2 && !isAtYBoundary("U")) {
                    spriteParams.topMargin = getPlayerYCoordinate() - grid.getTilePxFactor();
                    setPlayerY(getPlayerY() - 1);
                } else if (yCoordinate > (grid.getHeight() / 2) && !isAtYBoundary("D")) {
                    spriteParams.topMargin = getPlayerYCoordinate() + grid.getTilePxFactor();
                    setPlayerY(getPlayerY() + 1);
                }
            } else {
                if (!isAtXBoundary("R")) {
                    spriteParams.leftMargin = getPlayerXCoordinate() + grid.getTilePxFactor();
                    setPlayerX(getPlayerX() + 1);
                }
            }
            activeSprite.setLayoutParams(spriteParams);
            return false;
        });
    } // setupNavigation

}
