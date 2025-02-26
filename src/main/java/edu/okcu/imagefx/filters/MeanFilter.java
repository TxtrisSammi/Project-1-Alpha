package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



/*
    Mean Filter
    Functions the same as the other filters but uses small 'kernels' to describe the
    area where pixels are averaged out to create the simulated blur affect.
    Created by: Michael

    Approximate Example (3x3 Kernel) ~
    Color | Color | Color |

    Before              After
    9  | 12 | 15        11 | 11 | 11
    10 | 13 | 15  --->  11 | 11 | 11
    10 | 14 | 16        11 | 11 | 11

    *Keep in mind this is done to each individual pixel so the changes
    aren't super drastic when in this small of a kernel but scales up
    with kernel size.

    *The filter may be very slow depending on image size as well

    Sources:
    https://medium.com/@sarves021999/noise-filtering-mean-median-mid-point-filter-72ab3be76da2
    https://en.wikipedia.org/wiki/Geometric_mean_filter
 */

public class MeanFilter implements IFilter {

    public Image apply(File file) throws IOException {

        // There needs to be two separate images since the kernels will affect multiple pixels and
        // each pixel needs the original pixel areas to average out
        BufferedImage img = ImageIO.read(file);
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int kernelSize = 6;
        int kernelRadius = kernelSize / 2;

        // Iterates over each pixel for the final image
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int red = 0, green = 0, blue = 0;
                int count = 0;

                // Iterate over the kernel surrounding the selected pixel
                for (int ky = -kernelRadius; ky <= kernelRadius; ky++) {
                    for (int kx = -kernelRadius; kx <= kernelRadius; kx++) {
                        int pixelX = x + kx;
                        int pixelY = y + ky;

                        // Check if the pixel is within the image bounds
                        // (As kernels may search outside the image for image border pixels)
                        if (pixelX >= 0 && pixelX < img.getWidth() && pixelY >= 0 && pixelY < img.getHeight()) {
                            // Gets the pixel color values
                            int rgb = img.getRGB(pixelX, pixelY);
                            red += (rgb >> 16) & 0xFF;
                            green += (rgb >> 8) & 0xFF;
                            blue += rgb & 0xFF;
                            count++;
                        }
                    }
                }

                // Calculate the average color of each pixel based on kernels
                int avgRed = red / count;
                int avgGreen = green / count;
                int avgBlue = blue / count;

                // Set the new pixel value
                int newRGB = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                result.setRGB(x, y, newRGB);
            }
        }
        // Return final blurred image
        Image image = ImageUtil.convertBufferedImageToFxImage(result);
        return image;
    }
}

