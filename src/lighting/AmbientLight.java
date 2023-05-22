package lighting;

import primitives.*;

/*
 * AmbientLight class represents ambient lighting in a scene.
 */
public class AmbientLight {

    // The intensity of the ambient light
    private final Color intensity;

    // Constant for representing no ambient light
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an ambient light with the given intensity and attenuation factor.
     *
     * @param iA The intensity of the ambient light
     * @param kA The attenuation factor for the ambient light
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * Constructs an ambient light with a single attenuation factor.
     * The intensity of the ambient light is multiplied by the attenuation factor.
     *
     * @param kA The attenuation factor for the ambient light
     */
    public AmbientLight(double kA) {
        this.intensity = Color.BLACK.scale(kA);
    }

    /**
     * Gets the intensity of the ambient light.
     *
     * @return The intensity of the ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
