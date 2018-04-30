package com.company;

public class Heuristic {
    public static int manhattanDistance(Posicao from, Posicao to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }
}
