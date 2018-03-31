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
        try {
            BufferedReader bf = new BufferedReader(new FileReader("resources/input.txt"));
            line = bf.readLine();

            String[] array = line.split(" ");

            int nroCasais = Integer.parseInt(array[0]);
            int nroCartorios = Integer.parseInt(array[1]);

            line = bf.readLine();

            String genero = "HOMI";

            for (int i = 0; i < 2 * nroCasais; i++) {
                if (i >= nroCasais) {
                    genero = "MUIE";
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
                System.out.println("Agent: " + idAgent);
                System.out.println("Preferences" + preferenceList);

                line = bf.readLine();

            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
