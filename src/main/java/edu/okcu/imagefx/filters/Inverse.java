package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Inverse implements IFilter {
    @Override
    public BufferedImage apply(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                Color color = new Color(pixel);

                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // Calculation for Inverse
                var newRed = (255 - red);
                var newGreen = (255 - green);
                var newBlue = (255 - blue);

                // Create an Integer for the new values
                int newPixel = (alpha<<24) | (newRed<<16) | (newGreen<<8) | newBlue;
                img.setRGB(x, y, newPixel);
            }
        }
        return img;
    }

    public javafx.scene.image.Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                Color color = new Color(pixel);

                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // Calculation for Inverse
                var newRed = (255 - red);
                var newGreen = (255 - green);
                var newBlue = (255 - blue);

                // Create an Integer for the new values
                int newPixel = (alpha<<24) | (newRed<<16) | (newGreen<<8) | newBlue;
                img.setRGB(x, y, newPixel);
            }
        }

        Image image = ImageUtil.convertBufferedImageToFxImage(img);
        return image;
    }
}
