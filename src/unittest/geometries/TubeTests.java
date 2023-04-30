package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 *
 * @author Benjamin Machlev and Yossi Chakim
 */
class TubeTests {

    /**
     * Test method for {@link geometries.Tube#Tube(double, Ray)}  Tube(geometries.TubeTest)}.
     */
    @Test
    void testConstructor() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: The constructor throw error for nothing
        assertDoesNotThrow(() -> new Tube(0.1, new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "The constructor throw error for nothing");
    }

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}   Tube(geometries.TubeTest)}.
     */
    @Test
    void getNormal() {

        Tube tube = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));

        // ================== Equivalence Partitions Tests =================
        //TC01: Test when the point is orthogonal to the ray's head
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(1, 0, 1)),
                "ERROR: The calculation of normal to the tube is not calculated correctly");

        // =================== Boundary Values Tests ======================
        //TC02: Test when the point is orthogonal to the ray's head goes to the ZERO vector
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0, 1)),
                "Wrong vector against p0");
    }
}