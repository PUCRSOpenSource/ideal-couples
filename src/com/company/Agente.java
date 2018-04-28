package com.company;

import java.util.ArrayList;

public class Agente {

    private int id;
    private Agente conjuge;
    private ArrayList<Integer> listaPreferencias;
    private EstadoCivil estadoCivil;
    private Genero genero;
    private Posicao posicao;

    public Agente(int id, Agente conjuge, ArrayList<Integer> listaPreferencias, EstadoCivil estadoCivil, Genero genero, Posicao posicao) {
        this.id = id;
        this.conjuge = conjuge;
        this.listaPreferencias = listaPreferencias;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Genero getGenero() {
        return genero;
    }
}
