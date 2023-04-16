package com.example.game;
import android.view.View;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class LogManager {
    private ImageButton log1;
    private ImageButton log2;
    private ImageButton log3;
    private ImageButton log4;
    private ImageButton log5;
    private ImageButton log6;
    private ImageButton log7;
    private ImageButton[] logs;
    private int[] displacements;
    private GameGrid grid;
    private ImageButton activeSprite;
    private GameScreen screen;
    private CollisionManager collisionManager;

    public LogManager(ImageButton log1, ImageButton log2, ImageButton log3,
                      ImageButton log4, ImageButton log5, ImageButton log6, ImageButton log7) {
        this.log1 = log1;
        this.log2 = log2;
        this.log3 = log3;
        this.log4 = log4;
        this.log5 = log5;
        this.log6 = log6;
        this.log7 = log7;
        this.logs = new ImageButton[]{log1, log2, log3, log4, log5, log6, log7};
        this.displacements = new int[] {5, 40, 80, 120, 160, 89, 160};
    } // LogManager

    public void setupLogEtc(GameGrid grid, GameScreen screen, ImageButton activeSprite) {
        this.grid = grid;
        this.screen = screen;
        this.activeSprite = activeSprite;
        this.collisionManager = screen.getCollisionManager();
    } // setupLogEtc

    public ImageButton getLog(int index) {
        return logs[index];
    } // getLog

    public void setUpLogs() {
        for (int i = 0; i < logs.length; i++) {
            logs[i].setVisibility(View.VISIBLE);
            ConstraintLayout.LayoutParams logParams
                    = (ConstraintLayout.LayoutParams) logs[i].getLayoutParams();
            logParams.topMargin = grid.getTilePxFactor() * (8 - i);
            logs[i].setLayoutParams(logParams);
        }
    } // setUpLogs

    public void manageLogs() {
        this.collisionManager = screen.getCollisionManager();
        for (int i = 0; i < logs.length; i++) {
            ConstraintLayout.LayoutParams logParams
                    = (ConstraintLayout.LayoutParams) logs[i].getLayoutParams();
            if (logParams.leftMargin <= 0) {
                logParams.leftMargin = 1000;
                if (collisionManager.onLog(i)) {
                    screen.handleCollision();
                } // if
            } else {
                logParams.leftMargin -= displacements[i];
                if (collisionManager.onLog(i)) {
                    ConstraintLayout.LayoutParams spriteParams
                            = (ConstraintLayout.LayoutParams) activeSprite.getLayoutParams();
                    spriteParams.leftMargin -= displacements[i];
                    activeSprite.setLayoutParams(spriteParams);
                } // if
            }
            logs[i].setLayoutParams(logParams);
        }
    } // manageLogs
}
