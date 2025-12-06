package Position;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sprite.*;
public class PositionTest {

    @Test
    void constructorSetsInitialValues() {
        Position p = new Position(10, 20, 30, 40);

        assertArrayEquals(new double[]{10, 20}, p.getCenterPosition());
        assertArrayEquals(new double[]{30, 40}, p.getDimensions());
    }

    @Test
    void setPositionUpdatesCenter() {
        Position p = new Position(0, 0, 1, 1);

        p.setPostion(50, 60);

        assertArrayEquals(new double[]{50, 60}, p.getCenterPosition());
    }

    @Test
    void setSizeUpdatesDimensions() {
        Position p = new Position(0, 0, 10, 20);

        p.setSize(100, 200);

        assertArrayEquals(new double[]{100, 200}, p.getDimensions());
    }

    @Test
    void setRotationUpdatesAngle() {
        Position p = new Position(0, 0, 1, 1);

        p.setRotationDeg(45);

        assertEquals(45, p.getRotationDeg());
    }

    @Test
    void updatePositionMovesByDelta() {
        Position p = new Position(10, 20, 1, 1);

        p.updatePostion(5, -10);

        assertArrayEquals(new double[]{15, 10}, p.getCenterPosition());
    }
}
