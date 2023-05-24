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

        ImageWriter imageWriter = new ImageWriter("test", 800, 500);

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(i, j, new Color(255, 0, 0));
                else
                    imageWriter.writePixel(i, j, new Color(0, 255, 0));
            }
        }

        imageWriter.writeToImage();
    }
}
