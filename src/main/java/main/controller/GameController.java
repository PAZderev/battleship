package main.controller;

import main.service.GameServiceImpl;
import main.model.ShootResult;

public class GameController {
    private final GameServiceImpl gameService;

    public GameController() {
        this.gameService = new GameServiceImpl();
    }

    public boolean playerMove(int row, int col) {
        return gameService.playerShoot(row, col);
    }

    public ShootResult computerMove() {
        return gameService.computerShoot();
    }

    public String getPlayerGridState(boolean hideShips) {
        return gameService.getPlayerGridState(hideShips);
    }

    public String getComputerGridState(boolean hideShips) {
        return gameService.getComputerGridState(hideShips);
    }

    public boolean isGameOver() {
        return gameService.isGameOver();
    }

    public boolean isPlayerWinner() {
        return gameService.isPlayerWinner();
    }
}
