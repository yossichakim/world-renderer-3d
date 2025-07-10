package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 *
 * @author Benjamin Machlev and Yossi Chakim
 */
class PlaneTests {

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}  Plane(geometries.PlaneTest)}.
     */
    @Test
    void testConstructor() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a proper result.
        assertDoesNotThrow(() -> new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "The constructor throw error for nothing");

        // ============ Boundary Values Tests =============
        // TC02: Test when a point equal to all points.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(1, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC03: Test when b point equal to a point.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(2, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC04: Test when a point equal to c point.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(1, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC05: Test when b point equal to c point.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(2, 0, 0), new Point(1, 0, 0), new Point(1, 0, 0)),
                "The constructor don't throw error exception for the same three points");

        // TC06: Test when all points on the same line.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)),
                "The constructor don't throw error exception for the three points in the same line");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}  Plane(geometries.PlaneTest)}.
     */
    @Test
    void testGetNormalPoint() {
        Point p1 = new Point(1, 1, 1);
        Plane pl = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));

        // ============ Equivalence Partitions Tests =============
        // TC01: Test for a proper result.
        assertEquals(1, pl.getNormal(p1).length(),
                "testGetNormalPoint() failed");
        assertEquals(0, pl.getNormal().dotProduct(new Point(1, 0, 0).subtract(new Point(0, 1, 0))), 0.000001,
                "testGetNormal() failed");
        assertEquals(0, pl.getNormal().dotProduct(new Point(1, 0, 0).subtract(new Point(0, 0, 1))), 0.000001,
                "testGetNormal() failed");
    }

    /**
     * Test method for {@link Plane#getNormal()}  Plane(geometries.PlaneTest)}.
     */
    @Test
    void testGetNormal() {
        Plane pl = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a proper result.
        assertEquals(1.0, pl.getNormal().length(), 0.000001,
                "testGetNormal() failed");

        // ================= Boundary Values Tests ==================
        // TC02: Test for a proper result.
        assertEquals(0, pl.getNormal().dotProduct(new Point(1, 0, 0).subtract(new Point(0, 1, 0))), 0.000001,
                "testGetNormal() failed");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}  Plane(geometries.PlaneTest)}.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Ray intersects the plane
        Plane plane = new Plane(new Point(1, 0, 0), new Vector(0, 1, 0));
        Ray ray = new Ray(new Point(0, -2, 0), new Vector(1, 1, 0));
        assertEquals(1, plane.findIntersections(ray).size(),
                "testFindIntersections() failed, wrong number of points");

        //TC02: Ray does not intersect the plane
        ray = new Ray(new Point(0, -2, 0), new Vector(1, 0, 0));
        assertNull(plane.findIntersections(ray),
                "testFindIntersections() failed, ray does not intersect the plane");

        // =============== Boundary Values Tests ==================
        //TC03: Ray is parallel to the plane and not included in the plane (0 points)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, -1, 0));
        assertNull(plane.findIntersections(ray),
                "testFindIntersections() failed, ray is parallel to the plane and not included in the plane");

        //TC04: Ray is parallel to the plane and included in the plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(1, 0, 0));
        assertNull(plane.findIntersections(ray),
                "testFindIntersections() failed, ray is parallel to the plane and included in the plane");

        //TC05: Ray is orthogonal to the plane and before the plane (1 point)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, 1, 0));
        assertEquals(1, plane.findIntersections(ray).size(),
                "testFindIntersections() failed, ray is orthogonal to the plane and before the plane");

        //TC06: Ray is orthogonal to the plane and in the plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(0, 1, 0));
        assertNull(plane.findIntersections(ray),
                "testFindIntersections() failed, ray is orthogonal to the plane and in the plane");

        //TC07: Ray is orthogonal to the plane and after the plane (0 points)
        ray = new Ray(new Point(-1, 1, 0), new Vector(0, 1, 0));
        assertNull(plane.findIntersections(ray),
                "testFindIntersections() failed, ray is orthogonal to the plane and after the plane");

        //TC08: Ray is neither orthogonal nor parallel to and begins at the plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(1, 1, 0));
        assertNull(plane.findIntersections(ray),
                "testFindIntersections() failed, ray is neither orthogonal nor parallel to and begins at the plane");

        //TC09: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears
        // as reference point in the plane (0 points)
        ray = new Ray(new Point(1, 0, 0), new Vector(1, 1, 0));
        assertNull(plane.findIntersections(ray),
                "testFindIntersections() failed, ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane");
    }
}