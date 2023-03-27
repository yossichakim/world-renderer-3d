package geometries;

import primitives.*;

public class Tube extends RadialGeometry{

    protected Ray axisRay;

    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
