package com.company;

public class Posicao {

    private int x;
    private int y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Posicao pos = (Posicao) o;
        if (x == pos.getX() && y == pos.getY())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Position {x: " + y + ", y: " + x + "} ";
    }
}
