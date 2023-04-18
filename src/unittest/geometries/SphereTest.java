package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    @Test
    void testConstructor(){

        assertDoesNotThrow(()-> new Sphere(0.1,new Point(1,1,1)),
                "The constructor throw error for nothing");
    }
    @Test
    void getNormal() {
        Point p1 = new Point(1,1,1);
        Point p0 = new Point(0,0,0);
        Vector v = p1.subtract(p0).normalize();

        assertEquals(v,new Sphere(1.0,p0).getNormal(p1),
                "getNormal() failed");
    }
}