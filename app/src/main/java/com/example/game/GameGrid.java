package com.example.game;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

public class GameGrid {
    private int height;
    private int width;
    private Tile[][] grid;
    private int tilePxFactor;
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
    } // GameGrid

    public Tile[][] getGrid() {
        return grid;
    } // getGrid

    public int getTilePxFactor() {
        return tilePxFactor;
    } // getTilePxFactor

    public int getWidth() {
        return width;
    } // getWidth

    public int getHeight() {
        return height;
    } // getHeight

    public void populate(GridLayout layout) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                ImageView img = new ImageView(activity.getApplicationContext());
                populateBlock(i, j, img);
                setPlacement(img, layout, i, j);
            }
        }
    } // populate

    public void populateBlock(int i, int j, ImageView img) {
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
    } // populateBlock

    public void setPlacement(ImageView img, GridLayout layout, int i, int j) {
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        ViewGroup.LayoutParams tileParams = (ViewGroup.LayoutParams) img.getLayoutParams();

        if (tileParams == null) {
            tileParams = new GridLayout.LayoutParams();
        }
        tileParams.width = tilePxFactor;
        tileParams.height = tilePxFactor;

        img.setLayoutParams(tileParams);
        layoutParams.rowSpec = GridLayout.spec(i);
        layoutParams.columnSpec = GridLayout.spec(j);
        layoutParams.width = tilePxFactor;
        layoutParams.height = tilePxFactor;
        layout.addView(img, layoutParams);
    } // manageLocations
} // GameGrid
