package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The class Geometries represents a collection of geometries.
 * It is a derived class of the Intersectable abstract class.
 * It is composed of a list of geometries.
 * It is used to find intersections between a ray and the geometries in the collection.
 * It is used to find the closest intersection point to a given point.
 */
public class Geometries extends Intersectable {

    /**
     * The geometries.
     */
    private final List<Intersectable> geometriesList = new LinkedList<>();

    /**
     * Constructs a geometries object from a list of geometries.
     */
    public Geometries() {
    }

    /**
     * Constructs a geometries object from a list of geometries.
     *
     * @param geometries The geometries.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds geometries to the geometries list.
     *
     * @param geometries The geometries to add.
     */
    public void add(Intersectable... geometries) {
        this.geometriesList.addAll(List.of(geometries));
    }

    /**
     * Find Geometry intersections list.
     *
     * @param ray the ray
     * @return the list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;

        for (var geometry : geometriesList) {
            var geometryIntersections = geometry.findGeoIntersections(ray);
            if (geometryIntersections != null) {
                if (intersections == null) intersections = new LinkedList<>();
                intersections.addAll(geometryIntersections);
            }
        }
        return intersections;
    }
}
