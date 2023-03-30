package com.example.minesweeper;

import android.content.Context;

public class Game {
    private Board board;
    private BoardView boardViews;
    private Context context;

    public Game(Context context, int num_rows, int num_cols, int proportion) {
        this.context = context;
        this.board = new Board(num_rows, num_cols, proportion);
        this.boardViews = new BoardView(this.board, this.context);
    }

    public BoardView getBoardView(){
        return this.boardViews;
    }

}
