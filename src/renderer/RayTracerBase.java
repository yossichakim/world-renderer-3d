package renderer;

import primitives.*;
import scene.Scene;


/**
 * Abstract class for ray tracing
 */
public abstract class RayTracerBase {

    /**
     * The scene
     */
    protected final Scene scene;

    /**
     * Constructor
     * @param scene the scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces a ray
     * @param ray the ray
     * @return the color of the ray
     */
    public abstract Color traceRay(Ray ray);
}
