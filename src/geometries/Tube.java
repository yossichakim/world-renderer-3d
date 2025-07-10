package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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

    /**
     * getter function for axisRay
     *
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * function that returns radius value
     *
     * @return radius value
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point) {
        double t = this.axisRay.getDir().dotProduct(point.subtract(this.axisRay.getP0()));
        return point.subtract(axisRay.getPoint(t)).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector dir = ray.getDir();
        Vector v = axisRay.getDir();
        double dirV = dir.dotProduct(v);

        if (ray.getP0().equals(axisRay.getP0())) { // In case the ray starts on the p0.
            if (isZero(dirV))
                return List.of(new Intersectable.GeoPoint(this, ray.getPoint(radius)));

            if (dir.equals(v.scale(dir.dotProduct(v))))
                return null;


            return List.of(new Intersectable.GeoPoint(this, ray.getPoint(
                    Math.sqrt(radius * radius / dir.subtract(v.scale(dir.dotProduct(v))).lengthSquared()))));


        }
        Vector deltaP = ray.getP0().subtract(axisRay.getP0());
        double dpV = deltaP.dotProduct(v);

        double a = 1 - dirV * dirV;
        double b = 2 * (dir.dotProduct(deltaP) - dirV * dpV);
        double c = deltaP.lengthSquared() - dpV * dpV - radius * radius;

        if (isZero(a)) {
            if (isZero(b)) { // If a constant equation.
                return null;
            }
            return List.of(new Intersectable.GeoPoint(this,ray.getPoint(-c / b))); // if it's linear, there's a solution.
        }

        double discriminant = alignZero(b * b - 4 * a * c);

        if (discriminant < 0) // No real solutions.
            return null;

        double t1 = alignZero(-(b + Math.sqrt(discriminant)) / (2 * a)); // Positive solution.
        double t2 = alignZero(-(b - Math.sqrt(discriminant)) / (2 * a)); // Negative solution.

        if (discriminant <= 0) // No real solutions.
            return null;

        if (t1 > 0 && t2 > 0) {
            List<GeoPoint> points = new LinkedList<>();
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t1)));
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t2)));
            return points;
        }
        else if (t1 > 0) {
            List<GeoPoint> points = new LinkedList<>();
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t1)));
            return  points;
        }
        else if (t2 > 0) {
            List<GeoPoint> points = new LinkedList<>();
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t2)));
            return points;
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tube) &&
                this.axisRay.equals(((Tube) obj).axisRay) &&
                this.radius == ((Tube) obj).radius;
    }
}
