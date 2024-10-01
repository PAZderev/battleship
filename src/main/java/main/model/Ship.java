package main.model;

public class Ship {
    private final int size;
    private int hits;
    private int[][] coordinates;

    public Ship(int size) {
        this.size = size;
        this.hits = 0;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public void checkHit(int row, int col) {
        for (int[] coord : coordinates) {
            if (coord[0] == row && coord[1] == col) {
                hits++;
                break;
            }
        }
    }

    public boolean isSunk() {
        return hits >= size;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }
}
