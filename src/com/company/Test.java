package com.company;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> preferenciasHomem = new ArrayList<>();
        preferenciasHomem.add(1);
        ArrayList<Integer> preferenciasMulher = new ArrayList<>();
        preferenciasMulher.add(1);
        Agente homem = new Agente(1, preferenciasHomem, Genero.HOMEM);
        Agente mulher = new Agente(1, preferenciasMulher, Genero.MULHER);
        ArrayList<Agente> agentes = new ArrayList<>();
        agentes.add(homem);
        agentes.add(mulher);
        Ambiente amb = new Ambiente(10, agentes, 1);
        amb.activateAgentes();
    }
}
