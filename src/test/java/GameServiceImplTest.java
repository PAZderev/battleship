import main.model.Grid;
import main.model.ShootResult;
import main.service.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameServiceImpl();
    }

    @Test
    void testPlayerShoot() {
        // Находим ячейку для стрельбы
        int row = 0;
        int col = 0;

        // Проверяем, что выстрел не вызывает исключения
        assertDoesNotThrow(() -> {
            boolean result = gameService.playerShoot(row, col);
            assertNotNull(result, "Результат выстрела не должен быть null");
        });
    }

    @Test
    void testComputerShoot() {
        ShootResult result = gameService.computerShoot();
        assertNotNull(result, "Результат выстрела компьютера не должен быть null");

        int row = result.getRow();
        int col = result.getCol();
        boolean hit = result.isHit();

        assertTrue(row >= 0 && row < Grid.GRID_SIZE, "Строка должна быть от 0 до " + (Grid.GRID_SIZE - 1));
        assertTrue(col >= 0 && col < Grid.GRID_SIZE, "Столбец должен быть от 0 до " + (Grid.GRID_SIZE - 1));
        // hit - булево значение, всегда true или false
    }

    @Test
    void testIsGameOver() {
        assertFalse(gameService.isGameOver(), "Игра не должна быть закончена после инициализации");

        // Симулируем потопление всех кораблей компьютера
        Grid computerGrid = gameService.getComputerGrid();
        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            for (int j = 0; j < Grid.GRID_SIZE; j++) {
                if (computerGrid.getCell(i, j) == Grid.SHIP_CELL) {
                    computerGrid.shoot(i, j);
                }
            }
        }

        assertTrue(gameService.isGameOver(), "Игра должна быть закончена после потопления всех кораблей компьютера");
        assertTrue(gameService.isPlayerWinner(), "Игрок должен быть победителем");
    }
}
