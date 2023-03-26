package primitives;

public class Vector extends Point {

    public Vector(double x, double y, double z) {


        super(x, y, z);

        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
    }

    public Vector(Double3 xyz) {
        super(xyz);

        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
    }

    public Vector add(Vector other) {
        Double3 newCoords = xyz.add(other.xyz);
        return new Vector(newCoords);
    }

    public Vector scale(double scalar) {
        Double3 scaledCoords = xyz.scale(scalar);
        return new Vector(scaledCoords);
    }

    public double dotProduct(Vector other) {
        return this.xyz.d1 * other.xyz.d1 + this.xyz.d2 * other.xyz.d2 + this.xyz.d3 * other.xyz.d3;
    }

    public Vector crossProduct(Vector other) {
        double x = this.xyz.d2 * other.xyz.d3 - this.xyz.d3 * other.xyz.d2;
        double y = this.xyz.d3 * other.xyz.d1 - this.xyz.d1 * other.xyz.d3;
        double z = this.xyz.d1 * other.xyz.d2 - this.xyz.d2 * other.xyz.d1;
        return new Vector(x, y, z);
    }


    public double lengthSquared() {
        return this.dotProduct(this);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double len = length();
        return scale(1 / len);
    }


    @Override
    public String toString() {
        return "Vector: " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
