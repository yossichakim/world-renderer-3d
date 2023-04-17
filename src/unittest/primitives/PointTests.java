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
        Vector v = new Vector(-1, -2, -3);

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

        assertEquals(new Vector(1, 1, 1),p2.subtract(p1) ,
                "testSubtract() Point does not work correctly");
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

        assertEquals(3.0, p1.distanceSquared(p2),
                "distanceSquared() wrong result");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    public void testDistance() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);

        assertEquals(Math.sqrt(3), p1.distance(p2),
                "distance() wrong result");
    }
}
