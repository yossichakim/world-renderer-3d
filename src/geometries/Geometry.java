package geometries;
import primitives.*;


/**
 * The Geometry interface represents a geometric shape or object in 3D space.
 * Implementing classes must provide a method to retrieve the normal vector at a given point on the surface.
 */
public interface Geometry extends Intersectable {

    /**
     * Returns the normal vector at the given point on the surface of the geometry.
     *
     * @param point the point on the surface of the geometry
     * @return the normal vector at the given point
     */
    Vector getNormal(Point point);
}
