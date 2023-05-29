package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Testing ImageWriter Class
 */
public class ImageWriterTest {

    /**
     * Test method for
     * {@link renderer.ImageWriter#writeToImage()}.
     */
    @Test
    public void testWriteToImage() {
        final int width = 800;
        final int height = 800;
        final int step = 50;
        final Color color1 = new Color(java.awt.Color.RED);
        final Color color2 = new Color(java.awt.Color.GREEN);

        ImageWriter imageWriter = new ImageWriter("test", width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                imageWriter.writePixel(i, j, i % step == 0 || j % step == 0 ? color1 : color2);
        imageWriter.writeToImage();
    }
}
