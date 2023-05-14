package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Benjamin Machlev and Yossi Chakim
 */
class VectorTests {

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test adding two vectors with opposite direction
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(new Vector(-1, -2, -3), v1.add(new Vector(-2, -4, -6)),
                "testAdd() vector wrong result");

        // =============== Boundary Values Tests ==================
        //TC02: Test adding a vector with opposite direction to itself
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)),
                "testAdd() vector opposite direction");
    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test subtracting two vectors with opposite direction
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(new Vector(3, 6, 9), v1.subtract(new Vector(-2, -4, -6)),
                "testSubtract() vector wrong result");

        // =============== Boundary Values Tests ==================
        //TC02: Test subtracting a vector with opposite direction to itself
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(new Vector(1, 2, 3)),
                "testSubtract() vector opposite direction");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test scaling a vector by a positive scalar
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(new Vector(2, 4, 6), v1.scale(2),
                "testScale() vector");

        //TC02: Test scaling a vector by a negative scalar
        assertEquals(new Vector(-2, -4, -6), v1.scale(-2),
                "testScale() vector - negative scalar");

        // =============== Boundary Values Tests ==================
        //TC03: Test scaling a vector by zero
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0),
                "testScale() vector by zero");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test dot product between orthogonal vectors
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        assertEquals(0, v1.dotProduct(v3),
                "dotProduct() for orthogonal vectors is not zero");

        //TC02: Test dot product between two vectors with a known result
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals(0, v1.dotProduct(v2) + 28,
                "dotProduct() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.000001,
                "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(0, vr.dotProduct(v3), 0.000001,
                "crossProduct() result is not orthogonal to its operands");

        assertEquals(0, vr.dotProduct(v1), 0.000001,
                "crossProduct() result is not orthogonal to its operands");

        // =============== Boundary Values Tests ==================
        // TC03: test zero vector from cross-product of co-lined vectors
        Vector v2 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2),
                "crossProduct() for parallel vectors");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test the squared length of a vector with non-zero coordinates
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(14, v1.lengthSquared(),
                "lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test the length of a vector with non-zero coordinates
        Vector v1 = new Vector(0, 3, 4);
        assertEquals(5, v1.length(),
                "length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that normalizing a vector produces a unit vector in the same direction
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(1, u.length(),
                "testNormalize() vector is not a unit vector");

        //TC02: Test that the normalized vector is in the same direction as the original vector
        assertTrue(v.dotProduct(u) > 0,
                "testNormalize() vector is opposite to the original one");

        // =============== Boundary Values Tests ==================
        //TC03: Test that an exception is thrown when trying to compute the cross product
        // of two vectors that are parallel (i.e. the original vector and its normalized version)
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u),
                "testNormalize() vector is not parallel to the original one");
    }
}