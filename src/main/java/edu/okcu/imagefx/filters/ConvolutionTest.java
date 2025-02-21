package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;


/*
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Convolution Filter
Uses Kernels to execute and modify color values based on a preselected Kernel filter
It does this by taking the color value, then multiplying it by a multiplier of the kernel.
It adds each of these multiplier instances into a final sum which is the color value. And this
is done for each of the rgb colors.

Ex.
float[] sharpenKernel = {
    0, -1, 0,
    -1, 5, -1,
    0, -1, 0
};

Pixel:       (200, 200, 200)
Neighbors:   (100, 100, 100)

Red = (200 * 5) + (100 * -1) + (100 * -1) + (100 * -1) + (100 * -1)
     = 1000 - 100 - 100 - 100 - 100
     = 600

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Kernel Examples:

sharpenKernel
    0, -1, 0,
    -1, 5, -1,
    0, -1, 0

blurKernel
    1/9f, 1/9f, 1/9f,
    1/9f, 1/9f, 1/9f,
    1/9f, 1/9f, 1/9f

edgeDetectionKernel
    -1, -1, -1,
    -1, 8, -1,
    -1, -1, -1

sobelEdgeDetectionKernel
    -1, -2, -1,
    0, 0, 0,
    1, 2, 1

gaussianBlurKernel (Approximation)
    1/16f, 2/16f, 1/16f,
    2/16f, 4/16f, 2/16f,
    1/16f, 2/16f, 1/16f

gaussianKernel5x5 (Real)
    1/273f, 4/273f, 7/273f, 4/273f, 1/273f,
    4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
    7/273f, 26/273f, 41/273f, 26/273f, 7/273f,
    4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
    1/273f, 4/273f, 7/273f, 4/273f, 1/273f


~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sources:
https://en.wikipedia.org/wiki/Kernel_(image_processing)
https://www.cs.auckland.ac.nz/compsci373s1c/PatricesLectures/Convolution_1up.pdf
 */

public class ConvolutionTest implements IFilter {

    public Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        float[] kernel = {
                -1, -2, -1,
                0, 0, 0,
                1, 2, 1
        };
        int kernelSize = 3;
        int kernelRadius = (kernelSize - 1) / 2;

        // Iterates over each pixel for the final image
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int red = 0, green = 0, blue = 0;

                // Iterate over the kernel surrounding the selected pixel
                for (int ky = -kernelRadius; ky <= kernelRadius; ky++) {
                    for (int kx = -kernelRadius; kx <= kernelRadius; kx++) {
                        int pixelX = x + kx;
                        int pixelY = y + ky;

                        // Check if the pixel is within the image bounds
                        // (As kernels may search outside the image for image border pixels)
                        if (pixelX >= 0 && pixelX < img.getWidth() && pixelY >= 0 && pixelY < img.getHeight()) {
                            // Multiples color values by kernelMultipliers for each neighbor and adds to final color
                            int rgb = img.getRGB(pixelX, pixelY);
                            int kernelIndex = (ky + kernelRadius) * kernelSize + (kx + kernelRadius);
                            float weight = kernel[kernelIndex];

                            red += ((rgb >> 16) & 0xFF) * weight;
                            green += ((rgb >> 8) & 0xFF) * weight;
                            blue += (rgb & 0xFF) * weight;
                        }
                    }
                }

                // Clamp the values to the range [0, 255]
                // Why? Instances such as image sharpening can result
                // in the accumulation adding to values exceeding 255
                int newRed = (int) Math.min(Math.max(red, 0), 255);
                int newGreen = (int) Math.min(Math.max(green, 0), 255);
                int newBlue = (int) Math.min(Math.max(blue, 0), 255);

                // Set the new pixel value
                int newRGB = (newRed << 16) | (newGreen << 8) | newBlue;
                result.setRGB(x, y, newRGB);
            }
        }
        // Return final blurred image
        Image image = ImageUtil.convertBufferedImageToFxImage(result);
        return image;
    }

}
