package primitives;

/**
 * A class representing a mathematical vector in three-dimensional space.
 * A vector has a magnitude (length) and direction, but no specific starting point (unlike a point).
 */
public class Vector extends Point {

    /**
     * Constructs a vector with the given x, y, and z coordinates.
     *
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     * @throws IllegalArgumentException If the given coordinates represent the zero vector.
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
    }

    /**
     * Constructs a vector with the given coordinates.
     *
     * @param xyz The coordinates of the vector as a Double3 object.
     * @throws IllegalArgumentException If the given coordinates represent the zero vector.
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
    }

    /**
     * Adds the given vector to this vector and returns the result as a new vector.
     *
     * @param other The vector to add to this vector.
     * @return The sum of this vector and the given vector.
     */
    public Vector add(Vector other) {
        Double3 newCoords = xyz.add(other.xyz);
        return new Vector(newCoords);
    }

    /**
     * Scales this vector by the given scalar and returns the result as a new vector.
     *
     * @param scalar The scalar to multiply this vector by.
     * @return The product of this vector and the given scalar.
     */
    public Vector scale(double scalar) {
        Double3 scaledCoords = xyz.scale(scalar);
        return new Vector(scaledCoords);
    }

    /**
     * Computes the dot product of this vector and the given vector.
     *
     * @param other The vector to compute the dot product with.
     * @return The dot product of this vector and the given vector.
     */
    public double dotProduct(Vector other) {
        return this.xyz.d1 * other.xyz.d1 + this.xyz.d2 * other.xyz.d2 + this.xyz.d3 * other.xyz.d3;
    }

    /**
     * Computes the cross product of this vector and the given vector.
     *
     * @param other The vector to compute the cross product with.
     * @return The cross product of this vector and the given vector.
     */
    public Vector crossProduct(Vector other) {
        double x = this.xyz.d2 * other.xyz.d3 - this.xyz.d3 * other.xyz.d2;
        double y = this.xyz.d3 * other.xyz.d1 - this.xyz.d1 * other.xyz.d3;
        double z = this.xyz.d1 * other.xyz.d2 - this.xyz.d2 * other.xyz.d1;
        return new Vector(x, y, z);
    }

    /**
     * Computes the squared length of this vector.
     *
     * @return The squared length of this vector.
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Returns the length of this vector.
     *
     * @return The length of this vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Returns a new vector that is the normalized version of this vector.
     *
     * @return A new vector that is the normalized version of this vector.
     */
    public Vector normalize() {
        double len = length();
        return scale(1 / len);
    }

    /**
     * Returns a string representation of this vector.
     *
     * @return A string representation of this vector.
     */
    @Override
    public String toString() {
        return "Vector: " + super.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The object to compare to this one.
     * @return True if the other object is "equal to" this one, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
