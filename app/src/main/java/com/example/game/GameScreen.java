package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class GameScreen extends AppCompatActivity {
    private Bundle bundle;
    private TextView levelDetermination;
    private TextView nameDetermination;
    private TextView pointDetermination;
    private ImageView activeLives;
    private ImageButton activeSprite;
    private boolean gameOver;
    private GameGrid grid;
    private GridLayout gridLayout;
    private PlayerMovement playerMovement;
    private ScoreManager scoreManager;
    private CarManager carManager;
    private LogManager logManager;
    private CollisionManager collisionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameOver = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        levelDetermination = (TextView) findViewById(R.id.difficulty);
        nameDetermination = (TextView) findViewById(R.id.name);
        pointDetermination = (TextView) findViewById(R.id.points);
        bundle = getIntent().getExtras();

        grid = new GameGrid(this, 120);
        gridLayout = findViewById(R.id.grid);

        setSprite();
        setText();
        setDifficulty();
        playerMovement = new PlayerMovement(grid, findViewById(R.id.parent), activeSprite);
        scoreManager = new ScoreManager(playerMovement, pointDetermination, this);
        carManager = new CarManager(grid, findViewById(R.id.car1), findViewById(R.id.car2),
                findViewById(R.id.car3), findViewById(R.id.car4), findViewById(R.id.car5));
        logManager = new LogManager(findViewById(R.id.log1), findViewById(R.id.log2),
                findViewById(R.id.log3), findViewById(R.id.log4), findViewById(R.id.log5),
                findViewById(R.id.log6), findViewById(R.id.log7));
        logManager.setupLogEtc(grid, this, activeSprite);
        collisionManager = new CollisionManager(carManager, logManager, activeSprite, scoreManager,
                playerMovement);
        playerMovement.setupNavigation();
        setupGrid();

        new CountDownTimer(1000000000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                scoreManager.updateScore();
            }

            @Override
            public void onFinish() {
                // Code to execute when the timer is finished
            }
        }.start();

        new CountDownTimer(1000000000, 750) {
            @Override
            public void onTick(long millisUntilFinished) {
                carManager.manageCars();
                logManager.manageLogs();
                if ((collisionManager.getCarCollision()
                        || (collisionManager.waterContactMade() && !collisionManager.onLogs()))
                        && !gameOver) {
                    scoreManager.updateScore();
                    handleCollision();
                }
                scoreManager.updateScore();
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
        logManager.setUpLogs();
    }

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

        gameOver = true;
        Intent intent = new Intent(this, GameOverScreen.class);
        startActivity(intent);
    } // endGame

    public void winGame() {
        try {
            FileOutputStream file = openFileOutput("score.txt", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(file);
            writer.println(scoreManager.getScore());
            writer.close();
            file.close();
        } catch (Exception e) {
            Log.d("Exception", "Exception Occurred");
        }

        gameOver = true;
        Intent intent = new Intent(this, GameWinScreen.class);
        startActivity(intent);
    } // winGame

    public void handleCollision() {
        playerMovement.resetCoords();
        if (findViewById(R.id.imageView6).getVisibility() == View.VISIBLE) {
            endGame();
        } else if (findViewById(R.id.imageView2).getVisibility() == View.VISIBLE) {
            findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
            findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
            scoreManager.resetScore();
        } else if (findViewById(R.id.imageView5).getVisibility() == View.VISIBLE) {
            findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
            findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);
            scoreManager.resetScore();
        }
    } // handleCollision

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
} // GameScreen
