package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testConstructor() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a proper result.
        assertDoesNotThrow(()-> new Plane(new Point(1,0,0),new Point(0,1,0),new Point(0,0,1)),
                "The constructor throw error for nothing");

        // ============ Boundary Values Tests =============
        // TC02: Test when a point equal to all points.
        assertThrows(IllegalArgumentException.class,
                ()->new Plane(new Point(1,0,0),new Point(1,0,0),new Point(1,0,0)),
                "The constructor don't throw error exception for the same three points");

        // TC03: Test when b point equal to a point.
        assertThrows(IllegalArgumentException.class,
                ()->new Plane(new Point(1,0,0),new Point(1,0,0),new Point(2,0,0)),
                "The constructor don't throw error exception for the same three points");

        // TC04: Test when a point equal to c point.
        assertThrows(IllegalArgumentException.class,
                ()->new Plane(new Point(1,0,0),new Point(2,0,0),new Point(1,0,0)),
                "The constructor don't throw error exception for the same three points");

        // TC05: Test when b point equal to c point.
        assertThrows(IllegalArgumentException.class,
                ()->new Plane(new Point(2,0,0),new Point(1,0,0),new Point(1,0,0)),
                "The constructor don't throw error exception for the same three points");

        // TC06: Test when all points on the same line.
        assertThrows(IllegalArgumentException.class,
                ()->new Plane(new Point(1,0,0),new Point(2,0,0),new Point(3,0,0)),
                "The constructor don't throw error exception for the three points in the same line");

    }

    @Test
    void getNormal() {
    }

    @Test
    void testGetNormal() {
    }
}