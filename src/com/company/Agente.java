package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Agente {

    private int id;
    private Agente conjuge;
    private ArrayList<Integer> listaPreferencias;
    private EstadoCivil estadoCivil;
    private Genero genero;
    private Posicao posicao;
    private Ambiente amb;

    public Agente(int id, ArrayList<Integer> listaPreferencias, Genero genero) {
        this.id = id;
        this.listaPreferencias = listaPreferencias;
        this.estadoCivil = EstadoCivil.SOLTEIRO;
        this.genero = genero;
        this.amb = amb;
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

    public void setAmbiente(Ambiente amb) {
        this.amb = amb;
    }

    public void takeAction(ArrayList<Posicao> possibleMoves, ArrayList<Agente> agentsInRange) {
        Random rand = new Random();
        Posicao newPosicao = possibleMoves.get(rand.nextInt(possibleMoves.size()));
        amb.move(posicao, newPosicao);
        posicao = newPosicao;
    }
}
