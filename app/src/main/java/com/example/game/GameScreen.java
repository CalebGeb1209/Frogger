package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;



public class GameScreen extends AppCompatActivity {

    private TextView levelDetermination;
    private TextView nameDetermination;
    TextView pointDetermination;
    private ImageButton activeSprite;

    private ImageButton car1;
    private ImageButton car2;
    private ImageButton car3;
    private ImageButton car4;
    private ImageButton car5;
    private ImageView activeLives;
    private Bundle bundle;
    GameGrid grid;
    private ConstraintLayout.LayoutParams spriteParams;

    private ConstraintLayout.LayoutParams car1Params;
    private ConstraintLayout.LayoutParams car2Params;
    private ConstraintLayout.LayoutParams car3Params;
    private ConstraintLayout.LayoutParams car4Params;
    private ConstraintLayout.LayoutParams car5Params;
    private int maxLevelReached;
    private int points;

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
                Log.d("hello", ":)");
                manageCars();
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
        grid.setPlayerY(15);
    } // setupPoints

    void updateScore() {
        if (grid.getPlayerY() < maxLevelReached) {
            if (grid.getPlayerY() + 1 < 2) {
                points++;
            } else if (grid.getPlayerY() + 1 < 9) {
                points += 2;
            } else if (grid.getPlayerY() + 1 < 15 && grid.getPlayerY() + 1 > 9) {
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
        spriteParams.leftMargin = grid.getPlayerXCoordinate();
        spriteParams.topMargin = grid.getPlayerYCoordinate();

        View game = findViewById(R.id.parent);
        game.setOnTouchListener((View.OnTouchListener) (view, event) -> {
            int xCoordinate = (int) event.getX();
            int yCoordinate = (int) event.getY();

            if (xCoordinate < (grid.getWidth() / 3)) {
                if (!grid.isAtXBoundary("L")) {
                    spriteParams.leftMargin = grid.getPlayerXCoordinate() - grid.getTilePxFactor();
                    grid.setPlayerX(grid.getPlayerX() - 1);
                    Log.d("X", String.valueOf(grid.getPlayerX()));
                }
            } else if (xCoordinate < (grid.getWidth() / 3 * 2)) {
                if (yCoordinate <= (grid.getHeight()) / 2 && !grid.isAtYBoundary("U")) {
                    spriteParams.topMargin = grid.getPlayerYCoordinate() - grid.getTilePxFactor();
                    grid.setPlayerY(grid.getPlayerY() - 1);
                    Log.d("Y", String.valueOf(grid.getPlayerY()));
                } else if (yCoordinate > (grid.getHeight() / 2) && !grid.isAtYBoundary("D")) {
                    spriteParams.topMargin = grid.getPlayerYCoordinate() + grid.getTilePxFactor();
                    grid.setPlayerY(grid.getPlayerY() + 1);
                    Log.d("Y", String.valueOf(grid.getPlayerY()));
                }
            } else {
                if (!grid.isAtXBoundary("R")) {
                    spriteParams.leftMargin = grid.getPlayerXCoordinate() + grid.getTilePxFactor();
                    grid.setPlayerX(grid.getPlayerX() + 1);
                    Log.d("X", String.valueOf(grid.getPlayerX()));
                }
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

        if (car4Params.leftMargin <= 0) {
            car4Params.leftMargin = 1000;
        } else {
            car4Params.leftMargin = car4Params.leftMargin - 150;
        }
        car4.setLayoutParams(car4Params);

        if (car5Params.leftMargin <= 0) {
            car5Params.leftMargin = 1000;
        } else {
            car5Params.leftMargin = car5Params.leftMargin - 75;
        }
        car5.setLayoutParams(car5Params);
    }
} // GameScreen
