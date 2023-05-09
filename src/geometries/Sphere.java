package geometries;
import primitives.*;
import java.util.ArrayList;
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
    public List<Point> findIntersections(Ray ray) {
        // Ray starts at the center of the sphere
        if(center.equals(ray.getP0())) {
            List<Point> lst = new ArrayList<>();
            lst.add(ray.getPoint(radius));
            return lst;
        }

        Vector u = center.subtract(ray.getP0());
        // Not the other way around to not break LoD
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        // No intersection points
        if (d >= radius) return null;

        // Ray is tangent to the sphere
        List<Point> lst = new ArrayList<>();
        double th = Math.sqrt(radius * radius - d * d);

        double t1 = tm + th;
        double t2 = tm - th;
        if (alignZero(t1) > 0 )
            lst.add(( ray.getPoint(t1)));

        if (alignZero(t2) > 0 )
            lst.add(( ray.getPoint(t2)));

        if (lst.size() == 0) return null;

        return lst;
    }
}
