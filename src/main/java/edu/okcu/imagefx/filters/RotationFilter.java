package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotationFilter implements IFilter {
    //@Override
    public BufferedImage apply(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                Color color = new Color(pixel);

                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();


                // Create an Integer for the new values
                int newPixel = (alpha<<24) | (red<<16) | (green<<8) | blue;

                int newHeight = y - 950;
                int newWidth = x - 950;

                newHeight -= (newHeight * 2);
                newWidth -= (newWidth * 2);

                newHeight += 950;

                newWidth += 950;

                img.setRGB(newWidth, newHeight, newPixel);

            }
        }
        return img;
    }


    public javafx.scene.image.Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);

        double halfHeight = img.getHeight() / 2.0;
        double halfWidth = img.getWidth() / 2.0;

        for (int y = 0; y < halfHeight; y++) {
            for (int x = 0; x < halfWidth; x++) {
                int pixel = img.getRGB(x, y);

                Color color = new Color(pixel);


                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;

                double newy = y - halfHeight;
                double newx = x - halfWidth;

                newy = (-newy);
                newx = (-newx);

                newy += (halfHeight - 1);

                newx += (halfWidth - 1);




                int pixel2 = img.getRGB((int)newx, (int)newy);

                Color color2 = new Color(pixel2);


                int alpha2 = color2.getAlpha();
                int red2 = color2.getRed();
                int green2 = color2.getGreen();
                int blue2 = color2.getBlue();

                int newPixel2 = (alpha2 << 24) | (red2 << 16) | (green2 << 8) | blue2;





                img.setRGB(x, y, newPixel2);

                img.setRGB((int)newx, (int)newy, newPixel);

            }

        }

        Image image = ImageUtil.convertBufferedImageToFxImage(img);
        return image;
    }
}
