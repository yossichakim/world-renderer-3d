package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests between creating rays from a camera and
 * Calculation of sections of a beam with geometric bodies.
 */
public class IntegrationTests {

    /**
     * Integration tests of a camera and a sphere.
     */
    @Test
    public void testSphereAndCamera() {
        List<Intersectable> spheres = List.of(
                //TC01: the sphere is in front of the view plane (2 intersection points).
                new Sphere(1, new Point(0, 0, -3)),
                //TC02: the view plane is inside the sphere, all rays should intersect twice (18 intersection points).
                new Sphere(2.5, new Point(0, 0, -2.5)),
                //TC03: the view plane cross the sphere (10 intersection points).
                new Sphere(2, new Point(0, 0, -2)),
                //TC04: the camera is inside the sphere,all rays should intersect only once(9 intersection points).
                new Sphere(4, new Point(0, 0, -1)),
                //TC05: the sphere is behind the camera , no ray should intersect(0 intersection point).
                new Sphere(0.5, new Point(0, 0, 1)));

        var intersectionPointsExcepted = List.of(2, 18, 10, 9, 0);

        testIntersectionWithCamera(spheres, intersectionPointsExcepted);
    }

    /**
     * Integration tests of a camera and a plane.
     */
    @Test
    public void testPlaneAndCamera() {
        List<Intersectable> planes = List.of(
                //TC01: the plane is parallel with the view plane, all rays should intersect(9 intersection points).
                new Plane(new Point(0, 0, -2), new Vector(0, 0, 1)),
                //TC02: the plane is in front of the view plane and cross, all rays should intersect(9 intersection points).
                new Plane(new Point(0, 0, -1.5), new Vector(0, -0.5, 1)),
                //TC03: the plane is above the view plane's third row (6 intersection points).
                new Plane(new Point(0, 0, -3), new Vector(0, -1, 1)));
        var intersectionPointsExcepted = List.of(9, 9, 6);

        testIntersectionWithCamera(planes, intersectionPointsExcepted);
    }

    /**
     * Integration tests of a camera and a triangle.
     */
    @Test
    public void testTriangleAndCamera() {
        List<Intersectable> triangles = List.of(
                //TC01:only the center ray should intersect(1 intersection point).
                new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2)),
                //TC02: only the center ray and the top-middle ray should intersect(2 intersection points).
                new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2)));

        var intersectionPointsExcepted = List.of(1, 2);

        testIntersectionWithCamera(triangles, intersectionPointsExcepted);
    }

    /**
     * Integration tests of a camera and a plane.
     *
     * @param shapes                     the shapes (sphere, plane, triangle)
     * @param intersectionPointsExpected the expected number of intersection points
     */
    private void testIntersectionWithCamera(List<Intersectable> shapes, List<Integer> intersectionPointsExpected) {
        // Create the camera with the desired parameters
        Point cameraPosition = new Point(0, 0, 0.5);  // Set the camera position
        Vector vTo = new Vector(0, 0, -1);  // Set the direction vector towards the view plane
        Vector vUp = new Vector(0, 1, 0);  // Set the upward direction vector

        Camera camera = new Camera(cameraPosition, vTo, vUp)
                .setVPSize(3, 3)  // Set the size of the view plane
                .setVPDistance(1);  // Set the distance from the camera to the view plane

        // Calculate the expected sum of intersection points
        int expectedSum = intersectionPointsExpected.stream().mapToInt(Integer::intValue).sum();

        // Initialize the actual sum of intersection points
        int actualSum = 0;

        // Iterate over each shape
        for (Intersectable shape : shapes) {
            // Iterate over each pixel in the view plane
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    // Construct a ray for the current pixel
                    Ray ray = camera.constructRay(3, 3, col, row);

                    // Calculate the number of intersection points for the ray and the current shape
                    if (shape.findIntersections(ray) == null)
                        continue;

                    int intersectionPoints = shape.findIntersections(ray).size();

                    // Add the number of intersection points to the actual sum
                    actualSum += intersectionPoints;
                }
            }
        }

        // Check if the actual sum matches the expected sum
        assertEquals(expectedSum, actualSum,
                "testIntersectionWithCamera() " + shapes.get(0).getClass().getSimpleName() +
                        ", Wrong number of intersection points");
    }


}