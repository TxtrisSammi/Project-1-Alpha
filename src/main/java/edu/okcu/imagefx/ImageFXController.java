package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.GrayScaleFilter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageFXController {
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    @FXML
    private ImageView imgPicture;
    @FXML
    private ImageView imgNewPicture;
    private File selectedFile;
    @FXML
    protected void onHelloButtonClick() throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);

        Image image = new Image(file.toURI().toString());

        imgPicture.setImage(image);
        imgNewPicture.setImage(grayScaleFilter.apply(file));
    }
    @FXML


}