package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
    MeanFilter meanFilter = new MeanFilter();

    private String[] filterChoices = {"Sepia", "Grayscale", "Bryan", "Sammy", "Michael"};
    private File imageFile;

    @FXML
    private ChoiceBox filterChoiceBox;

    @FXML
    private ImageView imgPicture;
    @FXML
    private ImageView imgNewPicture;
    private File selectedFile;


    public void initialize() {
        filterChoiceBox.setValue(filterChoices[0]);
        ObservableList<String> items = FXCollections.observableArrayList(filterChoices);
        filterChoiceBox.setItems(items);
    }

    @FXML
    protected void onLoadButtonClick() throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        imageFile = file;

        Image image = new Image(file.toURI().toString());
        imgPicture.setImage(image);
    }

    @FXML
    protected void onApplyButtonClick() throws IOException {
        String filterChoice = (String) filterChoiceBox.getValue();
        if (imageFile != null) {
            imgNewPicture.setImage(applyFilter(filterChoice));
        }
    }

    public Image applyFilter(String filterName) throws IOException {
        if (filterName == "Sepia") {
        } else if (filterName == "Grayscale") {
            return grayScaleFilter.apply(imageFile);
        } else if (filterName == "Bryan") {
        } else if (filterName == "Sammy") {
        } else if (filterName == "Michael") {
            return meanFilter.apply(imageFile);
        }
        return null;
    }
}
