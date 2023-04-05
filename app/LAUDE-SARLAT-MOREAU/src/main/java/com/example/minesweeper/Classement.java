package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class Classement extends AppCompatActivity {

    private Button easy_button;
    private Button medium_button;
    private Button hard_button;
    private Button ret_menu_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        easy_button = findViewById(R.id.easy_classement_button);
        medium_button = findViewById(R.id.medium_classement_button);
        hard_button = findViewById(R.id.hard_classement_button);
        ret_menu_button = findViewById(R.id.retMenu);

        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_scores("easy");
            }});

        medium_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_scores("medium");
            }});

        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_scores("hard");
            }});

        ret_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }});
    }



    private void display_scores(String level){
        // Création d'un scoreManager
        File directory = getFilesDir();
        String fileName = level + "_scores.txt";
        File scoreFile = new File(directory, fileName);
        ScoreManager scoreManager = new ScoreManager(scoreFile);

        // Affichage du classement
        List<Integer> scores = scoreManager.getScores();
        Collections.sort(scores);

        LinearLayout linearLayoutScores = findViewById(R.id.linear_layout_scores);
        linearLayoutScores.removeAllViews();

        if(scores.size() > 0){
            for (int i = 0; i < scores.size(); i++) {
                TextView textViewScore = new TextView(getApplicationContext());
                textViewScore.setText(scores.get(i).toString() + " secs");
                linearLayoutScores.addView(textViewScore);
            }
        }
    }

}