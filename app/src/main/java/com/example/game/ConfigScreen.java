package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ConfigScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_configscreen);


        EditText nameEditText = findViewById(R.id.editTextTextPersonName);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = s.toString().trim();
                if (name.isEmpty() || name.equals("")) {
                    nameEditText.setError("Name cannot be empty or contain only whitespaces");
                } else {
                    nameEditText.setError(null);
                }
            }
        });

    }
}