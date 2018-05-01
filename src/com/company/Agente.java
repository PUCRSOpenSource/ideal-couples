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

    public void setConjuge(Agente conjuge) {
        this.conjuge = conjuge;
    }

    public ArrayList<Integer> getListaPreferencias() {
        return listaPreferencias;
    }

    public void setAmbiente(Ambiente amb) {
        this.amb = amb;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void takeAction(ArrayList<Posicao> possibleMoves, ArrayList<Agente> agentsInRange) {
        switch (state) {
            case SOLTEIRO:
                Agente pretendente = shouldChangeState(agentsInRange);
                if (pretendente != null) {
                    changeStateToDating(pretendente);
                } else {
                    idle(possibleMoves);
                }
                break;
            case CASADO:
                break;
            case DATING:
                break;
        }
    }

    public void changeStateToDating(Agente pretendente) {
        conjuge = pretendente;
        state = State.DATING;
        pretendente.setConjuge(this);
        pretendente.setState(State.DATING);
    }

    private Agente shouldChangeState(ArrayList<Agente> agentsInRange) {
        Agente pretendente = null;
        for (Agente a :
                agentsInRange) {
            boolean euQuero = false;
            if (a.getGenero() == genero.getOpposite() && vamoFica(a) ) {
                euQuero = true;
            }
            boolean elaQuer = false;
            if (a.getGenero() == genero.getOpposite() && a.vamoFica(this)) {
                elaQuer = true;
            }
            if (euQuero && elaQuer && (pretendente == null || listaPreferencias.indexOf(a) < listaPreferencias.indexOf(pretendente)))
                pretendente = a;
        }
        return pretendente;
    }

    public boolean vamoFica(Agente pretendente) {
        if (pretendente.getGenero() == genero.getOpposite())
            return conjuge == null || listaPreferencias.indexOf(pretendente.getId()) < listaPreferencias.indexOf(conjuge.getId());
        return false;
    }

    private void idle(ArrayList<Posicao> possibleMoves) {
        int[] offset = direction.getOffset();
        Posicao newPosition = new Posicao(posicao.getX() + offset[0], posicao.getY() + offset[1]);
        if (amb.canMove(newPosition)) {
            move(newPosition);
        } else {
            direction = Direction.randomDirection(direction);
            Random rand = new Random();
            newPosition = possibleMoves.get(rand.nextInt(possibleMoves.size()));
            move(newPosition);
        }
    }

    private void move(Posicao newPosition) {
        amb.move(posicao, newPosition);
        posicao = newPosition;
    }
}
