package geometries;

/**
 * Represents a radial geometry shape in 3D space.
 */
public abstract class RadialGeometry implements Geometry {

    /**
     * The radius of the radial geometry shape.
     */
    protected final double radius;

    /**
     * Constructs a radial geometry shape with a given radius.
     *
     * @param radius The radius of the radial geometry shape.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
