package main.service;

import main.model.Grid;
import main.model.ShootResult;

import java.util.Random;

public class GameServiceImpl implements GameService {
    private static final int GRID_SIZE = Grid.GRID_SIZE; // Предполагается, что GRID_SIZE публичная в Grid

    private final Grid playerGrid;
    private final Grid computerGrid;

    public GameServiceImpl() {
        this.playerGrid = new Grid();
        this.computerGrid = new Grid();
    }

    @Override
    public boolean playerShoot(int row, int col) {
        if (computerGrid.isCellShot(row, col)) {
            throw new IllegalArgumentException("Вы уже стреляли в эти координаты.");
        }
        return computerGrid.shoot(row, col);
    }

    @Override
    public ShootResult computerShoot() {
        int[] move = getRandomMove();
        boolean hit = playerGrid.shoot(move[0], move[1]);
        return new ShootResult(move[0], move[1], hit);
    }

    @Override
    public String getPlayerGridState(boolean hideShips) {
        return playerGrid.display(hideShips);
    }

    @Override
    public String getComputerGridState(boolean hideShips) {
        return computerGrid.display(hideShips);
    }

    @Override
    public boolean isGameOver() {
        return playerGrid.areAllShipsSunk() || computerGrid.areAllShipsSunk();
    }

    @Override
    public boolean isPlayerWinner() {
        return computerGrid.areAllShipsSunk();
    }

    private int[] getRandomMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(GRID_SIZE);
            col = random.nextInt(GRID_SIZE);
        } while (playerGrid.isCellShot(row, col));
        return new int[]{row, col};
    }

    public Grid getComputerGrid() {
        return computerGrid;
    }

    public Grid getPlayerGrid() {
        return playerGrid;
    }
}
