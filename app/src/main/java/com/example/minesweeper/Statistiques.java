package com.example.minesweeper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Statistiques extends AppCompatActivity {

    private int nbGames, easyWins, mediumWins, hardWins;
    private SharedPreferences preferences;

    private TextView easy_wins_tv;
    private TextView medium_wins_tv;
    private TextView hard_wins_tv;
    private TextView nb_games_tv;
    private TextView ret_menu_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display stats
        setContentView(R.layout.activity_statistiques);

        nb_games_tv = findViewById(R.id.nb_games_tv);
        easy_wins_tv = findViewById(R.id.easy_wins_tv);
        medium_wins_tv = findViewById(R.id.medium_wins_tv);
        hard_wins_tv = findViewById(R.id.hard_wins_tv);


        preferences = getApplicationContext().getSharedPreferences("stats", Context.MODE_PRIVATE);

        nbGames = preferences.getInt("nbGames", 0);
        easyWins = preferences.getInt("easyWins", 0);
        mediumWins = preferences.getInt("mediumWins", 0);
        hardWins = preferences.getInt("hardWins", 0);

        nb_games_tv.setText("Parties jouées : " + nbGames);
        easy_wins_tv.setText("Victoires Facile : " + easyWins);
        medium_wins_tv.setText("Victoires Moyen : " + mediumWins);
        hard_wins_tv.setText("Victoires Difficile : " + hardWins);

        // Set return menu button
        ret_menu_button = findViewById(R.id.retMenuStats);

        ret_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }});
    }
}