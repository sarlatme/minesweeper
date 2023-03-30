package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instancie le fragment
        GameFragment gameFragment = new GameFragment();

        // Ajoute le fragment au conteneur FrameLayout du MainActivity
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, gameFragment).commit();
    }
}