package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayTests {

    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Get distance from ray source with positive point
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(2, 0, 0));
        assertEquals(new Point(3, 0, 0), ray.getPoint(2),
                "testGetPoint() failed, Get distance from ray source with positive point");

        // TC02: Get distance from ray source with negative point
        assertEquals(new Point(-1, 0, 0), ray.getPoint(-2),
                "testGetPoint() failed, Get distance from ray source with negative point");

        // =============== Boundary Values Tests ==================
        // TC03: Get distance from ray source with zero point
        assertEquals(new Point(1, 0, 0), ray.getPoint(0),
                "testGetPoint() failed, Get distance from ray source with zero point");
    }
}
