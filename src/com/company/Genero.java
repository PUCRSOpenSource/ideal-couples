package com.company;

public enum Genero {
    HOMEM, MULHER;

    private Genero opposite;

    static {
        HOMEM.opposite = MULHER;
        MULHER.opposite = HOMEM;
    }

    public Genero getOpposite() {
        return opposite;
    }
}
