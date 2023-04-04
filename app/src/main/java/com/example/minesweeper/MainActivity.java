package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button easy_button;
    private Button medium_button;
    private Button hard_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easy_button = findViewById(R.id.easy_button);
        medium_button = findViewById(R.id.medium_button);
        hard_button = findViewById(R.id.hard_button);

        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFragment gameFragment = new GameFragment();
                Bundle args = new Bundle();
                args.putInt("row", 5);
                args.putInt("col", 5);
                args.putInt("bombs", 5);
                gameFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gameFragment).commit();
            }
        });

        medium_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFragment gameFragment = new GameFragment();
                Bundle args = new Bundle();
                args.putInt("row", 8);
                args.putInt("col", 8);
                args.putInt("bombs", 12);
                gameFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gameFragment).commit();
            }
        });

        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFragment gameFragment = new GameFragment();
                Bundle args = new Bundle();
                args.putInt("row", 10);
                args.putInt("col", 10);
                args.putInt("bombs", 20);
                gameFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gameFragment).commit();
            }
        });
    }
}
