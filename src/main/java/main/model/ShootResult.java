package main.model;

public class ShootResult {
    private final int row;
    private final int col;
    private final boolean hit;

    public ShootResult(int row, int col, boolean hit) {
        this.row = row;
        this.col = col;
        this.hit = hit;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHit() {
        return hit;
    }
}
