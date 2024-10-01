import main.model.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {


    @Test
    void testShipHitAndSink() {
        Ship ship = new Ship(2);
        int[][] coordinates = {{0, 0}, {0, 1}};
        ship.setCoordinates(coordinates);

        ship.checkHit(0, 0);
        assertFalse(ship.isSunk(), "Корабль не должен быть потоплен после одного попадания");

        ship.checkHit(0, 1);
        assertTrue(ship.isSunk(), "Корабль должен быть потоплен после всех попаданий");
    }

    @Test
    void testShipMiss() {
        Ship ship = new Ship(2);
        int[][] coordinates = {{0, 0}, {0, 1}};
        ship.setCoordinates(coordinates);

        ship.checkHit(1, 1);
        assertFalse(ship.isSunk(), "Корабль не должен быть потоплен при промахе");
    }
}
