package com.example.game;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Vehicle extends AppCompatActivity implements Runnable {
    private int positionY;
    private int positionX;
    private int speed;

    private int vehicleType;

    private static int width;
    private ConstraintLayout.LayoutParams spriteParams;

    private ImageView img;

    private Drawable car;//thing for type of car/image of car

        public Vehicle(int vehicleType, Activity activity, int lane, int width, int tilePxFactor) {
            ConstraintLayout parent = findViewById(R.id.parent);
            img = new ImageView(activity.getApplicationContext());
            parent.addView(img);
            Log.d("googoogahgah", String.valueOf(img));
            spriteParams = (ConstraintLayout.LayoutParams) img.getLayoutParams();
            spriteParams.leftMargin = width;
            spriteParams.topMargin = 9 * tilePxFactor + lane * tilePxFactor;
            if (vehicleType == 1) {
                this.speed = 1;
                img.setImageResource(R.drawable.frogspritegreenresized);
                spriteParams.width = tilePxFactor;
                spriteParams.height = tilePxFactor;
            } else if (vehicleType == 2) {
                this.speed = 2;
                img.setImageResource(R.drawable.frogspriteredresized);
                spriteParams.width = tilePxFactor;
                spriteParams.height = tilePxFactor;
            } else {
                this.speed = 3;
                img.setImageResource(R.drawable.frogspriteblue__1_);
                spriteParams.width = tilePxFactor;
                spriteParams.height = tilePxFactor;
            }
            this.positionY = lane;
            this.positionX = 0;
            img.setLayoutParams(spriteParams);
        }

        public static void updateVehicleX(Vehicle[] bobs) {
            for (int i = 0; i < 5; i++) {
                if (bobs[i].spriteParams.leftMargin <= 0) {
                    bobs[i].spriteParams.leftMargin = width;
                } else {
                    bobs[i].spriteParams.leftMargin = bobs[i].spriteParams.leftMargin - 5 * bobs[i].speed;
                }
            }
        }

        public int getVehicleX() {
            return positionX;
        }

        public int getVehicleY() {
            return positionY;
        }

        public int getSpeed() {
            return speed;
        }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
