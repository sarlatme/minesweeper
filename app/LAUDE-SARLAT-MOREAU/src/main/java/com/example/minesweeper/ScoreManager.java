package com.example.minesweeper;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class    ScoreManager extends AppCompatActivity{

    private final File file;

    ScoreManager(File scoreFile){
        this.file = scoreFile;
    }

    public void saveScore(String score){
        try {
            if (!this.file.exists()) {
                file.createNewFile();
            }

            // Ouverture du fichier en mode d'écriture
            FileOutputStream fos = new FileOutputStream(this.file, true);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.println(score);

            // Fermeture du flux d'écriture
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getScores(){
        // File file = new File(this.scoreFilePath);//Text file location

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(this.file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String currentLine = null;
        StringBuilder stringBuilder = new StringBuilder();

        List<Integer> scores = new ArrayList<>();

        while (true) {
            try {
                if (!((currentLine = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            scores.add(Integer.parseInt(currentLine));
            stringBuilder.append(currentLine + "\n"); //Read all the lines in the text file and concatenate them for a single stringBulder object.

        }

        return scores;
}




}
