package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;


/**
 * Abstract class for ray tracing (basic implementation)
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * The maximum recursion level in the color calculation.
     * Higher values allow for a higher level of recursion
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * The threshold for recursion.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * The initial color coefficient for recursion.
     */
    private static final Double3 INIT_CALC_COLOR_K = Double3.ONE;


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
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }


    /**
     * Calculate the color intensity in a point
     *
     * @param gp the point;
     * @return the color intensity in a point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INIT_CALC_COLOR_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * function calculates color of point
     *
     * @param geoPoint point to color
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double vn = alignZero(v.dotProduct(n));
        if (vn == 0) return Color.BLACK;

        Color color = calcLocalEffects(geoPoint, v, n, vn, k);
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, v, n, vn, level, k));
    }

    /**
     * function calculates local effects of color on point
     *
     * @param gp geometry point to color
     * @param v  incoming ray direction
     * @param n  normal to the geometry at the point
     * @param vn dot product of v and n
     * @return color
     */
    private Color calcLocalEffects(GeoPoint gp, Vector v, Vector n, double vn, Double3 k) {
        Color color = gp.geometry.getEmission();
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector lightVector = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(lightVector));
            if (alignZero(nl * vn) > 0) {
                Double3 ktr = transparency(gp, lightSource, lightVector, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl) //
                            .add(calcSpecular(material, n, lightVector, nl, v))));
                }
            }
        }
        return color;
    }

    /**
     * Calculates reflected ray and refraction ray
     *
     * @param geoPoint geometry point
     * @param v        incoming ray direction
     * @param n        normal to the geometry at the point
     * @param vn       dot product of v and n
     * @param k        k value
     * @return color
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Vector v, Vector n, double vn, int level, Double3 k) {
        Material material = geoPoint.geometry.getMaterial();
        Ray reflectedRay = constructReflectionRay(geoPoint, v, n, vn);
        Ray refractedRay = constructRefractionRay(geoPoint, v, n);
        return calcGlobalEffect(level, material.kR, k, reflectedRay)
                .add(calcGlobalEffect(level, material.kT, k, refractedRay));
    }

    /**
     * function calculates global effects of color on point
     *
     * @param level level of recursion
     * @param kx    kx value of material
     * @param k     k value until now
     * @param ray   ray that intersects
     * @return color
     */
    private Color calcGlobalEffect(int level, Double3 kx, Double3 k, Ray ray) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint reflectedPoint = findClosestIntersection(ray);
        return (reflectedPoint == null ? scene.background : calcColor(reflectedPoint, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * function calculates specular color
     *
     * @param material    material of geometry
     * @param normal      normal of geometry
     * @param lightVector light vector
     * @param nl          dot product of normal and light vector
     * @param vector      direction of ray
     * @return specular color
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector vector) {
        Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
        double minusVR = alignZero(-vector.dotProduct(reflectedVector));
        return minusVR <= 0 ? Double3.ZERO : material.kS.scale(Math.pow(minusVR, material.nShininess));

    }

    /**
     * function calculates diffusive color
     *
     * @param material material of geometry
     * @param nl       dot product of normal and light vector
     * @return diffusive color
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(nl < 0 ? -nl : nl);
    }

    /**
     * function checks if point is shaded
     *
     * @param l           light vector
     * @param n           normal of geometry
     * @param gp          geometry point
     * @param lightSource light source
     * @return true if point is shaded
     */
    @SuppressWarnings("unused")
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {
        Ray shadowRay = new Ray(gp.point, l.scale(-1), n);

        var intersections = scene.geometries.findGeoIntersections(shadowRay);
        if (intersections == null) return true;

        double lightDistance = lightSource.getDistance(gp.point);
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0)
                return false;
        }
        return true;
    }

    /**
     * function will return double that represents transparency
     *
     * @param geoPoint    geometry point to check
     * @param lightSource light source
     * @param l           light vector
     * @param n           normal vector
     * @return transparency value
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        Double3 ktr = Double3.ONE;
        if (intersections == null) return ktr;

        double distance = lightSource.getDistance(geoPoint.point);
        for (GeoPoint intersection : intersections) {
            if (distance > intersection.point.distance(geoPoint.point)) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * function will construct a reflection ray
     *
     * @param geoPoint geometry point to check
     * @param vector   direction of ray to point
     * @param normal   normal vector
     * @param vn       dot product of v and n
     * @return reflection ray
     */
    private Ray constructReflectionRay(GeoPoint geoPoint,  Vector vector,Vector normal, double vn) {
        Vector reflectedVector = vector.subtract(normal.scale(2 * vn));
        return new Ray(geoPoint.point, reflectedVector, normal);
    }

    /**
     * function will construct a refraction ray
     *
     * @param geoPoint geometry point to check
     * @param normal   normal vector
     * @param vector   direction of ray to point
     * @return refraction ray
     */
    private Ray constructRefractionRay(GeoPoint geoPoint,Vector vector,  Vector normal) {
        return new Ray(geoPoint.point, vector, normal);
    }

    /**
     * Find the closest intersection point with a ray.
     *
     * @param ray The ray to checks intersections with.
     * @return The closest intersection point with the ray.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }
}
