package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageButton;


public class ConfigScreen extends AppCompatActivity {

    private ImageButton greenSprite;
    private ImageButton redSprite;
    private ImageButton blueSprite;
    private Button easyLevel;
    private Button mediumLevel;
    private Button hardLevel;
    private Button nextScreen;

    private String difficulty = "";
    private String sprite = "";
    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_configscreen);


        // sprite buttons
        blueSprite = (ImageButton) findViewById(R.id.imageButton8);
        redSprite = (ImageButton) findViewById(R.id.imageButton9);
        greenSprite = (ImageButton) findViewById(R.id.imageButton10);
        // level buttons
        easyLevel = (Button) findViewById(R.id.restart);
        mediumLevel = (Button) findViewById(R.id.exit);
        hardLevel = (Button) findViewById(R.id.button4);
        // text edit
        EditText nameEditText = findViewById(R.id.editTextTextPersonName);
        // next button
        nextScreen = (Button) findViewById(R.id.button);



        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nameEditText.setError("Name cannot be empty or contain only whitespaces");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameEditText.setError("Name cannot be empty or contain only whitespaces");
            }

            @Override
            public void afterTextChanged(Editable s) {

                name = s.toString().trim();
                if (name.isEmpty() || name.equals("")) {
                    nextScreen.setClickable(false);
                    nameEditText.setError("Name cannot be empty or contain only whitespaces");

                } else {
                    nextScreen.setEnabled(true);
                    nameEditText.setError(null);

                }
            }

        });
        nameEditText.setText(name);


        easyLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = "Easy";
            }
        });

        mediumLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = "Medium";
            }
        });

        hardLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = "Hard";
            }
        });

        greenSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sprite = "green";
                greenSprite.setBackgroundColor(-1);
                redSprite.setBackgroundColor(0);
                blueSprite.setBackgroundColor(0);
            }
        });

        redSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sprite = "red";
                redSprite.setBackgroundColor(-1);
                greenSprite.setBackgroundColor(0);
                blueSprite.setBackgroundColor(0);

            }
        });


        blueSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sprite = "blue";
                blueSprite.setBackgroundColor(-1);
                redSprite.setBackgroundColor(0);
                greenSprite.setBackgroundColor(0);
            }

        });

        if (!name.trim().equals("")) {
            nextScreen.setEnabled(true);
        }
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                Bundle b = new Bundle();
                b.putString("sprite", sprite);
                b.putString("level", difficulty);
                b.putString("name", name);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

    }

    public void openGameScreen() {
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }

}