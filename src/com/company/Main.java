package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        String line;
        ArrayList<Agente> agentes = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader("resources/input.txt"));
            line = bf.readLine();

            String[] array = line.split(" ");

            int nroCasais = Integer.parseInt(array[0]);
            int nroCartorios = Integer.parseInt(array[1]);

            line = bf.readLine();

            Genero genero = Genero.HOMEM;

            for (int i = 0; i < 2 * nroCasais; i++) {
                if (i >= nroCasais) {
                    genero = Genero.MULHER;
                }
                String[] lineSplitted = line.split(" ");

                int idAgent = Integer.parseInt(lineSplitted[0]);
                ArrayList<Integer> preferenceList = new ArrayList<>(nroCasais);
                String[] preferencesRaw = Arrays.copyOfRange(lineSplitted, 1, lineSplitted.length);
                for (String elem :
                        preferencesRaw) {
                    preferenceList.add(Integer.valueOf(elem));
                }
                System.out.println(genero);
                System.out.println("Agente: " + idAgent);
                System.out.println("Preferences" + preferenceList);

                Agente agente = new Agente(idAgent, preferenceList, genero);
                agentes.add(agente);

                line = bf.readLine();

            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        Ambiente amb = new Ambiente(20, agentes);
        amb.print_ambiente();
    }
}
