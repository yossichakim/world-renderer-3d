package renderer;

import geometries.Intersectable;
import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Testing basic create image of sphere in box
 */
public class SphereInBox {
    private final Intersectable sphere = new Sphere(60d, new Point(0, 0, -200))
            .setEmission(new Color(BLUE))
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
    private final Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);

    private final Scene scene = new Scene("Test scene");
    private final Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVPSize(200, 200).setVPDistance(1000)
            .setRayTracer(new RayTracerBasic(scene));

    /**
     * function for the tests in this module
     */
    @Test
    public void sphereInBox() {

        scene.setAmbientLight(new AmbientLight(new Color(YELLOW), new Double3(0.15)));

        // Define the polygons
        Polygon bottom = new Polygon(
                new Point(-100, -87, -500),
                new Point(100, -87, -500),
                new Point(100, -90, -210),
                new Point(-100, -90, -210)
        );

        Polygon top = new Polygon(
                new Point(-100, 87, -500),
                new Point(100, 87, -500),
                new Point(100, 90, -210),
                new Point(-100, 90, -210)
        );

        Polygon left = new Polygon(
                new Point(-100, -87, -500),
                new Point(-100, 87, -500),
                new Point(-100, 90, -210),
                new Point(-100, -90, -210)
        );

        Polygon right = new Polygon(
                new Point(100, -87, -500),
                new Point(100, 87, -500),
                new Point(100, 90, -210),
                new Point(100, -90, -210)
        );

        Polygon back = new Polygon(
                new Point(-100, -87, -500),
                new Point(100, -87, -500),
                new Point(100, 87, -500),
                new Point(-100, 87, -500)
        );

        // Set the properties of the polygons
        bottom.setMaterial(new Material().setKt(0).setKr(0.5)).setEmission(new Color(GREEN));
        top.setMaterial(new Material().setKt(0).setKr(0)).setEmission(new Color(BLUE));
        left.setMaterial(new Material().setKt(0.5).setKr(0.2)).setEmission(new Color(YELLOW));
        right.setMaterial(new Material().setKt(0.5).setKr(0.2)).setEmission(new Color(PINK));
        back.setMaterial(new Material().setKt(0.5).setKr(0.2)).setEmission(new Color(BLACK));

        // Create the sphere
        Sphere sphere = new Sphere(80, new Point(0, 0, -300));
        sphere.setEmission(new Color(RED))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));

        // Add the geometries to the scene
        scene.geometries.add(bottom, top, left, right, back, sphere);

        // Define the lights
        scene.lights.add(
                new SpotLight(new Color(500, 500, 500), new Point(0, 200, -100), new Vector(0, -1, -2))
                        .setKl(1E-4).setKq(1E-5)
        );

        // Set up the camera
        camera.setImageWriter(new ImageWriter("sphereInBox", 600, 600))
                .renderImage()
                .writeToImage();
    }
}
