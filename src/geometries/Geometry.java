package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;


/**
 * The Geometry abstract represents a geometric shape or object in 3D space.
 * Implementing classes must provide a method to retrieve the normal vector at a given point on the surface.
 */
public abstract class Geometry extends Intersectable {

    /**
     * The color of the geometry.
     */
    protected Color emission = Color.BLACK;

    /**
     * The material of the geometry.
     */
    private Material material = new Material();

    /**
     * Function will set the color of the geometry
     *
     * @param emission the color of the emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Function will return the color of the geometry
     *
     * @return the color of the geometry
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Function will return the material of the geometry
     *
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Function will set the material of the geometry
     */
    public Geometry setMaterial(Material _material) {
        material = _material;
        return this;
    }

    /**
     * Returns the normal vector at the given point on the surface of the geometry.
     *
     * @param point the point on the surface of the geometry
     * @return the normal vector at the given point
     */
    public abstract Vector getNormal(Point point);
}
