package com.example.game;

public class PlayerMovement {
    private int[] playerCoord;
    private GameGrid grid;

    public PlayerMovement(GameGrid grid) {
        this.playerCoord = new int[]{(grid.getGrid()[0].length / 2) - 1, grid.getGrid().length - 2};
        this.grid = grid;
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

    public boolean contactMade() {
        return getPlayerY() < 9 && getPlayerY() > 2;
    } // contactMade

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
    } // resetCoords
}
