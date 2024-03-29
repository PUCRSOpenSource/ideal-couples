package com.company;

import java.util.*;

public class Ambiente {

    private Quadradinho mapa[][];
    private ArrayList<Agente> agentes;
    private ArrayList<Posicao> cartorios;
    private int side;

    private int offsetSight[][] = {
            {-2, -2}, {-2, -1}, {-2, 0}, {-2, +1}, {-2, +2},
            {-1, -2}, {-1, -1}, {-1, 0}, {-1, +1}, {-1, +2},
            { 0, -2}, { 0, -1},          { 0, +1}, { 0, +2},
            {+1, -2}, {+1, -1}, {+1, 0}, {+1, +1}, {+1, +2},
            {+2, -2}, {+2, -1}, {+2, 0}, {+2, +1}, {+2, +2}};

    private int offsetMove[][] = {
            {-1, -1}, {-1, 0}, {-1, +1},
            { 0, -1},          { 0, +1},
            {+1, -1}, {+1, 0}, {+1, +1}};

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
            a.setAmbiente(this);
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
            possiveisPosicoes.remove(posicaoSorteada);
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
            int wall_y_position_in_territory = rand.nextInt(side - wallLength - 2) + 1;

            int wallX = currentX + wall_x_position_in_territory;
            int endY = wall_y_position_in_territory + wallLength;
            for (int j = wall_y_position_in_territory; j < endY; j++) {
                mapa[j][wallX] = Quadradinho.P;
            }
            currentX += wall_territory;
        }
    }

    public void activateAgentes() {
        Scanner in = new Scanner(System.in);
        while (true) {
            print_ambiente();
            in.nextLine();
            for (Agente a :
                    agentes) {
                Posicao posicao = a.getPosicao();
                ArrayList<Posicao> possibleMoves = agentPossibleMoves(posicao);
                ArrayList<Agente> agentsInRange = agentsInRange(posicao);
                a.takeAction(possibleMoves, agentsInRange);
            }
        }
    }

    private ArrayList<Agente> agentsInRange(Posicao agentPosition) {
        ArrayList<Agente> agentsInRange = new ArrayList<>();
        for (int[] off :
                offsetSight) {
            int x = agentPosition.getX() + off[0];
            if (x < 0 || x >= side)
                continue;
            int y = agentPosition.getY() + off[1];
            if (y < 0 || y >= side)
                continue;
            if (mapa[x][y] == Quadradinho.A) {
                Agente agente = getAgentByPosition(new Posicao(x, y));
                agentsInRange.add(agente);
            }
        }
        return agentsInRange;
    }

    private ArrayList<Posicao> agentPossibleMoves(Posicao agentPosition) {
        return getEmptyPositions(agentPosition, offsetMove);
    }

    private ArrayList<Posicao> possibleMoves(Posicao position) {
        return getEmptyPositions(position, offsetSight);

    }

    private ArrayList<Posicao> getEmptyPositions(Posicao position, int[][] offsetSight) {
        ArrayList<Posicao> possibleMoves = new ArrayList<>();
        for (int[] off :
                offsetSight) {
            int x = position.getX() + off[0];
            if (x < 0 || x >= side)
                continue;
            int y = position.getY() + off[1];
            if (y < 0 || y >= side)
                continue;
            if (mapa[x][y] == Quadradinho.N) {
                possibleMoves.add(new Posicao(x, y));
            }
        }
        return possibleMoves;
    }

    private ArrayList<Posicao> getEmptyPositionsPlusCartorios(Posicao position, int[][] offsetSight) {
        ArrayList<Posicao> possibleMoves = new ArrayList<>();
        for (int[] off :
                offsetSight) {
            int x = position.getX() + off[0];
            if (x < 0 || x >= side)
                continue;
            int y = position.getY() + off[1];
            if (y < 0 || y >= side)
                continue;
            if (mapa[x][y] == Quadradinho.N || mapa[x][y] == Quadradinho.C) {
                possibleMoves.add(new Posicao(x, y));
            }
        }
        return possibleMoves;
    }

    private Agente getAgentByPosition(Posicao posicao) {
        for (Agente a :
                agentes) {
            if (a.getPosicao().equals(posicao))
                return a;
        }
        return null;
    }

    public void move(Posicao from, Posicao to) {
        mapa[from.getX()][from.getY()] = Quadradinho.N;
        mapa[to.getX()][to.getY()] = Quadradinho.A;
    }

    public Posicao getNearestCartorioByPosition(Posicao agentPosition) {
        int aux = Integer.MAX_VALUE;
        Posicao nearestCartorio = null;
        for (Posicao cartorio :
                cartorios) {
            int distance = Heuristic.manhattanDistance(agentPosition, cartorio);
            if (distance <= aux) {
                aux = distance;
                nearestCartorio = cartorio;
            }
        }
        return nearestCartorio;
    }

    // A* Algorithm
    public ArrayList<Posicao> aStar(Posicao from, Posicao to) {
        PriorityQueue<QueueElement> open = new PriorityQueue<>(new QueueElementComparator());
        open.add(new QueueElement(from, 0));
        HashSet<QueueElement> closed = new HashSet<>();
        while (open.peek() != null && !open.peek().getPosition().equals(to)) {
            QueueElement lowestRank = open.poll();
            closed.add(lowestRank);
            ArrayList<Posicao> possibleMoves = getEmptyPositionsPlusCartorios(lowestRank.getPosition(), offsetMove);
            for (Posicao neighbor :
                    possibleMoves) {
                int cost = lowestRank.getValue() + 1;
                QueueElement neighborQueueElement = new QueueElement(neighbor, cost);
                if (inOpenCostLess(open, neighborQueueElement))
                    open.remove(neighborQueueElement);
                if (notInOpenAndNotInClosed(neighborQueueElement, open, closed))
                    neighborQueueElement.setValue(cost);
                neighborQueueElement.setPriority(cost + Heuristic.manhattanDistance(neighbor, to));
                neighborQueueElement.setParent(lowestRank);
                open.add(neighborQueueElement);
            }
        }
        QueueElement lastVisited = open.peek();
        if (lastVisited == null)
            return new ArrayList<Posicao>();
        return reconstructPath(lastVisited);
    }

    private ArrayList<Posicao> reconstructPath(QueueElement lastVisited) {
        QueueElement current = lastVisited;
        ArrayList<Posicao> path = new ArrayList<>();
        while (current.getParent() != null) {
            path.add(0, current.getPosition());
            current = current.getParent();
        }
        return path;
    }

    private boolean notInOpenAndNotInClosed(QueueElement neighbor, PriorityQueue<QueueElement> open, HashSet<QueueElement> closed) {
        return !open.contains(neighbor) && !closed.contains(neighbor);
    }

    private boolean inOpenCostLess(PriorityQueue<QueueElement> open, QueueElement neighbor) {
        for (QueueElement qe :
                open) {
            if (qe.getPosition().equals(neighbor.getPosition()) && qe.getValue() > neighbor.getValue())
                return true;
        }
        return false;
    }

    public boolean canMove(Posicao position) {
        if (position.getX() < 0 || position.getX() >= side || position.getY() < 0 || position.getY() >= side)
            return false;
        Quadradinho quad = mapa[position.getX()][position.getY()];
        if (quad == Quadradinho.N)
            return true;
        return false;
    }

    public void eraseWomanFromMap(Agente woman) {
        Posicao pos = woman.getPosicao();
        mapa[pos.getX()][pos.getY()] = Quadradinho.N;
    }

    public void rebuildCartorio(Posicao pos) {
        mapa[pos.getX()][pos.getY()] = Quadradinho.C;
    }
}
