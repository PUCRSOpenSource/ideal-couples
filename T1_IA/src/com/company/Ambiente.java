package com.company;

public class Ambiente {


    private Quadradinho mapa[][];
    private Agente listaAgentes;
    private Posicao listaPosicoes;

    public Ambiente(int n) {
        this.mapa = new Quadradinho[n][n];
    }
}
