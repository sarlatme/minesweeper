package com.example.minesweeper;

public class Cell {
    private int valeur;
    private boolean revelee;

    private boolean flag;

    public Cell(int valeur) {
        this.valeur = valeur;
        this.revelee = false;
        this.flag = false;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public boolean isRevelee() {
        return revelee;
    }

    public void setRevelee(boolean bool){
        this.revelee = bool;
    }

    public boolean isFlag(){
        return this.flag;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }
}
