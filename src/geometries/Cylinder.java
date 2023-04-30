package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder shape in 3D space.
 * A cylinder is defined by a tube with a given radius and an axis ray,
 * and an additional height parameter that determines the cylinder's height.
 */
public class Cylinder extends Tube {

    /**
     * Cylinder height
     */
     private final double height;

    /**
     * Constructs a Cylinder with a given radius, axis Ray and height.
     *
     * @param radius  The cylinder radius
     * @param axisRay The cylinder axis Ray
     * @param height  The cylinder height
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Returns the normal to the cylinder surface at a given point.
     *
     * @param point The point to calculate the normal at
     * @return The normal vector to the cylinder surface at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        double t = this.axisRay.getDir().dotProduct(point.subtract(this.axisRay.getP0()));
        Point po = this.axisRay.getP0().add(this.axisRay.getDir().scale(t));

        if (t == 0 || t == this.height) {
            return this.axisRay.getDir();
        }

        return point.subtract(po).normalize();
    }
}
