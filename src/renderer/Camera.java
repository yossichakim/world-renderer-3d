package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * The type Camera - represents a camera in the scene.
 */
public class Camera {

    private final Point position;
    private final Vector vTo;
    private final Vector vUp;
    private final Vector vRight;

    private double width;
    private double height;
    private double distance;

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

    /**
     * Gets position of the camera.
     *
     * @return the position of the camera.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Gets v to - the vector from the camera to the view plane.
     *
     * @return the v to - the vector from the camera to the view plane.
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Gets v up.
     *
     * @return the vUp Vector.
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Gets v right.
     *
     * @return the vRight Vector.
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Gets width of the view plane.
     *
     * @return the width of the view plane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height of the view plane.
     *
     * @return the height of the view plane.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets distance from the camera to the view plane.
     *
     * @return the distance from the camera to the view plane.
     */
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

}
