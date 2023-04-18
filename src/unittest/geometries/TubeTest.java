package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 * @author Benjamin Machlev and Yossi Chakim
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#Tube(double, Ray)}  Tube(geometries.TubeTest)}.
     */
    @Test
    void testConstructor(){

        // =============== Boundary Values Tests ==================
        assertDoesNotThrow(()->new Tube(0.1,new Ray(new Point(1,1,1),new Vector(1,1,1))),
                "The constructor throw error for nothing");
    }

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}   Tube(geometries.TubeTest)}.
     */
    @Test
    void getNormal() {
        Point p0 = new Point(1,1,1);
        Vector v = new Vector(1,0,0);
        Ray ray = new Ray(p0,v);
        Tube tube = new Tube(1.0,ray);
        Point p1 = new Point(2,1,1);
        Point p2 = new Point(2,2,2);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(0,1,1).normalize(),tube.getNormal(p2),
                "getNormal() failed");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->tube.getNormal(p1),
                "p1 and p0 are the same");
    }
}