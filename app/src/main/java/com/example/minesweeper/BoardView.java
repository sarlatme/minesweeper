package com.example.minesweeper;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
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
                return R.drawable.empty_case;
        }
    }

    public ImageView[][] createBoardView(Board board, Context context) {
        int num_rows = board.getRows();
        int num_cols = board.getCols();

        ImageView[][] boardViews = new ImageView[num_rows][num_cols];


        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                // Créez une nouvelle instance ImageView pour chaque entier dans le tableau 2D
                ImageView imageView = new ImageView(context);
                imageView.setTag(new int[]{row, col});
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int[] coords = (int[]) v.getTag();
                        int value = board.getParticularCell(coords[0], coords[1]).getValeur();
                        ((ImageView) v).setImageResource(getImageResource(value));
                    }
                });
                boardViews[row][col] = imageView;
            }
        }

        return boardViews;
    }


}
