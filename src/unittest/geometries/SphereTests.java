package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 *
 * @author Benjamin Machlev and Yossi Chakim
 */
class SphereTests {
    /**
     * Test method for {@link geometries.Sphere#Sphere(double, Point)} Sphere(geometries.SphereTest)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for correct radius
        assertDoesNotThrow(() -> new Sphere(0.1, new Point(1, 1, 1)),
                "The constructor throw error for nothing");
    }

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)} Sphere(geometries.SphereTest)}.
     */
    @Test
    void getNormal() {
        Point p1 = new Point(1, 1, 1);
        Point p0 = new Point(0, 0, 0);
        Vector v = p1.subtract(p0).normalize();

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for correct normal
        assertEquals(v, new Sphere(1.0, p0).getNormal(p1),
                "getNormal() failed");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(Ray)} Sphere(geometries.SphereTest)}.
     */
    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point(1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "testFindIntersections() failed, Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));

        assertEquals(2, result.size(),
                "testFindIntersections() failed, Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result,
                "testFindIntersections() failed, Wrong points");

        // TC03: Ray starts inside the sphere (1 point)
        Point intersectionPoint = new Point(0, 0, 0);
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(-1.5, 0, 0)));
        assertEquals(1, result.size(),
                "testFindIntersections() failed, Wrong number of points");
        assertEquals(intersectionPoint, result.get(0),
                "Ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        Ray ray = new Ray(new Point(2, 0, 0), new Vector(3, 0, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC05: Ray starts at sphere and goes inside (1 point)
        Point p3 = new Point(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 0)));
        assertEquals(1, result.size(),
                "testFindIntersections() failed, Wrong number of points");
        assertEquals(p3, result.get(0),
                "testFindIntersections() failed, Wrong points");

        // TC06: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(1, 1, 0), new Vector(0, 1, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");

        // **** Group: Ray's line goes through the center
        // TC07: Ray starts before the sphere (2 points)
        p1 = new Point(0, 0, 0);
        p2 = new Point(2, 0, 0);
        result = sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(2, result.size(),
                "testFindIntersections() failed, Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result,
                "testFindIntersections() failed, Wrong points");

        // TC08: Ray starts at sphere and goes inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(),
                "testFindIntersections() failed, Wrong number of points");
        assertEquals(p1, result.get(0),
                "testFindIntersections() failed, Wrong points");

        // TC09: Ray starts inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(),
                "testFindIntersections() failed, Wrong number of points");
        assertEquals(p1, result.get(0),
                "testFindIntersections() failed, Ray starts inside sphere, passing through center");

        // TC10: Ray starts at the center (1 point)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(),
                "testFindIntersections() failed, Wrong number of points");
        assertEquals(p1, result.get(0),
                "testFindIntersections() failed, Ray starts at center of the sphere");

        // TC11: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(1, 1, 0), new Vector(0, 1, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");

        // TC12: Ray starts after sphere (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(3, 0, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC13: Ray starts before the tangent point
        ray = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");

        // TC14: Ray starts at the tangent point
        ray = new Ray(new Point(1, 1, 0), new Vector(1, 0, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");

        // TC15: Ray starts after the tangent point
        ray = new Ray(new Point(2, 1, 0), new Vector(1, 0, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");

        // **** Group: Special cases
        // TC16: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        ray = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));
        assertNull(sphere.findIntersections(ray),
                "testFindIntersections() failed, Ray's line out of sphere");
    }
}