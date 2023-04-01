package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.FileOutputStream;
import java.io.PrintWriter;


public class GameScreen extends AppCompatActivity {

    private TextView levelDetermination;
    private TextView nameDetermination;
    private TextView pointDetermination;
    private ImageButton activeSprite;

    private ImageButton car1;
    private ImageButton car2;
    private ImageButton car3;
    private ImageButton car4;
    private ImageButton car5;
    private ImageView activeLives;
    private Bundle bundle;
    private GameGrid grid;
    private ConstraintLayout.LayoutParams spriteParams;

    private ConstraintLayout.LayoutParams car1Params;
    private ConstraintLayout.LayoutParams car2Params;
    private ConstraintLayout.LayoutParams car3Params;
    private ConstraintLayout.LayoutParams car4Params;
    private ConstraintLayout.LayoutParams car5Params;
    private int maxLevelReached;
    private int points;
    private int hitbox = 120;
    private Rect car1Rect = new Rect();
    private Rect car2Rect = new Rect();
    private Rect car3Rect = new Rect();
    private Rect car4Rect = new Rect();
    private Rect car5Rect = new Rect();
    private Rect playerRect = new Rect();
    private PlayerMovement pl_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        levelDetermination = (TextView) findViewById(R.id.difficulty);
        nameDetermination = (TextView) findViewById(R.id.name);
        pointDetermination = (TextView) findViewById(R.id.points);
        bundle = getIntent().getExtras();

        grid = new GameGrid(this, 120);
        GridLayout gridLayout = findViewById(R.id.grid);

        pl_move = new PlayerMovement(grid);

        setText();
        setDifficulty();
        setSprite();
        setupNavigation();
        setupScore();
        grid.populate(gridLayout);
        setUpCars();
        new CountDownTimer(1000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                manageCars();
                getCarCollision();
            }

            @Override
            public void onFinish() {
                // Code to execute when the timer is finished
            }
        }.start();

    } // onCreate

    private void setupScore() {
        maxLevelReached = 15;
        points = 0;
        pl_move.setPlayerY(15);
    } // setupPoints

    private void updateScore() {
        if (pl_move.getPlayerY() < maxLevelReached) {
            if (pl_move.getPlayerY() + 1 < 2) {
                points++;
            } else if (pl_move.getPlayerY() + 1 < 9) {
                points += 2;
            } else if (pl_move.getPlayerY() + 1 < 15 && pl_move.getPlayerY() + 1 > 9) {
                points += 3;
            } else {
                points++;
            }
            maxLevelReached--;
            pointDetermination.setText(points + " Pts");
        }
    } // updateScore

    @SuppressLint("ClickableViewAccessibility")
    private void setupNavigation() {
        spriteParams = (ConstraintLayout.LayoutParams) activeSprite.getLayoutParams();
        spriteParams.leftMargin = pl_move.getPlayerXCoordinate();
        spriteParams.topMargin = pl_move.getPlayerYCoordinate();

        View game = findViewById(R.id.parent);
        game.setOnTouchListener((View.OnTouchListener) (view, event) -> {
            int xCoordinate = (int) event.getX();
            int yCoordinate = (int) event.getY();

            if (xCoordinate < (grid.getWidth() / 3)) {
                if (!pl_move.isAtXBoundary("L")) {
                    spriteParams.leftMargin = pl_move.getPlayerXCoordinate() - grid.getTilePxFactor();
                    pl_move.setPlayerX(pl_move.getPlayerX() - 1);
                    Log.d("X", String.valueOf(pl_move.getPlayerX()));
                }
                getCarCollision();
            } else if (xCoordinate < (grid.getWidth() / 3 * 2)) {
                if (yCoordinate <= (grid.getHeight()) / 2 && !pl_move.isAtYBoundary("U")) {
                    spriteParams.topMargin = pl_move.getPlayerYCoordinate() - grid.getTilePxFactor();
                    pl_move.setPlayerY(pl_move.getPlayerY() - 1);
                    Log.d("Y", String.valueOf(pl_move.getPlayerY()));
                    if (pl_move.contactMade()) {
                        pointDetermination.setText("0 Points");
                        pl_move.resetCoords();
                        spriteParams.topMargin = pl_move.getPlayerYCoordinate();
                        spriteParams.leftMargin = pl_move.getPlayerXCoordinate();
                        if (findViewById(R.id.imageView6).getVisibility() == View.VISIBLE) { // hard 1
                            try {
                                FileOutputStream file = openFileOutput("score.txt", Context.MODE_PRIVATE);
                                PrintWriter writer = new PrintWriter(file);
                                writer.println(points);
                                writer.close();
                                file.close();
                            } catch (Exception e) {
                                Log.d("Exception", "Exception");
                            }

                            Intent intent = new Intent(this, GameOverScreen.class);
                            startActivity(intent);
                        } else if (findViewById(R.id.imageView2).getVisibility() == View.VISIBLE) { // easy 3
                            findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                            findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
                            points = 0;
                            maxLevelReached = 15;
                        } else if (findViewById(R.id.imageView5).getVisibility() == View.VISIBLE) { // medium 2
                            findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
                            findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);
                            points = 0;
                            maxLevelReached = 15;
                        }
                    }
                } else if (yCoordinate > (grid.getHeight() / 2) && !pl_move.isAtYBoundary("D")) {
                    spriteParams.topMargin = pl_move.getPlayerYCoordinate() + grid.getTilePxFactor();
                     pl_move.setPlayerY(pl_move.getPlayerY() + 1);
                }
                getCarCollision();
            } else {
                if (!pl_move.isAtXBoundary("R")) {
                    spriteParams.leftMargin = pl_move.getPlayerXCoordinate() + grid.getTilePxFactor();
                    pl_move.setPlayerX(pl_move.getPlayerX() + 1);
                    Log.d("X", String.valueOf(pl_move.getPlayerX()));
                }
                getCarCollision();
            }
            updateScore();

            activeSprite.setLayoutParams(spriteParams);
            return false;
        });
    } // setupNavigation

    private void setText() {
        String name = bundle.getString("name");

        nameDetermination.setText(name);
        pointDetermination.setText("0 Points");
    } // setText

    private void setDifficulty() {
        String difficulty = bundle.getString("level");

        if (difficulty.equals("Hard")) {
            activeLives = (ImageView) findViewById(R.id.imageView6);
        } else if (difficulty.equals("Easy")) {
            activeLives = (ImageView) findViewById(R.id.imageView2);
        } else if (difficulty.equals("Medium")) {
            activeLives = (ImageView) findViewById(R.id.imageView5);
        }
        activeLives.setVisibility(View.VISIBLE);
    } // setDifficulty

    private void setSprite() {
        String sprite = bundle.getString("sprite");

        if (sprite.equals("green")) {
            activeSprite = (ImageButton) findViewById(R.id.imageButton10);
        } else if (sprite.equals("blue")) {
            activeSprite = (ImageButton) findViewById(R.id.imageButton8);
        } else if (sprite.equals("red")) {
            activeSprite = (ImageButton) findViewById(R.id.imageButton9);
        }
        activeSprite.setVisibility(View.VISIBLE);
    } // setSprite

    private void setUpCars() {
        car1 = (ImageButton) findViewById(R.id.car1);
        car1.setVisibility(View.VISIBLE);
        car1Params = (ConstraintLayout.LayoutParams) car1.getLayoutParams();
        car1Params.topMargin = grid.getTilePxFactor() * 14;
        car1.setLayoutParams(car1Params);

        car2 = (ImageButton) findViewById(R.id.car2);
        car2.setVisibility(View.VISIBLE);
        car2Params = (ConstraintLayout.LayoutParams) car2.getLayoutParams();
        car2Params.topMargin = grid.getTilePxFactor() * 13;
        car2.setLayoutParams(car1Params);

        car3 = (ImageButton) findViewById(R.id.car3);
        car3.setVisibility(View.VISIBLE);
        car3Params = (ConstraintLayout.LayoutParams) car3.getLayoutParams();
        car3Params.topMargin = grid.getTilePxFactor() * 12;
        car3.setLayoutParams(car3Params);

        car4 = (ImageButton) findViewById(R.id.car4);
        car4.setVisibility(View.VISIBLE);
        car4Params = (ConstraintLayout.LayoutParams) car4.getLayoutParams();
        car4Params.topMargin = grid.getTilePxFactor() * 11;
        car4.setLayoutParams(car4Params);

        car5 = (ImageButton) findViewById(R.id.car5);
        car5.setVisibility(View.VISIBLE);
        car5Params = (ConstraintLayout.LayoutParams) car5.getLayoutParams();
        car5Params.topMargin = grid.getTilePxFactor() * 10;
        car5.setLayoutParams(car5Params);
    }

    private void manageCars() {
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

    private void shrinkBox(Rect rect) {
        int currHeight = rect.bottom - rect.top;
        int newHeight = (int) (currHeight * 0.2);
        int displacement = (int)((currHeight - newHeight) / 3);
        rect.top += displacement;
        rect.bottom = rect.top + newHeight;
    } // shrinkBox
    private void getCarCollision() {
        activeSprite.getGlobalVisibleRect(playerRect);
        car1.getGlobalVisibleRect(car1Rect);
        car2.getGlobalVisibleRect(car2Rect);
        car3.getGlobalVisibleRect(car3Rect);
        car4.getGlobalVisibleRect(car4Rect);
        car5.getGlobalVisibleRect(car5Rect);

        shrinkBox(playerRect);
        shrinkBox(car1Rect);
        shrinkBox(car2Rect);
        shrinkBox(car3Rect);
        shrinkBox(car4Rect);
        shrinkBox(car5Rect);

        if (playerRect.intersect(car1Rect) || playerRect.intersect(car2Rect) || playerRect.intersect(car3Rect) || playerRect.intersect(car4Rect) || playerRect.intersect(car5Rect)) {
            pointDetermination.setText("0 Points");
            pl_move.resetCoords();
            spriteParams.topMargin = pl_move.getPlayerYCoordinate();
            spriteParams.leftMargin = pl_move.getPlayerXCoordinate();
            if (findViewById(R.id.imageView6).getVisibility() == View.VISIBLE) { // hard 1
                try {
                    FileOutputStream file = openFileOutput("score.txt", Context.MODE_PRIVATE);
                    PrintWriter writer = new PrintWriter(file);
                    writer.println(points);
                    writer.close();
                    file.close();
                } catch (Exception e) {
                    Log.d("Exception", "Exception");
                }

                Intent intent = new Intent(this, GameOverScreen.class);
                startActivity(intent);
            } else if (findViewById(R.id.imageView2).getVisibility() == View.VISIBLE) { // easy 3
                findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
                points = 0;
                maxLevelReached = 15;
            } else if (findViewById(R.id.imageView5).getVisibility() == View.VISIBLE) { // medium 2
                findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
                findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);
                points = 0;
                maxLevelReached = 15;
            }
        }
    } // getCarCollision
} // GameScreen
