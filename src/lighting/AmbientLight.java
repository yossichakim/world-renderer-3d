package lighting;

import primitives.*;

/*
 * AmbientLight class represents an ambient light in a scene.
 */
public class AmbientLight extends Light {


    // Constant for representing no ambient light
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an ambient light with the given intensity and attenuation factor.
     *
     * @param iA The intensity of the ambient light
     * @param kA The attenuation factor for the ambient light
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Constructs an ambient light with a single attenuation factor.
     * The intensity of the ambient light is multiplied by the attenuation factor.
     *
     * @param kA The attenuation factor for the ambient light
     */
    public AmbientLight(double kA) {
        super(Color.BLACK.scale(kA));
    }
}
