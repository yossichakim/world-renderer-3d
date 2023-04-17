package primitives;

import org.junit.jupiter.api.Test;
import java.lang.IllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTests {

    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        //Vector v2 = new Vector(-2,-4,-6);
        //Vector v3 = new Vector(0,3,-2);

        assertEquals(new Vector(-1,-2,-3), v1.add(new Vector(-2, -4, -6)),
                "testAdd() vector wrong result");

        assertThrows( IllegalArgumentException.class, ()-> v1.add(new Vector(-1, -2, -3)),
           "testAdd() vector opposite direction");
    }

    @Test
    void testScale() {
        Vector v1 = new Vector(1, 2, 3);

        assertEquals(new Vector(2,4,6),v1.scale(2),
                "testScale() vector");

        assertEquals(new Vector(-2,-4,-6),v1.scale(-2),
                "testScale() vector - negative scalar");

        assertThrows( IllegalArgumentException.class, ()-> v1.scale(0),
                "testScale() vector by zero");
    }

    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2,-4,-6);
        Vector v3 = new Vector(0,3,-2);

        assertEquals(0 ,v1.dotProduct(v3),
                "dotProduct() for orthogonal vectors is not zero");

        assertEquals(0 ,v1.dotProduct(v2) + 28,
                "dotProduct() wrong value");
    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2,-4,-6);
        Vector v3 = new Vector(0,3,-2);;
        Vector vr = v1.crossProduct(v3);

        assertTrue(isZero(vr.length() - v1.length() * v3.length()),
                "crossProduct() wrong result length");

        assertTrue(isZero(vr.dotProduct(v1)) || isZero(vr.dotProduct(v3)),
                "crossProduct() result is not orthogonal to its operands");

        assertThrows( IllegalArgumentException.class, ()-> v1.crossProduct(v2),
                "crossProduct() for parallel vectors");
    }

    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(1,2,3);
        assertEquals(14,v1.lengthSquared(),
                "lengthSquared() wrong value");
    }

    @Test
    void testLength() {
        Vector v1 = new Vector(0,3,4);
        assertEquals(5,v1.length(),
                "length() wrong value");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        assertEquals(1,u.length(),
                "testNormalize() vector is not a unit vector");

        assertTrue(v.dotProduct(u) > 0,
                "testNormalize() vector is opposite to the original one");

        assertThrows( IllegalArgumentException.class, ()-> v.crossProduct(u),
                "testNormalize() vector is not parallel to the original one");
    }
}