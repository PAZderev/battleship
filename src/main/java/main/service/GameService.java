package main.service;

import main.model.ShootResult;

public interface GameService {
    boolean playerShoot(int row, int col);
    ShootResult computerShoot();
    String getPlayerGridState(boolean hideShips);
    String getComputerGridState(boolean hideShips);
    boolean isGameOver();
    boolean isPlayerWinner();
}
