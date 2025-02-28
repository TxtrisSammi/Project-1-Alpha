package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SepiaFilter implements IFilter {
    
    public Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                Color color = new Color(pixel);

                //Initializes colors
                //Fetches colors
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // Calculation for Sepia
                var newRed = (red * 0.393) + (green * 0.769) + (blue * 0.189);
                var newGreen =  (red * 0.349) + (green * 0.686) + (blue * 0.168);
                var newBlue =  (red * 0.272) + (green * 0.534) + (blue * 0.131);

                /*
                set rgb = 255 to create a cap for how high the rgb values can be
                since rgb values can only be from 0-255
                found this on StackOverFlow
                https://stackoverflow.com/questions/1061093/how-is-a-sepia-tone-created
                 */
                if (newRed > 255){
                    newRed = 255;
                }
                if (newGreen > 255){
                    newGreen = 255;
                }
                if (newBlue > 255){
                    newBlue = 255;
                }

                // Create an Integer for the new values
                int newPixel =  ((alpha<<24) | ((int)newRed<<16) | ((int)newGreen<<8) | (int)newBlue);
                img.setRGB(x, y, newPixel);
            }
        }

        Image image = ImageUtil.convertBufferedImageToFxImage(img);
        return image;
    }
}
