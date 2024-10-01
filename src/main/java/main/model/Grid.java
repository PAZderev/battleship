package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
    public static final int GRID_SIZE = 10;

    // Константы для состояния клеток
    public static final char SHIP_CELL = 'S';
    private static final char HIT_CELL = 'X';
    private static final char MISS_CELL = 'O';
    private static final char EMPTY_CELL = '~';

    // Константы для ориентации кораблей
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int ORIENTATION_COUNT = 2;

    // Константы для проверки соседних клеток
    private static final int ADJACENT_CELL_START = -1;
    private static final int ADJACENT_CELL_END = 1;

    // Массив размеров кораблей
    private static final int[] SHIP_SIZES = {4, 3, 3, 2, 2, 1, 1};

    private final char[][] grid;
    private final List<Ship> ships;

    public Grid() {
        this.grid = new char[GRID_SIZE][GRID_SIZE];
        this.ships = generateShips();
    }

    public boolean areAllShipsSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    public boolean shoot(int row, int col) {
        if (grid[row][col] == SHIP_CELL) {
            grid[row][col] = HIT_CELL;
            ships.forEach(ship -> ship.checkHit(row, col));
            return true; // Попадание
        } else if (grid[row][col] == 0 || grid[row][col] == EMPTY_CELL) {
            grid[row][col] = MISS_CELL;
            return false; // Промах
        }
        return false; // Уже стреляли сюда
    }

    public boolean isCellShot(int row, int col) {
        return grid[row][col] == HIT_CELL || grid[row][col] == MISS_CELL;
    }

    public String display(boolean hideShips) {
        StringBuilder sb = new StringBuilder();

        // Добавляем нумерацию столбцов
        sb.append("   ");
        for (int col = 1; col <= GRID_SIZE; col++) {
            sb.append(col).append(" ");
        }
        sb.append("\n");

        for (int row = 0; row < GRID_SIZE; row++) {
            // Добавляем нумерацию строк
            sb.append(String.format("%2d ", row + 1));
            for (int col = 0; col < GRID_SIZE; col++) {
                char cell = grid[row][col];
                if (hideShips && cell == SHIP_CELL) {
                    sb.append(EMPTY_CELL).append(" ");
                } else {
                    sb.append(cell == 0 ? EMPTY_CELL + " " : cell + " ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<Ship> generateShips() {
        List<Ship> ships = new ArrayList<>();
        Random random = new Random();

        for (int size : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                int orientation = random.nextInt(ORIENTATION_COUNT); // 0 - горизонтально, 1 - вертикально
                int row, col;

                if (orientation == HORIZONTAL) { // Горизонтально
                    row = random.nextInt(GRID_SIZE);
                    col = random.nextInt(GRID_SIZE - size + 1);
                } else { // Вертикально
                    row = random.nextInt(GRID_SIZE - size + 1);
                    col = random.nextInt(GRID_SIZE);
                }

                if (canPlaceShip(row, col, size, orientation)) {
                    int[][] coordinates = new int[size][2];
                    for (int i = 0; i < size; i++) {
                        int currentRow = row + (orientation == VERTICAL ? i : 0);
                        int currentCol = col + (orientation == HORIZONTAL ? i : 0);
                        grid[currentRow][currentCol] = SHIP_CELL;
                        coordinates[i][0] = currentRow;
                        coordinates[i][1] = currentCol;
                    }
                    Ship ship = new Ship(size);
                    ship.setCoordinates(coordinates);
                    ships.add(ship);
                    placed = true;
                }
            }
        }
        return ships;
    }

    private boolean canPlaceShip(int row, int col, int size, int orientation) {
        for (int i = 0; i < size; i++) {
            int currentRow = row + (orientation == VERTICAL ? i : 0);
            int currentCol = col + (orientation == HORIZONTAL ? i : 0);

            if (grid[currentRow][currentCol] == SHIP_CELL) {
                return false; // Пересечение с другим кораблем
            }

            if (!isCellAvailable(currentRow, currentCol)) {
                return false; // Соприкосновение с другим кораблем
            }
        }
        return true;
    }

    private boolean isCellAvailable(int row, int col) {
        for (int i = ADJACENT_CELL_START; i <= ADJACENT_CELL_END; i++) {
            int r = row + i;
            if (r < 0 || r >= GRID_SIZE) continue;
            for (int j = ADJACENT_CELL_START; j <= ADJACENT_CELL_END; j++) {
                int c = col + j;
                if (c < 0 || c >= GRID_SIZE) continue;
                if (grid[r][c] == SHIP_CELL) {
                    return false; // Соприкосновение с другим кораблем
                }
            }
        }
        return true;
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public List<Ship> getShips() {
        return ships;
    }
}
