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
    private Bundle bundle;
    private TextView levelDetermination;
    private TextView nameDetermination;
    private TextView pointDetermination;
    private ImageView activeLives;
    private ImageButton activeSprite;
    private ConstraintLayout.LayoutParams spriteParams;

    private GameGrid grid;
    private GridLayout gridLayout;
    private PlayerMovement playerMovement;
    private ScoreManager scoreManager;
    private CarManager carManager;
    private CollisionManager collisionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        levelDetermination = (TextView) findViewById(R.id.difficulty);
        nameDetermination = (TextView) findViewById(R.id.name);
        pointDetermination = (TextView) findViewById(R.id.points);
        bundle = getIntent().getExtras();

        grid = new GameGrid(this, 120);
        gridLayout = findViewById(R.id.grid);

        setSprite();



        playerMovement = new PlayerMovement(grid);
        scoreManager = new ScoreManager(playerMovement, pointDetermination);
        carManager = new CarManager(grid, findViewById(R.id.car1), findViewById(R.id.car2), findViewById(R.id.car3), findViewById(R.id.car4), findViewById(R.id.car5));
        collisionManager = new CollisionManager(carManager, activeSprite, scoreManager, playerMovement);

        setupGrid();






        setText();
        setDifficulty();

        setupNavigation();


        new CountDownTimer(1000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                carManager.manageCars();
                if (collisionManager.getCarCollision()) {
                    if (findViewById(R.id.imageView6).getVisibility() == View.VISIBLE) { // hard 1
                        endGame();
                    } else if (findViewById(R.id.imageView2).getVisibility() == View.VISIBLE) { // easy 3
                        findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                        findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
                        scoreManager.resetScore();
                    } else if (findViewById(R.id.imageView5).getVisibility() == View.VISIBLE) { // medium 2
                        findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
                        findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);
                        scoreManager.resetScore();
                    }
                };
            }

            @Override
            public void onFinish() {
                // Code to execute when the timer is finished
            }
        }.start();

    } // onCreate

    public void setupGrid() {
        grid.populate(gridLayout);
        carManager.setUpCars();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupNavigation() {
        spriteParams = (ConstraintLayout.LayoutParams) activeSprite.getLayoutParams();
        spriteParams.leftMargin = playerMovement.getPlayerXCoordinate();
        spriteParams.topMargin = playerMovement.getPlayerYCoordinate();

        View game = findViewById(R.id.parent);
        game.setOnTouchListener((View.OnTouchListener) (view, event) -> {
            int xCoordinate = (int) event.getX();
            int yCoordinate = (int) event.getY();

            if (xCoordinate < (grid.getWidth() / 3)) {
                if (!playerMovement.isAtXBoundary("L")) {
                    spriteParams.leftMargin = playerMovement.getPlayerXCoordinate() - grid.getTilePxFactor();
                    playerMovement.setPlayerX(playerMovement.getPlayerX() - 1);
                }
                collisionManager.getCarCollision();
            } else if (xCoordinate < (grid.getWidth() / 3 * 2)) {
                if (yCoordinate <= (grid.getHeight()) / 2 && !playerMovement.isAtYBoundary("U")) {
                    spriteParams.topMargin = playerMovement.getPlayerYCoordinate() - grid.getTilePxFactor();
                    playerMovement.setPlayerY(playerMovement.getPlayerY() - 1);
                    if (playerMovement.contactMade()) {
                        pointDetermination.setText("0 Points");
                        playerMovement.resetCoords();
                        spriteParams.topMargin = playerMovement.getPlayerYCoordinate();
                        spriteParams.leftMargin = playerMovement.getPlayerXCoordinate();
                        if (findViewById(R.id.imageView6).getVisibility() == View.VISIBLE) { // hard 1
                            endGame();
                        } else if (findViewById(R.id.imageView2).getVisibility() == View.VISIBLE) { // easy 3
                            findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                            findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
                            scoreManager.resetScore();
                        } else if (findViewById(R.id.imageView5).getVisibility() == View.VISIBLE) { // medium 2
                            findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
                            findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);
                            scoreManager.resetScore();
                        }
                    }
                } else if (yCoordinate > (grid.getHeight() / 2) && !playerMovement.isAtYBoundary("D")) {
                    spriteParams.topMargin = playerMovement.getPlayerYCoordinate() + grid.getTilePxFactor();
                     playerMovement.setPlayerY(playerMovement.getPlayerY() + 1);
                }
                collisionManager.getCarCollision();
            } else {
                if (!playerMovement.isAtXBoundary("R")) {
                    spriteParams.leftMargin = playerMovement.getPlayerXCoordinate() + grid.getTilePxFactor();
                    playerMovement.setPlayerX(playerMovement.getPlayerX() + 1);
                }
                collisionManager.getCarCollision();
            }
            scoreManager.updateScore();

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

    public void endGame() {
        try {
            FileOutputStream file = openFileOutput("score.txt", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(file);
            writer.println(scoreManager.getScore());
            writer.close();
            file.close();
        } catch (Exception e) {
            Log.d("Exception", "Exception Occurred");
        }

        Intent intent = new Intent(this, GameOverScreen.class);
        startActivity(intent);
    } // endGame
} // GameScreen
