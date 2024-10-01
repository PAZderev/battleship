import main.model.Grid;
import main.model.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid();
    }

    @Test
    void testGridInitialization() {
        assertNotNull(grid, "Игровое поле должно быть инициализировано");
        assertFalse(grid.areAllShipsSunk(), "Все корабли не должны быть потоплены после инициализации");
    }

    @Test
    void testShootMiss() {
        // Находим пустую ячейку (не 'S')
        int row = -1, col = -1;
        outerLoop:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid.getCell(i, j) != 'S') {
                    row = i;
                    col = j;
                    break outerLoop;
                }
            }
        }
        assertTrue(row != -1 && col != -1, "Не найдена пустая ячейка для тестирования промаха");
        boolean result = grid.shoot(row, col);
        assertFalse(result, "Стрельба по пустой ячейке должна быть промахом");
        assertEquals('O', grid.getCell(row, col), "Ячейка должна быть помечена 'O' после промаха");
    }

    @Test
    void testShootHit() {
        // Находим ячейку с кораблем ('S')
        int row = -1, col = -1;
        outerLoop:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid.getCell(i, j) == 'S') {
                    row = i;
                    col = j;
                    break outerLoop;
                }
            }
        }
        assertTrue(row != -1 && col != -1, "Не найдена ячейка с кораблем для тестирования попадания");
        boolean result = grid.shoot(row, col);
        assertTrue(result, "Стрельба по кораблю должна быть попаданием");
        assertEquals('X', grid.getCell(row, col), "Ячейка должна быть помечена 'X' после попадания");
    }

    @Test
    void testAllShipsSunk() {
        // Симулируем потопление всех кораблей
        for (Ship ship : grid.getShips()) {
            for (int[] coord : ship.getCoordinates()) {
                grid.shoot(coord[0], coord[1]);
            }
        }
        assertTrue(grid.areAllShipsSunk(), "Все корабли должны быть потоплены после попаданий по всем ячейкам кораблей");
    }
}
