package edu.okcu.imagefx.filters;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface IFilter {
    Image apply(File img) throws IOException;
}
