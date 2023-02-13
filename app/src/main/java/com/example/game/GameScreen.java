package com.example.game;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    private TextView levelDetermination;

    private TextView nameDetermination;
    private TextView pointDetermination;
    private ImageButton spriteGreen;
    private ImageButton spriteBlue;

    private ImageButton spriteRed;
    private ImageView oneLife;
    private ImageView twoLives;
    private ImageView threeLives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        levelDetermination = (TextView) findViewById(R.id.difficulty);
        nameDetermination = (TextView) findViewById(R.id.name);
        pointDetermination = (TextView) findViewById(R.id.points);

        Bundle bundle = getIntent().getExtras();
        String sprite = bundle.getString("sprite");
        String difficulty = bundle.getString("level");
        String name = bundle.getString("name");

        nameDetermination.setText(name);
        pointDetermination.setText("0 Points");


        if (difficulty.equals("Hard")) {
            oneLife = (ImageView) findViewById(R.id.imageView6);
            oneLife.setVisibility(View.VISIBLE);
        } else if (difficulty.equals("Easy")) {
            twoLives = (ImageView) findViewById(R.id.imageView2);
            twoLives.setVisibility(View.VISIBLE);
        } else if (difficulty.equals("Medium")) {
            threeLives = (ImageView) findViewById(R.id.imageView5);
            threeLives.setVisibility(View.VISIBLE);
        }


        if (sprite.equals("green")) {
            spriteGreen = (ImageButton) findViewById(R.id.imageButton10);
            spriteGreen.setVisibility(View.VISIBLE);
        } else if (sprite.equals("blue")) {
            spriteBlue = (ImageButton) findViewById(R.id.imageButton8);
            spriteBlue.setVisibility(View.VISIBLE);

        } else if (sprite.equals("red")) {
            spriteRed = (ImageButton) findViewById(R.id.imageButton9);
            spriteRed.setVisibility(View.VISIBLE);
        }



    }
}
