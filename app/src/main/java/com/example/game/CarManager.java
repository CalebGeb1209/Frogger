package com.example.game;

import android.view.View;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CarManager {
    private ImageButton car1;
    private ImageButton car2;
    private ImageButton car3;
    private ImageButton car4;
    private ImageButton car5;
    private ImageButton[] cars;
    private int[] displacements;
    private GameGrid grid;

    public CarManager(GameGrid grid, ImageButton car1, ImageButton car2, ImageButton car3,
                      ImageButton car4, ImageButton car5) {
        this.grid = grid;
        this.car1 = car1;
        this.car2 = car2;
        this.car3 = car3;
        this.car4 = car4;
        this.car5 = car5;
        this.cars = new ImageButton[]{car1, car2, car3, car4, car5};
        this.displacements = new int[] {100, 150, 50, 150, 75};
    } // CarManager

    public ImageButton getCar(int index) {
        return cars[index - 1];
    } // getCar1

    public void setUpCars() {
        for (int i = 0; i < cars.length; i++) {
            cars[i].setVisibility(View.VISIBLE);
            ConstraintLayout.LayoutParams carParams
                    = (ConstraintLayout.LayoutParams) cars[i].getLayoutParams();
            carParams.topMargin = grid.getTilePxFactor() * (14 - i);
            cars[i].setLayoutParams(carParams);
        }
    } // setUpCars

    public void manageCars() {
        for (int i = 0; i < cars.length; i++) {
            ConstraintLayout.LayoutParams carParams
                    = (ConstraintLayout.LayoutParams) cars[i].getLayoutParams();
            if (carParams.leftMargin <= 0) {
                carParams.leftMargin = 1000;
            } else {
                carParams.leftMargin -= displacements[i];
            }
            cars[i].setLayoutParams(carParams);
        }
    } // manageCars
}
