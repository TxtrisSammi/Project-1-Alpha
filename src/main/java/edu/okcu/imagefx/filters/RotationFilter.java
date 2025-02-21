package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotationFilter implements IFilter {
    public Image apply(File file) throws IOException {

        BufferedImage img = ImageIO.read(file);

        double halfHeight = img.getHeight() / 2.0;
        double halfWidth = img.getWidth() / 2.0;


        for (int y = 0; y < halfHeight ; y++) {
            for (int x = 0; x < img.getWidth(); x++) {


                //Get data from pixel at (x, y)
                int pixel = img.getRGB(x, y);
                Color color = new Color(pixel);

                //Store pixel color data
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;


                /*  To Find Coordinates for Pixel Rotation
                    Usually to rotate about the origin you would just multiply the x and y values by -1
                    However our grid starts at (0, 0) and ends at (img.getWidth() - 1, img.getHeight() - 1)
                    So since we're not rotating about the origin, we have to set the center as the origin
                    To do this I'm translating all the pixels half the width to the left and half the height down
                    Making the center of the image == (0, 0)
                    Then I perform rotation about the origin as normal by multiplying the "newx" and "newy" by -1
                    Then I perform the opposite of the translation I did to bring the center of the image to the origin
                    i.e I translate all the pixels to the right half the width and up half the height
                */

                //Translates pixel coordinates left and down by half the width and height
                double newy = y - halfHeight;
                double newx = x - halfWidth;

                //Multiplies the coordinates by -1
                newy = (-newy);
                newx = (-newx);

                //Translates image right and up by half the width and height
                //The minus 1 is there to account for the width and height starting at 1 while coordinates start at (0, 0)
                newy += (halfHeight - 1);
                newx += (halfWidth - 1);


                //Get data from pixel at target location
                int pixel2 = img.getRGB((int)newx, (int)newy);
                Color color2 = new Color(pixel2);

                //Store second pixel color data
                int alpha2 = color2.getAlpha();
                int red2 = color2.getRed();
                int green2 = color2.getGreen();
                int blue2 = color2.getBlue();
                int newPixel2 = (alpha2 << 24) | (red2 << 16) | (green2 << 8) | blue2;

                
                //Set pixel at (x, y) to the color at target location
                img.setRGB(x, y, newPixel2);
                //Set pixel at target location to the color at (x, y)
                img.setRGB((int)newx, (int)newy, newPixel);

            }

        }

        Image image = ImageUtil.convertBufferedImageToFxImage(img);
        return image;
    }
}
