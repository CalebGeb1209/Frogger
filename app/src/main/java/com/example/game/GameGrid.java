package com.example.game;

import android.app.Activity;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import java.util.Random;



public class GameGrid {
    private int height;
    private int width;
    private Tile[][] grid;
    private int tilePxFactor;
    private int[] playerCoord;

    private Activity activity;

    public GameGrid(Activity activity, int tilePxFactor) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.height = metrics.heightPixels;
        this.width = metrics.widthPixels;
        this.tilePxFactor = tilePxFactor;
        this.activity = activity;
        this.grid =
                new Tile[this.height / this.tilePxFactor][this.width / this.tilePxFactor];
        this.playerCoord = new int[]{(grid[0].length / 2) - 1, grid.length - 2};
    } // GameGrid

    public void populate(GridLayout layout) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                ImageView img = new ImageView(activity.getApplicationContext());
                ViewGroup.LayoutParams tileParams = (ViewGroup.LayoutParams) img.getLayoutParams();
                if (tileParams == null) {
                    tileParams = new GridLayout.LayoutParams();
                }
                tileParams.width = tilePxFactor; // Set the width of the image view to 200 pixels
                tileParams.height = tilePxFactor; // Set the height of the image view to 200 pixels
                // Set the new LayoutParams for the image view
                if (i < 2) {
                    grid[i][j] = new GoalTile();
                    img.setImageResource(R.mipmap.goaltileart_foreground);
                } else if (i < 9) {
                    grid[i][j] = new RiverTile();
                    img.setImageResource(R.mipmap.rivertileart_foreground);
                } else if (i < 15 && i > 9) {
                    grid[i][j] = new RoadTile();
                    img.setImageResource(R.mipmap.roadtileart_foreground);
                } else {
                    grid[i][j] = new SafeTile();
                    img.setImageResource(R.mipmap.safetileart_foreground);
                }
                //tileParams.leftMargin = i * this.tilePxFactor;
                //tileParams.topMargin = j * this.tilePxFactor;
                img.setLayoutParams(tileParams);
                layoutParams.rowSpec = GridLayout.spec(i);
                layoutParams.columnSpec = GridLayout.spec(j);
                layoutParams.width = tilePxFactor;
                layoutParams.height = tilePxFactor;
                layout.addView(img, layoutParams);
            }
        }
        //Random rand = new Random();
        //Vehicle[] bobs = new Vehicle[5];
        //Handler handler = new Handler();
        //for (int i = 0; i < 5; i++) {
//            int n = rand.nextInt(3) + 1;
//            bobs[i] = new Vehicle(n, activity, i + 10, width, tilePxFactor);
//
//        }
//        Runnable runnable = new Runnable() {
//            public void run() {
//                Vehicle.updateVehicleX(bobs);
//                //Thread thread = new Thread();
//                //thread.start();
//                handler.postDelayed(this, 0);
//            }
//        };
//        handler.postDelayed(runnable, 0);
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
                || (direction.equals("R") && getPlayerX() == grid[0].length - 2);
    } // isAtXBoundary

    public boolean isAtYBoundary(String direction) {
        return (direction.equals("U") && getPlayerY() == 1)
                || (direction.equals("D") && getPlayerY() == grid.length - 2);
    } // isAtYBoundary
} // GameGrid
