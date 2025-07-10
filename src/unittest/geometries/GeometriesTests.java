package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


/**
 * Testing Geometries
 */
public class GeometriesTests {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {
        Geometries geometries = new Geometries();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Some shapes (but not all) are cut
        geometries.add(new Sphere(1, new Point(1, 0, 0)));
        geometries.add(new Plane(new Point(1, 0, 0), new Vector(0, 1, 0)));
        geometries.add(new Triangle(new Point(1, 0, 0), new Point(1, 5, 0), new Point(6, 0, 0)));
        assertEquals(3, geometries.findIntersections(new Ray(new Point(0.5, 4, 0.5), new Vector(0, -1, 0))).size(),
                "findIntersections() failed, Some shapes (but not all) are cut");

        // =============== Boundary Values Tests ==================
        // TC02: No shape is cut
        assertNull(geometries.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, 1, 0))),
                "findIntersections() failed, No shape is cut");

        // TC03: All shapes are cut
        assertEquals(4, geometries.findIntersections(new Ray(new Point(1.5, 1, -0.5), new Vector(-1, -4, 3.5))).size(),
                "findIntersections() failed, All shapes are cut");

        // TC04: Only one shape is cut
        assertEquals(1, geometries.findIntersections(new Ray(new Point(0.5, 4, 3), new Vector(0, -1, 0))).size(),
                "findIntersections() failed, Only one shape is cut");

        // TC05: Empty list
        geometries = new Geometries();
        assertNull(geometries.findIntersections(new Ray(new Point(0.5, 4, 0.5), new Vector(0, -1, 0))),
                "findIntersections(), Empty list");
    }
}