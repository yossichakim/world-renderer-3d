package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit tests for geometries.Cylinder class
 *
 * @author Benjamin Machlev and Yossi Chakim
 */

class CylinderTests {

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */

    @Test
    public void testGetNormal() {
        Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        //The cylinder that is used in the tests.
        Cylinder cylinder = new Cylinder(1, r, 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: on the round surface
        Point p1 = new Point(0, 1, 0.5);
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(p1),
                "testGetNormal() failed, on the round surface.");

        // TC02: points on side 1
        Point p2 = new Point(0, 0.5, 0);
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(p2),
                "testGetNormal() failed, points on side 1.");

        // TC03: points on side 2
        Point p3 = new Point(0, 0.5, 1);
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(p3),
                "testGetNormal() failed, points on side 2.");

        // =============== Boundary Values Tests ==================
        // TC04: points on side 1 edge
        Point p4 = new Point(0, 1, 0);
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(p4),
                "testGetNormal() failed, points on side 1 edge.");

        // TC05: points on side 2 edge
        Point p5 = new Point(0, 1, 1);
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(p5),
                "testGetNormal() failed, points on side 2 edge.");
    }
}