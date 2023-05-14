package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a tube in 3D space.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the tube.
     */
    protected final Ray axisRay;

    /**
     * Constructs a tube with a given radius and axis ray.
     *
     * @param radius  The radius of the tube.
     * @param axisRay The axis ray of the tube.
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        double t = this.axisRay.getDir().dotProduct(point.subtract(this.axisRay.getP0()));
        return point.subtract(axisRay.getPoint(t)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
