package lighting;

import primitives.Color;

/**
 * Abstract class for all lights.
 */
abstract class Light {
    private final Color intensity;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for the light's intensity.
     *
     * @return the light's intensity.
     */
    public Color getIntensity() {
        return intensity;
    }

}
