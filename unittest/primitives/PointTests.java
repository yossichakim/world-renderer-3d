package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Benjamin Machlev and Yossi Chakim
 */
public class PointTests {
    // ============ Equivalence Partitions Tests ==============
    // Test cases that cover the different equivalence partitions

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    public void testAdd() {
        Point p1 = new Point(1, 2, 3);
        Vector v = new Vector(1, 1, 1);
        Point p2 = p1.add(v);
        assertEquals(new Point(2, 3, 4), p2, "add() wrong result");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    public void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);
        Vector v = p1.subtract(p2);
        assertEquals(new Vector(-1, -1, -1), v, "subtract() wrong result");
    }

    // =============== Boundary Values Tests ==================
    // Test cases that cover the boundary values

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    public void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);
        double result = p1.distanceSquared(p2);
        assertEquals(3.0, result, 0, "distanceSquared() wrong result");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    public void testDistance() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);
        double result = p1.distance(p2);
        assertEquals(Math.sqrt(3), result, 0, "distance() wrong result");
    }
}
