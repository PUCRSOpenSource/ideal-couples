package com.company;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> preferenciasHomem = new ArrayList<>();
        preferenciasHomem.add(1);
        Agente homemGostoso = new Agente(1, preferenciasHomem, Genero.HOMEM);

        ArrayList<Integer> preferenciasHomem2 = new ArrayList<>();
        preferenciasHomem.add(1);
        Agente homemNaoTaoGostoso = new Agente(2, preferenciasHomem, Genero.HOMEM);

        ArrayList<Integer> preferenciasMulher = new ArrayList<>();
        preferenciasMulher.add(1);
        preferenciasMulher.add(2);
        Agente mulher = new Agente(1, preferenciasMulher, Genero.MULHER);

        ArrayList<Agente> agentes = new ArrayList<>();
        agentes.add(homemGostoso);
        agentes.add(homemNaoTaoGostoso);
        agentes.add(mulher);

        Ambiente amb = new Ambiente(10, agentes, 1);
        amb.activateAgentes();
    }
}
