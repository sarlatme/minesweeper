package com.example.minesweeper;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class BoardView {

    private Board board;

    private ImageView[][] boardView;

    public BoardView(Board board, Context context){
        this.board = board;
        this.boardView = createBoardView(board, context);
    }

    public Board getBoard(){
        return this.board;
    }

    public ImageView getParticularImageView(int row, int col){
        return this.boardView[row][col];
    }

    public ImageView[][] createBoardView(Board board, Context context) {
        int num_rows = board.getRows();
        int num_cols = board.getCols();

        ImageView[][] boardViews = new ImageView[num_rows][num_cols];

        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                // Créez une nouvelle instance ImageView pour chaque entier dans le tableau 2D
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.drawable.case_1);
                boardViews[row][col] = imageView;
            }
        }

        return boardViews;
    }


}
