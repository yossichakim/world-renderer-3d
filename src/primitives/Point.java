package primitives;

/**
 * Represents a point in 3D space.
 */
public class Point {
    final Double3 xyz;

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
     * Determines whether this point is equal to the specified object.
     *
     * @param obj the object to compare this point with
     * @return true if the object is a point and has the same coordinates as this point, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other) && this.xyz.equals(other.xyz);
    }

    /**
     * Returns a string representation of this point in the format "(x, y, z)".
     *
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * Computes the vector from this point to the specified point.
     *
     * @param other the point to compute the vector to
     * @return the vector from this point to the specified point
     */
    public Vector subtract(Point other) {
        Double3 diff = this.xyz.subtract(other.xyz);
        return new Vector(diff.d1, diff.d2, diff.d3);
    }

    /**
     * Computes the point obtained by adding the specified vector to this point.
     *
     * @param vec the vector to add to this point
     * @return the point obtained by adding the specified vector to this point
     */
    public Point add(Vector vec) {
        Double3 newCoords = xyz.add(vec.xyz);
        return new Point(newCoords);
    }

    /**
     * Computes the square of the distance from this point to the specified point.
     *
     * @param other the point to compute the distance to
     * @return the square of the distance from this point to the specified point
     */
    public double distanceSquared(Point other) {
        return (this.xyz.d1 - other.xyz.d1) * (this.xyz.d1 - other.xyz.d1) +
                (this.xyz.d2 - other.xyz.d2) * (this.xyz.d2 - other.xyz.d2) +
                (this.xyz.d3 - other.xyz.d3) * (this.xyz.d3 - other.xyz.d3);
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

}
