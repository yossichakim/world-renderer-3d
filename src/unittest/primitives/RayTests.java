package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testing Ray
 **/
public class RayTests {

    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Get distance from ray source with positive point
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(2, 0, 0));
        assertEquals(new Point(3, 0, 0), ray.getPoint(2),
                "testGetPoint() failed, Get distance from ray source with positive point");

        // TC02: Get distance from ray source with negative point
        assertEquals(new Point(-1, 0, 0), ray.getPoint(-2),
                "testGetPoint() failed, Get distance from ray source with negative point");

        // =============== Boundary Values Tests ==================
        // TC03: Get distance from ray source with zero point
        assertEquals(new Point(1, 0, 0), ray.getPoint(0),
                "testGetPoint() failed, Get distance from ray source with zero point");
    }

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List Point)}.
     */
    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: A point in the middle of the list is the one closest to the beginning of the foundation
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(2, 0, 0));
        List<Point> points = List.of(new Point(3, 0, 0), new Point(2, 0, 0), new Point(4, 0, 0));
        assertEquals(new Point(2, 0, 0), ray.findClosestPoint(points),
                "testFindClosestPoint() failed, A point in the middle of the list is the one closest to the beginning of the foundation");

        // =============== Boundary Values Tests ==================
        // TC02: An empty list (the method should return a null value)
        points = List.of();
        assertNull(ray.findClosestPoint(points),
                "testFindClosestPoint() failed, An empty list (the method should return a null value)");

        // TC03: The first point is closest to the beginning of the ray
        points = List.of(new Point(2, 0, 0), new Point(3, 0, 0), new Point(4, 0, 0));
        assertEquals(new Point(2, 0, 0), ray.findClosestPoint(points),
                "testFindClosestPoint() failed, The first point is closest to the beginning of the ray");

        // TC04: The last point is closest to the beginning of the ray
        points = List.of(new Point(4, 0, 0), new Point(3, 0, 0), new Point(2, 0, 0));
        assertEquals(new Point(2, 0, 0), ray.findClosestPoint(points),
                "testFindClosestPoint() failed, The last point is closest to the beginning of the ray");
    }
}
