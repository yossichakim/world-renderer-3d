package primitives;

/**
 * Represents a point in 3D space.
 */
public class Point {
    protected final Double3 xyz;
    public static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * Constructs a new point with the specified x, y, and z coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param z the z-coordinate of the point
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    Point(Double3 xyz) { // package-friendly access modifier
        this.xyz = xyz;
    }

    /**
     * Computes the vector from this point to the specified point.
     *
     * @param other the point to compute the vector to
     * @return the vector from this point to the specified point
     */
    public Vector subtract(Point other) {
        return new Vector(this.xyz.subtract(other.xyz));
    }

    /**
     * Computes the point obtained by adding the specified vector to this point.
     *
     * @param vec the vector to add to this point
     * @return the point obtained by adding the specified vector to this point
     */
    public Point add(Vector vec) {
        return new Point(xyz.add(vec.xyz));
    }

    /**
     * Computes the square of the distance from this point to the specified point.
     *
     * @param other the point to compute the distance to
     * @return the square of the distance from this point to the specified point
     */
    public double distanceSquared(Point other) {
        double dx = this.xyz.d1 - other.xyz.d1;
        double dy = this.xyz.d2 - other.xyz.d2;
        double dz = this.xyz.d3 - other.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Computes the distance from this point to the specified point.
     *
     * @param other the point to compute the distance to
     * @return the distance from this point to the specified point
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * Getter of X coordinate value
     *
     * @return x coordinate value
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Getter of Y coordinate value
     *
     * @return y coordinate value
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Getter of Z coordinate value
     *
     * @return z coordinate value
     */
    public double getZ() {
        return xyz.d3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other) && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return String.valueOf(xyz);
    }

}
