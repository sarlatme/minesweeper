package com.example.minesweeper;

import android.content.Context;

public class Game {
    private Board board;
    private BoardView boardViews;
    private Context context;

    public Game(Context context, int num_rows, int num_cols, int num_bombs, String level) {
        this.context = context;
        this.board = new Board(num_rows, num_cols, num_bombs);
        this.boardViews = new BoardView(this.board, this.context, level);
    }

    public BoardView getBoardView(){
        return this.boardViews;
    }

    public int getBombs(){
        return this.boardViews.getBombs();
    }
}
