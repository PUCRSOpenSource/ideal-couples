package com.company;

import java.util.Random;

public class Ambiente {

    private Quadradinho mapa[][];
    private Agente listaAgentes;
    private Posicao listaPosicoes;
    private int side;

    public Ambiente(int n) {
        this.side = n;
        this.mapa = new Quadradinho[n][n];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                mapa[i][j] = Quadradinho.N;
            }
        }
        build_walls();
    }

    public void print_ambiente() {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private void build_walls() {
        Random rand = new Random();

        int nro_walls = side / 5;
        int wall_territory = side / nro_walls;

        int currentX = 0;
        for (int i = 0; i < nro_walls; i++) {
            int wallLength = side / 2 + rand.nextInt(1);
            int wall_x_position_in_territory = wall_territory / 2 + rand.nextInt(1);
            int wall_y_position_in_territory = rand.nextInt(side - wallLength) + 1;

            int wallX = currentX + wall_x_position_in_territory;
            int endY = wall_y_position_in_territory + wallLength;
            for (int j = wall_y_position_in_territory; j < endY; j++) {
                mapa[j][wallX] = Quadradinho.P;
            }
            currentX += wall_territory;
        }
    }
}
