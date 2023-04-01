package com.example.game;

import android.graphics.Rect;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CollisionManager {
    private Rect car1Rect;
    private Rect car2Rect;
    private Rect car3Rect;
    private Rect car4Rect;
    private Rect car5Rect;
    private Rect playerRect;
    private ImageButton activeSprite;
    private CarManager carManager;
    private ScoreManager scoreManager;
    private PlayerMovement playerMovement;


    public CollisionManager(CarManager carManager, ImageButton activeSprite, ScoreManager scoreManager, PlayerMovement playerMovement) {
        this.carManager = carManager;
        this.scoreManager = scoreManager;
        this.playerMovement = playerMovement;
        this.activeSprite = activeSprite;
        car1Rect = new Rect();
        car2Rect = new Rect();
        car3Rect = new Rect();
        car4Rect = new Rect();
        car5Rect = new Rect();
        playerRect = new Rect();
    } // CollisionManager

    public void shrinkBox(Rect rect) {
        int currHeight = rect.bottom - rect.top;
        int newHeight = (int) (currHeight * 0.2);
        int displacement = (int)((currHeight - newHeight) / 3);
        rect.top += displacement;
        rect.bottom = rect.top + newHeight;
    } // shrinkBox

    public boolean getCarCollision() {
        activeSprite.getGlobalVisibleRect(playerRect);
        carManager.getCar1().getGlobalVisibleRect(car1Rect);
        carManager.getCar2().getGlobalVisibleRect(car2Rect);
        carManager.getCar3().getGlobalVisibleRect(car3Rect);
        carManager.getCar4().getGlobalVisibleRect(car4Rect);
        carManager.getCar5().getGlobalVisibleRect(car5Rect);

        shrinkBox(playerRect);
        shrinkBox(car1Rect);
        shrinkBox(car2Rect);
        shrinkBox(car3Rect);
        shrinkBox(car4Rect);
        shrinkBox(car5Rect);

        if (playerRect.intersect(car1Rect) || playerRect.intersect(car2Rect) || playerRect.intersect(car3Rect) || playerRect.intersect(car4Rect) || playerRect.intersect(car5Rect)) {
            resetSprite();
            return true;
        }
        return false;
    } // getCarCollision

    public void resetSprite() {
        scoreManager.resetScore();
        ConstraintLayout.LayoutParams spriteParams = (ConstraintLayout.LayoutParams) activeSprite.getLayoutParams();
        spriteParams.topMargin = playerMovement.getPlayerYCoordinate();
        spriteParams.leftMargin = playerMovement.getPlayerXCoordinate();
        activeSprite.setLayoutParams(spriteParams);
    }
}
