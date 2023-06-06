package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * SpotLight class represents a spot light in a scene.
 */
public class SpotLight extends PointLight{
    private final Vector direction;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     * @param position  the light's position.
     * @param direction the light's direction.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        double projection = alignZero(direction.dotProduct(getL(point)));
        return alignZero(projection) <= 0 ? Color.BLACK :
                super.getIntensity(point).scale(projection);
    }
}
