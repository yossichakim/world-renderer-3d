package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * DirectionalLight class represents a directional light in a scene.
 */
public class DirectionalLight extends Light implements LightSource{

    private final Vector direction;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     * @param direction the light's direction.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return intensity;
    }

    @Override
    public Vector getL(Point point) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
