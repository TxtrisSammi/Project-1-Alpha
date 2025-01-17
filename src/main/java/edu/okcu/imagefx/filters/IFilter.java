package edu.okcu.imagefx.filters;

import java.awt.image.BufferedImage;

public interface IFilter {
    BufferedImage apply(BufferedImage img);
}
