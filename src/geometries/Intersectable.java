package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;


/**
 * The abstract Intersectable.
 */
public abstract class Intersectable {

    /**
     * helper class to connect point to Geometry
     */
    public static class GeoPoint {

        /**
         * The geometry of geoPoint
         */
        public Geometry geometry;

        /**
         * The point of geoPoint
         */
        public Point point;

        /**
         * constructor of the helper class
         *
         * @param geometry to set
         * @param point    to set
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return o instanceof GeoPoint geoPoint && geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * Find points of intersection between the geometry and a ray
     *
     * @param ray the ray
     * @return the list of intersection points
     */
    public final List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * function that returns a list of all intersections of a ray
     *
     * @param ray the ray to check for intersections
     * @return a list of all intersections points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * helper function that returns a list of all intersections of a ray
     *
     * @param ray the ray to check for intersections
     * @return a list of all intersections points
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}