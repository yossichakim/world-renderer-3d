package geometries;
import primitives.*;
import java.util.List;


/**
 * The interface Intersectable.
 */
public interface Intersectable {

    /**
     * Find intersections list.
     *
     * @param ray the ray
     * @return the list
     */
    List<Point> findIntersections(Ray ray);
}