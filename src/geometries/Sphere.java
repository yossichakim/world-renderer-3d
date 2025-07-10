package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a sphere in 3D space.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a sphere with a given radius and center point.
     *
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Gets the normal vector to the sphere at a given point.
     *
     * @param point The point at which to get the normal vector.
     * @return The normal vector to the sphere at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(this.center).normalize();
    }

    /**
     * @param ray the ray to find intersections with
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Ray starts at the center of the sphere
        if (center.equals(ray.getP0()))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));

        Vector u = center.subtract(ray.getP0());
        // Not the other way around to not break LoD
        double tm = u.dotProduct(ray.getDir());
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = radiusSquared - dSquared;
        // No intersection points or ray is tangent ot sphere
        if (alignZero(thSquared) <= 0) return null;

        double th = Math.sqrt(thSquared); // it's always positive
        double t1 = tm + th; // it's always greater than t2
        if (alignZero(t1) <= 0) return null;

        double t2 = tm - th;
        return alignZero(t2) > 0 ?
                List.of(new GeoPoint(this ,ray.getPoint(t2)), new GeoPoint(this,ray.getPoint(t1))) :
                List.of(new GeoPoint(this,ray.getPoint(t1)));
    }
}
