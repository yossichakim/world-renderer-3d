package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;
import java.util.stream.IntStream;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The type Camera - represents a camera in the scene.
 */
public class Camera {

    private final Point position;
    private final Vector vTo;
    private final Vector vUp;
    private final Vector vRight;
    private int threads = 0;
    private double width;
    private double height;
    private double distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private double printInterval;

    /**
     * Constructor for a new Camera.
     *
     * @param position the position
     * @param vTo      the v to - the vector from the camera to the view plane.
     * @param vUp      the v up.
     */
    public Camera(Point position, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) throw new IllegalArgumentException("vTo and vUp must be orthogonal");

        this.position = position;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    public Camera setMultiThreading(int threads) {
        if (threads < -2)
            throw new MissingResourceException("threads must be >= 1 or -2 for default or -1 for streaming", "Camera", "threads");
        if (threads >= -1)
            this.threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors();
            this.threads = cores <= 2 ? 1 : cores;
        }
        return this;
    }

    public Camera setDebugPrint(double interval) {
        printInterval = interval;
        return this;
    }

    /**
     * Gets position of the camera.
     *
     * @return the position of the camera.
     */
    @SuppressWarnings("unused")
    public Point getPosition() {
        return position;
    }

    /**
     * Gets v to - the vector from the camera to the view plane.
     *
     * @return the v to - the vector from the camera to the view plane.
     */
    @SuppressWarnings("unused")
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Gets v up.
     *
     * @return the vUp Vector.
     */
    @SuppressWarnings("unused")
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Gets v right.
     *
     * @return the vRight Vector.
     */
    @SuppressWarnings("unused")
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Gets width of the view plane.
     *
     * @return the width of the view plane.
     */
    @SuppressWarnings("unused")
    public double getWidth() {
        return width;
    }

    /**
     * Gets height of the view plane.
     *
     * @return the height of the view plane.
     */
    @SuppressWarnings("unused")
    public double getHeight() {
        return height;
    }

    /**
     * Gets distance from the camera to the view plane.
     *
     * @return the distance from the camera to the view plane.
     */
    @SuppressWarnings("unused")
    public double getDistance() {
        return distance;
    }

    /**
     * Sets vp size - the size of the view plane.
     *
     * @param width  the width of the view plane.
     * @param height the height of the view plane.
     * @return the vp size - the size of the view plane.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets vp distance - the distance from the camera to the view plane.
     *
     * @param distance the distance from the camera to the view plane.
     * @return the vp distance - the distance from the camera to the view plane.
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }


    /**
     * Sets image writer.
     *
     * @param imageWriter the image writer
     * @return the image writer
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets ray tracer.
     *
     * @param rayTracer the ray tracer
     * @return the ray tracer
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Constructs a ray through a pixel in the view plane.
     *
     * @param nX  - the number of pixels in the width of the view plane.
     * @param nY  - the number of pixels in the height of the view plane.
     * @param row - the index of the pixel in the width of the view plane.
     * @param col - the index of the pixel in the height of the view plane.
     * @return the ray through the pixel.
     */
    public Ray constructRay(int nX, int nY, int col, int row) {
        // Calculate the center point of the view plane
        Point pCenter = position.add(vTo.scale(distance));

        // Calculate the size of each pixel
        double rX = width / nX;
        double rY = height / nY;

        // Calculate the coordinates of the current pixel in the view plane
        double xJ = (col - (nX - 1) / 2d) * rX;
        double yI = -(row - (nY - 1) / 2d) * rY;

        // Calculate the position of the current pixel in 3D space
        Point pIJ = pCenter;
        if (!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));

        // Construct and return the ray from the camera position to the pixel position
        return new Ray(position, pIJ.subtract(position));
    }

    /**
     * Render the image.
     *
     * @return camera object itself
     */
    public Camera renderImage() {
        // Check if imageWriter is set
        if (imageWriter == null)
            throw new MissingResourceException("", "Camera", "ImageWriter is not set");

        // Check if rayTracer is set
        if (rayTracer == null)
            throw new MissingResourceException("", "Camera", "RayTracer is not set");

        // Check if width, height, and distance are set
        if (alignZero(width) <= 0 || alignZero(height) <= 0 || alignZero(distance) <= 0)
            throw new MissingResourceException("", "Camera", "View plane dimensions are not set");

        // Check if position, vTo, and vUp are set
        if (position == null || vTo == null || vUp == null)
            throw new MissingResourceException("", "Camera", "Camera position or orientation vectors are not set");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        Pixel.initialize(nY, nX, printInterval);
        if (this.threads >= 0)
            for (int i = 0; i < nX; i++)
                for (int j = 0; j < nY; j++)
                    this.castRay(nX, nY, j, i);
        else
            IntStream.range(0, nY).parallel() //
                    .forEach(i -> IntStream.range(0, nX) //
                            .forEach(j -> this.castRay(nX, nY, j, i)));

        return this;
    }

    /**
     * casts ray through pixel
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     */
    private void castRay(int nX, int nY, int j, int i) {
        this.imageWriter.writePixel(j, i, rayTracer.traceRay(constructRay(nX, nY, j, i)));
        Pixel.pixelDone();
    }

    /**
     * prints grid on top of image
     *
     * @param interval of grid
     * @param color    of grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("", "Camera", "ImageWriter is not set");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if ((i % interval == 0) || (j % interval == 0))
                    imageWriter.writePixel(i, j, color);
            }
        }
    }

    /**
     * Writes the image to the imageWriter.
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("", "Camera", "ImageWriter is not set");

        imageWriter.writeToImage();
    }

}
