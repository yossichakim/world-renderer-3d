package primitives;

public class Point {
     final Double3 xyz;

    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    Point(Double3 xyz) { // package-friendly access modifier
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other) && this.xyz.equals(other.xyz);

    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    public Vector subtract(Point other) {
        Double3 diff = this.xyz.subtract(other.xyz);
        return new Vector(diff.d1, diff.d2, diff.d3);
    }

    public Point add(Vector vec) {
        Double3 newCoords = xyz.add(vec.xyz);
        return new Point(newCoords);
    }

    public double distanceSquared(Point other) {
        return (this.xyz.d1 - other.xyz.d1) * (this.xyz.d1 - other.xyz.d1) +
                (this.xyz.d2 - other.xyz.d2) * (this.xyz.d2 - other.xyz.d2) +
                (this.xyz.d3 - other.xyz.d3) * (this.xyz.d3 - other.xyz.d3);
    }

    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

}
