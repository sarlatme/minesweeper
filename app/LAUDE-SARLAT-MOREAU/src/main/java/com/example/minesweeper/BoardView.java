package com.example.minesweeper;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class BoardView extends AppCompatActivity {

    private Board board;

    private ImageView[][] boardView;

    private Context context;

    private SharedPreferences sp;

    private CountDownTimer countDownTimer;
    public static long TIMER_LENGTH = 999000L;

    private int mTime = 0;
    private String level;

    private TextView mTimerTextView;

    public BoardView(Board board, Context context, String level){
        sp = context.getSharedPreferences("stats", context.MODE_PRIVATE);

        this.board = board;
        this.context = context;
        this.boardView = createBoardView(context);
        this.mTime = 0;
        this.level = level;

        this.mTimerTextView = (TextView) ((Activity) context).findViewById(R.id.timer_text_view);


        this.countDownTimer = new CountDownTimer(TIMER_LENGTH, 1000) {
            @Override
            public void onTick(long l) {
                mTime +=1;
                mTimerTextView.setText(Integer.toString(mTime));
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.cancel();
        countDownTimer.start();
    }

    public Board getBoard(){
        return this.board;
    }

    public int getBombs() {
        return this.board.getBombs();
    }

    public ImageView getParticularImageView(int row, int col){
        return this.boardView[row][col];
    }

    private int getImageResource(int value) {
        switch (value) {
            case 1:
                return R.drawable.case_1;
            case 2:
                return R.drawable.case_2;
            case 3:
                return R.drawable.case_3;
            case 4:
                return R.drawable.case_4;
            case 5:
                return R.drawable.case_5;
            case 6:
                return R.drawable.case_6;
            case 7:
                return R.drawable.case_7;
            case 8:
                return R.drawable.case_8;
            case 9:
                return R.drawable.bomb;
            default:
                return R.drawable.case_virgin;
        }
    }

    private void revealEmptyCells(Cell[][] board, ImageView[][] boardViews, int row, int col) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
            return;
        }

        Cell cell = board[row][col];

        if (cell.isRevelee()) {
            return;
        }

        cell.setRevelee(true);
        boardViews[row][col].setImageResource(getImageResource(cell.getValeur()));

        if (cell.getValeur() == 0){
            revealEmptyCells(board, boardViews, row - 1, col - 1);
            revealEmptyCells(board, boardViews, row - 1, col);
            revealEmptyCells(board, boardViews, row - 1, col + 1);
            revealEmptyCells(board, boardViews, row, col - 1);
            revealEmptyCells(board, boardViews, row, col + 1);
            revealEmptyCells(board, boardViews, row + 1, col - 1);
            revealEmptyCells(board, boardViews, row + 1, col);
            revealEmptyCells(board, boardViews, row + 1, col + 1);
        } else{
            return;
        }

    }

    private boolean checkWin(Cell[][] board) {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.getValeur() != 9 && !cell.isRevelee()) {
                    return false;
                }
            }
        }

        // Save score
        String seconds = mTimerTextView.getText().toString();

        // Création d'un scoreManager
        File directory = context.getFilesDir();
        String fileName = this.level + "_scores.txt";
        File scoreFile = new File(directory, fileName);
        ScoreManager scoreManager = new ScoreManager(scoreFile);

        // Enregistrement du temps courant
        scoreManager.saveScore(seconds);

        // Mettre à jour les préférences
        String key = level + "Wins";
        int nbWins = sp.getInt(key, -1);
        nbWins ++;

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, nbWins);
        editor.commit();
        int easyWins = sp.getInt(key, -1);

        return true;
    }

    private void endGameClick(Cell[][] board, ImageView[][] boardViews) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getValeur() == 9) {
                    boardViews[i][j].setImageResource(getImageResource(9));
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getValeur() != 9) {
                    boardViews[i][j].setImageResource(getImageResource(board[i][j].getValeur()));
                }
                board[i][j].setRevelee(true);
            }
        }

        // Increment the number of games
        int nbGames = sp.getInt("nbGames", 0);
        nbGames ++;

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("nbGames", nbGames);
        editor.commit();

        countDownTimer.cancel();
    }


    public ImageView[][] createBoardView(Context context) {
        int num_rows = this.board.getRows();
        int num_cols = this.board.getCols();

        ImageView[][] boardViews = new ImageView[num_rows][num_cols];

        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                // Créez une nouvelle instance ImageView pour chaque entier dans le tableau 2D
                ImageView imageView = new ImageView(context);
                int ligne = row;
                int colonne = col;
                Cell cell = this.board.getParticularCell(ligne, colonne);
                int value = cell.getValeur();
                Board tableau = this.board;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(cell.isFlag()){
                            return;
                        }
                        if (cell.getValeur() == 0){
                            revealEmptyCells(tableau.getCellules(), boardViews, ligne, colonne);
                        }

                        if (cell.getValeur() == 9){
                            endGameClick(tableau.getCellules(), boardViews);
                            Toast.makeText(context, "Perdu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        cell.setRevelee(true);
                        ((ImageView) v).setImageResource(getImageResource(value));

                        if (checkWin(tableau.getCellules())) {
                            //Intent intent = new Intent(context, WinActivity.class);
                            //context.startActivity(intent);
                            endGameClick(tableau.getCellules(), boardViews);
                            Toast.makeText(context, "Gagné", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        if(cell.isRevelee()){
                            return false;
                        }

                        if(!cell.isFlag()){
                            cell.setFlag(true);
                            ((ImageView) v).setImageResource(R.drawable.flag);
                        } else{
                            cell.setFlag(false);
                            ((ImageView) v).setImageResource(R.drawable.empty_case);
                        }

                        return true;
                    }
                });
                boardViews[row][col] = imageView;
            }
        }
        return boardViews;
    }


}
