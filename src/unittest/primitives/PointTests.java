package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 */
public class PointTests {

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    public void testAdd() {
        Point p1 = new Point(1, 2, 3);
        Vector v = new Vector(-1, -2, -3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test adding a vector to a point
        assertEquals(new Point(0, 0, 0), p1.add(v),
                "testAdd() Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    public void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);

        // ============ Equivalence Partitions Tests ==============
        // TC01:  Test subtracting two points
        assertEquals(new Vector(1, 1, 1), p2.subtract(p1),
                "testSubtract() Point does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC02:  Test subtracting the same point
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "testSubtract() subtracting the same point");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    public void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);

        // ============ Equivalence Partitions Tests ==============
        // TC01:  Test squared distance between two points
        assertEquals(3.0, p1.distanceSquared(p2),
                "distanceSquared() wrong result");

        // =============== Boundary Values Tests ==================
        //  TC02:  distance squared from point to itself
        assertEquals(p1.distanceSquared(p1), 0, "distance of point to itself is not 0");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    public void testDistance() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);

        // ============ Equivalence Partitions Tests ==============
        //TC01: Test distance between two points
        assertEquals(Math.sqrt(3), p1.distance(p2),
                "distance() wrong result");

        // =============== Boundary Values Tests ==================
        // TC02: distance from point to itself
        assertEquals(0, p1.distance(p1), "distance of point to itself is not 0");
    }
}
