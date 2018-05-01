package com.company;

import java.util.ArrayList;
import java.util.Random;

public enum Direction {

    NORTH, SOUTH, WEST, EAST;

    private int[] offset;

    static {
        NORTH.offset = new int[]{+1, 0};
        EAST.offset = new int[]{0, +1};
        SOUTH.offset = new int[]{+1, 0};
        WEST.offset = new int[]{0, -1};
    }

    public int[] getOffset() {
        return offset;
    }

    public static Direction randomDirection() {
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        possibleDirections.add(Direction.NORTH);
        possibleDirections.add(Direction.EAST);
        possibleDirections.add(Direction.SOUTH);
        possibleDirections.add(Direction.WEST);
        Random rand = new Random();
        return possibleDirections.get(rand.nextInt(possibleDirections.size()));
    }

    public static Direction randomDirection(Direction dir) {
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        possibleDirections.add(Direction.NORTH);
        possibleDirections.add(Direction.EAST);
        possibleDirections.add(Direction.SOUTH);
        possibleDirections.add(Direction.WEST);
        possibleDirections.remove(dir);
        Random rand = new Random();
        return possibleDirections.get(rand.nextInt(possibleDirections.size()));
    }

}
