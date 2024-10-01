package main.engine;

import main.controller.GameController;
import main.model.ShootResult;
import main.ui.UserInterface;
import main.utils.factory.ConsoleUIFactory;
import main.utils.factory.SessionManagerFactory;

import java.util.UUID;

public class BattleshipGame {
    private final UserInterface ui;
    private final SessionManager sessionManager;

    public BattleshipGame() {
        this.ui = ConsoleUIFactory.getInstance();
        this.sessionManager = SessionManagerFactory.getInstance();
    }

    public void startGame() {
        UUID sessionId = sessionManager.createSession();
        GameController controller = sessionManager.getSession(sessionId).getController();

        while (!controller.isGameOver()) {
            displayGameState(controller);
            processTurn(controller);
        }

        endGame(controller);
        sessionManager.removeSession(sessionId); // Удаляем сессию после окончания игры
    }

    private void displayGameState(GameController controller) {
        ui.displayMessage("Ваше поле:");
        ui.displayMessage(controller.getPlayerGridState(false)); // Ложь, чтобы показать корабли игрока
        ui.displayMessage("Поле компьютера:");
        ui.displayMessage(controller.getComputerGridState(true)); // Истина, чтобы скрыть корабли компьютера
    }

    private void processTurn(GameController controller) {
        boolean validMove = false;
        while (!validMove) {
            try {
                int[] playerMove = ui.getMove();  // Получаем ход от игрока
                boolean playerHit = controller.playerMove(playerMove[0], playerMove[1]);
                ui.displayMessage(playerHit ? "Попадание!" : "Мимо!");
                validMove = true;
            } catch (IllegalArgumentException e) {
                ui.displayMessage(e.getMessage());
            }
        }

        if (!controller.isGameOver()) {
            ShootResult computerResult = controller.computerMove(); // Компьютер делает ход
            int compRow = computerResult.getRow() + 1; // Добавляем 1 для отображения пользователю
            int compCol = computerResult.getCol() + 1;
            boolean compHit = computerResult.isHit();
            ui.displayMessage("Компьютер выстрелил в координаты (" + compRow + ", " + compCol + ") и " + (compHit ? "попал!" : "промахнулся."));
        }
    }


    private void endGame(GameController controller) {
        if (controller.isPlayerWinner()) {
            ui.displayMessage("Поздравляем, вы победили!");
        } else {
            ui.displayMessage("Компьютер выиграл. Попробуйте снова!");
        }

        // Отображаем итоговые поля с раскрытыми кораблями
        ui.displayMessage("\nИтоговое состояние вашего поля:");
        ui.displayMessage(controller.getPlayerGridState(false)); // Показать все корабли игрока

        ui.displayMessage("Итоговое состояние поля компьютера:");
        ui.displayMessage(controller.getComputerGridState(false)); // Показать все корабли компьютера
    }
}
