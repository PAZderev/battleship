package main.engine;

import main.controller.GameController;

public class GameSession {
    private final GameController controller;

    public GameSession() {
        this.controller = new GameController();
    }

    public GameController getController() {
        return controller;
    }
}
