package com.example.minesweeper;

public class Board {
    private Cell[][] cellules;
    private int num_rows;
    private int num_cols;

    private int num_bombs;

    public Board(int num_rows, int num_cols, int num_bombs) {
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        this.num_bombs = num_bombs;
        this.cellules = createBoard(this.num_rows, this.num_cols, this.num_bombs);
    }

    public int getRows(){
        return num_rows;
    }

    public int getCols(){
        return num_cols;
    }

    public int getBombs(){
        return this.num_bombs;
    }

    public Cell[][] getCellules(){
        return this.cellules;
    }

    public Cell getParticularCell(int row, int col){
        return this.cellules[row][col];
    }
    public void initBoards(Cell[][] array) {
        int num_rows = array.length;
        int num_cols = array[0].length;

        // Copie temporaire de array pour éviter de modifier les cases déjà parcourues
        Cell[][] tempArray = new Cell[num_rows][num_cols];
        for (int i = 0; i < num_rows; i++) {
            for (int j = 0; j < num_cols; j++) {
                tempArray[i][j] = array[i][j];
            }
        }

        // Parcourt le tableau et met à jour les cases nécessaires
        for (int i = 0; i < num_rows; i++) {
            for (int j = 0; j < num_cols; j++) {
                if (array[i][j].getValeur() == 0) {
                    // Compte le nombre de cases de valeur 9 autour de la case courante
                    int numNeighborNines = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            int row = i + k;
                            int col = j + l;
                            if (row >= 0 && row < num_rows && col >= 0 && col < num_cols && array[row][col].getValeur() == 9) {
                                numNeighborNines++;
                            }
                        }
                    }
                    tempArray[i][j].setValeur(numNeighborNines);
                }
            }
        }
    }

    public Cell[][] createBoard(int num_rows, int num_cols, int num_bombs) {
        Cell[][] array = new Cell[num_rows][num_cols];

        for (int i = 0; i < num_rows; i++) {
            for (int j = 0; j < num_cols; j++) {
                array[i][j] = new Cell(0);
            }
        }

        int indiceI, indiceJ = 0;
        for (int i = 0; i < num_bombs; i++){
            indiceI = (int) (Math.random() * num_rows);
            indiceJ = (int) (Math.random() * num_cols);
            array[indiceI][indiceJ] = new Cell(9);
        }

        initBoards(array);
        return array;
    }
}
