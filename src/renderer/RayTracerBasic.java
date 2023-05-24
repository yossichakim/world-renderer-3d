package renderer;


import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;


/**
 * Abstract class for ray tracing (basic implementation)
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Constructor
     *
     * @param scene the scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {

        List<Point> intersections = scene.geometries.findIntersections(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestPoint(intersections));
    }

    /**
     * Calculate the color intensity in a point
     *
     * @param closestPoint the closest point to the camera
     * @return the color intensity in a point
     */
    private Color calcColor(Point closestPoint) {
        return scene.ambientLight.getIntensity();
    }
}
