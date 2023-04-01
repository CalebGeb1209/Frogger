package com.example.game;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CarManager {
    private ImageButton car1;
    private ImageButton car2;
    private ImageButton car3;
    private ImageButton car4;
    private ImageButton car5;
    private ConstraintLayout.LayoutParams car1Params;
    private ConstraintLayout.LayoutParams car2Params;
    private ConstraintLayout.LayoutParams car3Params;
    private ConstraintLayout.LayoutParams car4Params;
    private ConstraintLayout.LayoutParams car5Params;
    GameGrid grid;

    public CarManager(GameGrid grid, ImageButton car1, ImageButton car2, ImageButton car3, ImageButton car4, ImageButton car5) {
        this.grid = grid;
        this.car1 = car1;
        this.car2 = car2;
        this.car3 = car3;
        this.car4 = car4;
        this.car5 = car5;
    } // CarManager

    public ImageButton getCar1() {
        return car1;
    } // getCar1

    public ImageButton getCar2() {
        return car2;
    } // getCar2

    public ImageButton getCar3() {
        return car3;
    } // getCar3

    public ImageButton getCar4() {
        return car4;
    } // getCar4

    public ImageButton getCar5() {
        return car5;
    } // getCar5

    public void setUpCars() {
        car1.setVisibility(View.VISIBLE);
        car1Params = (ConstraintLayout.LayoutParams) car1.getLayoutParams();
        car1Params.topMargin = grid.getTilePxFactor() * 14;
        car1.setLayoutParams(car1Params);

        car2.setVisibility(View.VISIBLE);
        car2Params = (ConstraintLayout.LayoutParams) car2.getLayoutParams();
        car2Params.topMargin = grid.getTilePxFactor() * 13;
        car2.setLayoutParams(car1Params);

        car3.setVisibility(View.VISIBLE);
        car3Params = (ConstraintLayout.LayoutParams) car3.getLayoutParams();
        car3Params.topMargin = grid.getTilePxFactor() * 12;
        car3.setLayoutParams(car3Params);

        car4.setVisibility(View.VISIBLE);
        car4Params = (ConstraintLayout.LayoutParams) car4.getLayoutParams();
        car4Params.topMargin = grid.getTilePxFactor() * 11;
        car4.setLayoutParams(car4Params);

        car5.setVisibility(View.VISIBLE);
        car5Params = (ConstraintLayout.LayoutParams) car5.getLayoutParams();
        car5Params.topMargin = grid.getTilePxFactor() * 10;
        car5.setLayoutParams(car5Params);
    }

    public void manageCars() {
        if (car1Params.leftMargin <= 0) {
            car1Params.leftMargin = 1000;
        } else {
            car1Params.leftMargin = car1Params.leftMargin - 100;
        }
        car1.setLayoutParams(car1Params);

        if (car2Params.leftMargin <= 0) {
            car2Params.leftMargin = 1000;
        } else {
            car2Params.leftMargin = car2Params.leftMargin - 150;
        }
        car2.setLayoutParams(car2Params);

        if (car3Params.leftMargin <= 0) {
            car3Params.leftMargin = 1000;
        } else {
            car3Params.leftMargin = car3Params.leftMargin - 50;
        }
        car3.setLayoutParams(car3Params);

        if (car4Params.leftMargin >= 1000) {
            car4Params.leftMargin = 0;
        } else {
            car4Params.leftMargin = car4Params.leftMargin + 150;
        }
        car4.setLayoutParams(car4Params);

        if (car5Params.leftMargin >= 1000) {
            car5Params.leftMargin = 0;
        } else {
            car5Params.leftMargin = car5Params.leftMargin + 75;
        }
        car5.setLayoutParams(car5Params);
    }

}
