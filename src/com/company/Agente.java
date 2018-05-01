package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Agente {

    private int id;
    private Agente conjuge;
    private ArrayList<Integer> listaPreferencias;
    private State state;
    private Genero genero;
    private Posicao posicao;
    private Ambiente amb;
    private Direction direction;

    public Agente(int id, ArrayList<Integer> listaPreferencias, Genero genero) {
        this.id = id;
        this.listaPreferencias = listaPreferencias;
        this.state = State.SOLTEIRO;
        this.genero = genero;
        this.direction = Direction.randomDirection();
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
        switch (state) {
            case SOLTEIRO:
                idle(possibleMoves);
                break;
            case CASADO:
                break;
            case DATING:
                break;
        }
    }

    public void idle(ArrayList<Posicao> possibleMoves) {
        int[] offset = direction.getOffset();
        Posicao newPosition = new Posicao(posicao.getX() + offset[0], posicao.getY() + offset[1]);
        if (amb.canMove(newPosition)) {
            amb.move(posicao, newPosition);
            posicao = newPosition;
        } else {
            direction = Direction.randomDirection(direction);
            Random rand = new Random();
            newPosition = possibleMoves.get(rand.nextInt(possibleMoves.size()));
            amb.move(posicao, newPosition);
            posicao = newPosition;
        }
    }
}
