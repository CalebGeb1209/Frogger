package com.example.game;

import android.app.Activity;
import android.util.DisplayMetrics;

public class GameGrid {
    private int height;
    private int width;
    private Tile[][] grid;
    private int tilePxFactor;
    private int[] playerCoord;

    public GameGrid(Activity activity, int tilePxFactor) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.height = metrics.heightPixels;
        this.width = metrics.widthPixels;
        this.tilePxFactor = tilePxFactor;
        this.grid =
                new Tile[this.height / this.tilePxFactor - 1][this.width / this.tilePxFactor - 1];
        this.playerCoord = new int[]{(grid[0].length / 2) - 1, grid.length - 1};
    } // GameGrid

    public void populate() {

    } // populate

    public void buildRoadBiome() {

    } // buildRoadBiome

    public void buildRiverBiome() {

    } // buildRiverBiome

    public void buildSafeBiome() {

    } // buildSafeBiome

    public void buildGoalBiome() {

    } // buildGoalBiome

    public int getWidth() {
        return width;
    } // getWidth

    public int getHeight() {
        return height;
    } // getHeight

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
        return playerCoord[0] * tilePxFactor;
    } // getPlayerXCoordinate

    public int getPlayerYCoordinate() {
        return playerCoord[1] * tilePxFactor;
    } // getPlayerYCoordinate

    public int getTilePxFactor() {
        return tilePxFactor;
    } // getTilePxFactor

    public boolean isAtXBoundary(String direction) {
        return (direction.equals("L") && getPlayerX() == 0)
                || (direction.equals("R") && getPlayerX() == grid[0].length - 1);
    } // isAtXBoundary

    public boolean isAtYBoundary() {
        return getPlayerY() == 1;
    } // isAtYBoundary
} // GameGrid
