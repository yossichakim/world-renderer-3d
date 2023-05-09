package geometries;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import primitives.*;

/**
 * Testing Polygons
 *
 * @author Dan
 */
public class PolygonTests {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 1, 1)),
                "Failed constructing a correct polygon");

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertex on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertices on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertices on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                {new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1)};
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertEquals(0d,
                    result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])),
                    0.0000001,
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {
        Polygon polygon = new Polygon(new Point(-0.5, -0.5, 0), new Point(0, 1, 0), new Point(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects inside polygon.
        Ray ray = new Ray(new Point(0.25, 0.25, 1), new Vector(0.25, 0, -1));
        List<Point> expRes = List.of(new Point(0.5, 0.25, 0));
        List<Point> res = polygon.findIntersections(ray);
        assertEquals(res.size(), 1,
                "Ray intersects inside polygon EP doesn't work.");
        assertEquals(res, expRes,
                "Ray intersects inside polygon EP doesn't work.");


        // TC02: Ray outside polygon against vertex.
        ray = new Ray(new Point(0.25, 0.25, 1), new Vector(1.5, -0.5, -1));
        assertNull(polygon.findIntersections(ray), "Ray outside polygon against vertex EP doesn't work.");

        // TC03: Ray outside polygon against edge.
        ray = new Ray(new Point(0.25, 0.25, 1), new Vector(0.75, 0.75, -1));
        assertNull(polygon.findIntersections(ray), "Ray outside polygon against edge EP doesn't work.");

        // =============== Boundary Values Tests ==================
        // TC04: Ray intersects on vertex of polygon.
        ray = new Ray(new Point(0.25, 0.25, 1), new Vector(-0.25, 0.75, -1));
        assertNull(polygon.findIntersections(ray), "Ray intersects on vertex of polygon BVA doesn't work.");

        // TC05: Ray intersects on edge of polygon.
        ray = new Ray(new Point(0.25, 0.25, 1), new Vector(0.25, 0.25, -1));
        assertNull(polygon.findIntersections(ray), "Ray intersects on edge of polygon BVA doesn't work.");

        // TC06: Ray intersects on edge's continuation of polygon.
        ray = new Ray(new Point(0.25, 0.25, 1), new Vector(-1.25, -2.25, -1));
        assertNull(polygon.findIntersections(ray), "Ray intersects on edge's continuation of polygon BVA doesn't work.");
    }
}