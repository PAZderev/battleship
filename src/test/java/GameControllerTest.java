import main.controller.GameController;
import main.model.Grid;
import main.model.ShootResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController();
    }

    @Test
    void testPlayerMove() {
        int row = 0;
        int col = 0;

        assertDoesNotThrow(() -> {
            boolean result = controller.playerMove(row, col);
            assertNotNull(result, "Результат хода игрока не должен быть null");
        });
    }

    @Test
    void testComputerMove() {
        ShootResult result = controller.computerMove();
        assertNotNull(result, "Результат хода компьютера не должен быть null");

        int row = result.getRow();
        int col = result.getCol();
        boolean hit = result.isHit();

        assertTrue(row >= 0 && row < Grid.GRID_SIZE, "Строка должна быть в пределах игрового поля");
        assertTrue(col >= 0 && col < Grid.GRID_SIZE, "Столбец должен быть в пределах игрового поля");
        // hit - булево значение, всегда true или false
    }

    @Test
    void testIsGameOver() {
        assertFalse(controller.isGameOver(), "Игра не должна быть закончена после инициализации");
    }
}
