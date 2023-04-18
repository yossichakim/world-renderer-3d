package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 * @author Benjamin Machlev and Yossi Chakim
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#Sphere(double, Point)} Sphere(geometries.SphereTest)}.
     */
    @Test
    void testConstructor(){

        // =============== Boundary Values Tests ==================
        assertDoesNotThrow(()-> new Sphere(0.1,new Point(1,1,1)),
                "The constructor throw error for nothing");
    }

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)} Sphere(geometries.SphereTest)}.
     */
    @Test
    void getNormal() {
        Point p1 = new Point(1,1,1);
        Point p0 = new Point(0,0,0);
        Vector v = p1.subtract(p0).normalize();

        // ============ Equivalence Partitions Tests ==============
        assertEquals(v,new Sphere(1.0,p0).getNormal(p1),
                "getNormal() failed");
    }
}