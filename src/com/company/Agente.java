package com.company;

import java.util.ArrayList;

public class Agente {

    private int id;
    private Agente conjuge;
    private ArrayList<Integer> listaPreferencias;
    private EstadoCivil estadoCivil;
    private Genero genero;
    private Posicao posicao;

    public Agente(int id, ArrayList<Integer> listaPreferencias, Genero genero) {
        this.id = id;
        this.listaPreferencias = listaPreferencias;
        this.estadoCivil = EstadoCivil.SOLTEIRO;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public Posicao getPosicao()
    {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Genero getGenero() {
        return genero;
    }
}
