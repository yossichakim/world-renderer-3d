package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 *
 * @author Benjamin Machlev and Yossi Chakim
 */
class TriangleTests {

    /**
     * Test method for {@link geometries.Triangle#Triangle(Point, Point, Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a proper result.
        assertDoesNotThrow(() -> new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "The constructor throw error for nothing");

        // ============ Boundary Values Tests =============
        // TC02: Test when a point equal to all points.
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(new Point(1, 0, 0), new Point(1, 0, 0), new Point(1, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC03: Test when b point equal to a point.
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(new Point(1, 0, 0), new Point(1, 0, 0), new Point(2, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC04: Test when a point equal to c point.
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(new Point(1, 0, 0), new Point(2, 0, 0), new Point(1, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC05: Test when b point equal to c point.
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(new Point(2, 0, 0), new Point(1, 0, 0), new Point(1, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC06: Test when all points on the same line.
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)),
                "The constructor don't throw error exception for the three points in the same line");
    }

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(1, 1, 1);
        Triangle tr = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a proper result.
        assertEquals(1, tr.getNormal(p1).length(),
                "testGetNormal() failed");

        // ================= Boundary Values Tests ==================
        // TC02: Test for a proper result.
        assertEquals(0, tr.getNormal(p1).dotProduct(new Point(1,0,0).subtract(new Point(0,1,0))), 0.000001,
                "testGetNormal() failed");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections(){
        //============ Equivalence Partitions Tests ==============
        // TC01: Inside triangle
        Triangle tr = new Triangle(new Point(1, 0, 0), new Point(1, 5, 0), new Point(6, 0, 0));
        Ray ray = new Ray(new Point(3, 3, 2), new Vector(-1, -1, -4));
        assertEquals(1, tr.findIntersections(ray).size(),
                "testFindIntersections() failed, Inside triangle");

        // TC02: Outside against edge
        ray = new Ray(new Point(3, 3, 2), new Vector(1, 1, -4));
        assertNull(tr.findIntersections(ray),
                "testFindIntersections() failed, Outside against edge");

        // TC03: Outside against vertex
        ray = new Ray(new Point(3, 3, 2), new Vector(-5, 5.5, -4));
        assertNull(tr.findIntersections(ray),
                "testFindIntersections() failed, Outside against vertex");

        //============ Boundary Values Tests ==============
        // TC04: On vertex
        ray = new Ray(new Point(1, 0, 3), new Vector(0, 0, -1));
        assertNull(tr.findIntersections(ray),
                "testFindIntersections() failed, On vertex");

        // TC05: On edge
        ray = new Ray(new Point(1, 0, 3), new Vector(1, 0, -6));
        assertNull(tr.findIntersections(ray),
                "testFindIntersections() failed, On edge");

        // TC06: On edge's continuation
        ray = new Ray(new Point(0.5, 0, 3), new Vector(0, 0, -1));
        assertNull(tr.findIntersections(ray),
                "testFindIntersections() failed, On edge's continuation");
    }
}