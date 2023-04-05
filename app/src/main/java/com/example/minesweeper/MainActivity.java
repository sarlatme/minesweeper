package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kotlin.contracts.Returns;

public class MainActivity extends AppCompatActivity {
    private Button easy_button;
    private Button medium_button;
    private Button hard_button;
    private Button classement_button;
    private Button returnMenu;
    private TextView mTimerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easy_button = findViewById(R.id.easy_button);
        medium_button = findViewById(R.id.medium_button);
        hard_button = findViewById(R.id.hard_button);
        classement_button = findViewById(R.id.classement_button);
        mTimerTextView = findViewById(R.id.timer_text_view);
        returnMenu = findViewById(R.id.ReturnMenu);

        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGame(5, 5, 5, "easy");
            }
        });

        medium_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGame(8, 8, 12, "medium");
            }
        });

        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGame(10, 10, 20, "hard");
            }
        });

        classement_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Classement.class);
                startActivity(intent);
            }
        });

        returnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchGame(int nbRows, int nbCols, int nbBombs, String level){
        hideButtons();

        GameFragment gameFragment = new GameFragment();
        Bundle args = new Bundle();
        args.putInt("row", nbRows);
        args.putInt("col", nbCols);
        args.putInt("bombs", nbBombs);
        args.putString("level", level);
        gameFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, gameFragment).commit();
    }

    private void hideButtons(){
        easy_button.setVisibility(View.GONE);
        medium_button.setVisibility(View.GONE);
        hard_button.setVisibility(View.GONE);
        classement_button.setVisibility(View.GONE);
        returnMenu.setVisibility(View.VISIBLE);
    }
}
