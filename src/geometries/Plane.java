package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements  Geometry{

    private Point q0;
    private Vector normal;

    public Plane(Point p1, Point p2, Point p3){
        this.normal = null;
        this.q0 = p1;
    }

    public Plane(Point p, Vector normal){
        this.normal=normal.normalize();
        this.q0=p;
    }
    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    public Vector getNormal() {
        return this.normal;
    }
}
