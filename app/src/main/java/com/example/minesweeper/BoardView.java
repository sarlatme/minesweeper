package com.example.minesweeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class BoardView {

    private Board board;

    private ImageView[][] boardView;

    public BoardView(Board board, Context context){
        this.board = board;
        this.boardView = createBoardView(context);
    }

    public Board getBoard(){
        return this.board;
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
        return true;
    }

    private void bombClick(Cell[][] board, ImageView[][] boardViews, int row, int col) {
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
    }

    public int getBombs() {
        return this.board.getBombs();
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

                int num_bombs = this.board.getBombs();
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
                            bombClick(tableau.getCellules(), boardViews, ligne, colonne);
                            Toast.makeText(context, "Perdu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        cell.setRevelee(true);
                        ((ImageView) v).setImageResource(getImageResource(value));

                        if (checkWin(tableau.getCellules())) {
                            //Intent intent = new Intent(context, WinActivity.class);
                            //context.startActivity(intent);
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
