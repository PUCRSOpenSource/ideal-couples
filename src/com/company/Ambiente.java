package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Ambiente {

    private Quadradinho mapa[][];
    private ArrayList<Agente> agentes;
    private ArrayList<Posicao> cartorios;

    private int side;

    public Ambiente(int n, ArrayList<Agente> listaAgentes, int nroCartorios) {
        this.side = n;
        cartorios = new ArrayList<>();
        this.mapa = new Quadradinho[n][n];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                mapa[i][j] = Quadradinho.N;
            }
        }
        build_walls();
        build_cartorios(nroCartorios);

        for (Agente a :
                listaAgentes) {
            placeOnMap(a);
        }
        agentes = listaAgentes;
    }

    private void placeOnMap(Agente agente) {
        while (true) {
            int x = (int) (Math.random() * side);
            int y = (int) (Math.random() * side);
            if (mapa[x][y] == Quadradinho.N) {
                agente.setPosicao(new Posicao(x, y));
                mapa[x][y] = Quadradinho.A;
                break;
            }
        }
    }

    public void print_ambiente() {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private void build_cartorios(int nroCartorios) {
        ArrayList<Posicao> possiveisPosicoes = new ArrayList<>();

        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if (mapa[i][j] == Quadradinho.P) {
                    possiveisPosicoes.add(new Posicao(i, j - 1));
                    possiveisPosicoes.add(new Posicao(i, j + 1));
                }
            }
        }
        Random rand = new Random();
        for (int i = 0; i < nroCartorios; i++) {
            int posicaoSorteada = rand.nextInt(possiveisPosicoes.size());
            Posicao posicao = possiveisPosicoes.get(posicaoSorteada);
            mapa[posicao.getX()][posicao.getY()] = Quadradinho.C;
            cartorios.add(posicao);
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
