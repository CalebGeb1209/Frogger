package com.example.game;

import android.graphics.Rect;
import android.widget.ImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CollisionManager {
    private Rect car1Rect;
    private Rect car2Rect;
    private Rect car3Rect;
    private Rect car4Rect;
    private Rect car5Rect;
    private Rect log1Rect;
    private Rect log2Rect;
    private Rect log3Rect;
    private Rect log4Rect;
    private Rect log5Rect;
    private Rect log6Rect;
    private Rect log7Rect;
    private Rect playerRect;
    private Rect[] carRects;
    private Rect[] logRects;
    private ImageButton activeSprite;
    private CarManager carManager;
    private LogManager logManager;
    private ScoreManager scoreManager;
    private PlayerMovement playerMovement;

    public CollisionManager(CarManager carManager, LogManager logManager, ImageButton activeSprite,
                            ScoreManager scoreManager, PlayerMovement playerMovement) {
        this.carManager = carManager;
        this.scoreManager = scoreManager;
        this.playerMovement = playerMovement;
        this.activeSprite = activeSprite;
        this.logManager = logManager;
        car1Rect = new Rect();
        car2Rect = new Rect();
        car3Rect = new Rect();
        car4Rect = new Rect();
        car5Rect = new Rect();
        log1Rect = new Rect();
        log2Rect = new Rect();
        log3Rect = new Rect();
        log4Rect = new Rect();
        log5Rect = new Rect();
        log6Rect = new Rect();
        log7Rect = new Rect();
        logRects = new Rect[]{log1Rect, log2Rect, log3Rect, log4Rect, log5Rect, log6Rect, log7Rect};
        carRects = new Rect[]{car1Rect, car2Rect, car3Rect, car4Rect, car5Rect};
        playerRect = new Rect();
    } // CollisionManager

    public void shrinkBox(Rect rect) {
        int currHeight = rect.bottom - rect.top;
        int newHeight = (int) (currHeight * 0.2);
        int displacement = (int) ((currHeight - newHeight) / 3);
        rect.top += displacement;
        rect.bottom = rect.top + newHeight;
    } // shrinkBox

    public boolean onLogs() {
        activeSprite.getGlobalVisibleRect(playerRect);

        for (int i = 0; i < logRects.length; i++) {
            logManager.getLog(i).getGlobalVisibleRect(logRects[i]);
        }

        shrinkBox(playerRect);
        for (int i = 0; i < logRects.length; i++) {
            shrinkBox(logRects[i]);
        }

        return (playerRect.intersect(log1Rect) || playerRect.intersect(log2Rect)
                || playerRect.intersect(log3Rect) || playerRect.intersect(log4Rect)
                || playerRect.intersect(log5Rect) || playerRect.intersect(log6Rect)
                || playerRect.intersect(log7Rect));
    } // onLogs

    public boolean onLog(int index) {
        activeSprite.getGlobalVisibleRect(playerRect);
        Rect currRect = new Rect();
        logManager.getLog(index).getGlobalVisibleRect(currRect);

        shrinkBox(playerRect);
        shrinkBox(currRect);

        return playerRect.intersect(currRect);
    }

    public boolean getCarCollision() {
        activeSprite.getGlobalVisibleRect(playerRect);

        for (int i = 0; i < carRects.length; i++) {
            carManager.getCar(i + 1).getGlobalVisibleRect(carRects[i]);
        }

        shrinkBox(playerRect);
        for (int i = 0; i < carRects.length; i++) {
            shrinkBox(carRects[i]);
        }

        if (playerRect.intersect(car1Rect) || playerRect.intersect(car2Rect)
                || playerRect.intersect(car3Rect) || playerRect.intersect(car4Rect)
                    || playerRect.intersect(car5Rect)) {
            resetSprite();
            return true;
        }
        return false;
    } // getCarCollision

    public void resetSprite() {
        ConstraintLayout.LayoutParams spriteParams
                = (ConstraintLayout.LayoutParams) activeSprite.getLayoutParams();
        spriteParams.topMargin = playerMovement.getPlayerYCoordinate();
        spriteParams.leftMargin = playerMovement.getPlayerXCoordinate();
        activeSprite.setLayoutParams(spriteParams);
    } // resetSprite

    public boolean waterContactMade() {
        return playerMovement.getPlayerY() < 9 && playerMovement.getPlayerY() > 2;
    } // contactMade
}
