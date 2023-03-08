package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
    private TextView pointDetermination;
    private ImageButton activeSprite;
    private ImageView activeLives;
    private Bundle bundle;
    private GameGrid grid;
    private ConstraintLayout.LayoutParams spriteParams;
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
    } // onCreate

    private void setupScore() {
        maxLevelReached = 15;
        points = 0;
        grid.setPlayerY(15);
    } // setupPoints

    private void updateScore() {
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
} // GameScreen
