package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageFXController {
    SepiaFilter sepiaFilter = new SepiaFilter();
    RotationFilter rotationFilter = new RotationFilter();
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    MeanFilter meanFilter = new MeanFilter();
    ConvolutionTest convolutionTest = new ConvolutionTest();

    private String[] filterChoices = {"Sepia", "Grayscale", "Bryan", "Rotation", "Michael", "ConvolutionTest"};
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
            return sepiaFilter.apply(imageFile);
        } else if (filterName == "Grayscale") {
            return grayScaleFilter.apply(imageFile);
        } else if (filterName == "Bryan") {
        } else if (filterName == "Rotation") {
            return rotationFilter.apply(imageFile);
        } else if (filterName == "Michael") {
            return meanFilter.apply(imageFile);
        } else if (filterName =="ConvolutionTest") {
            return convolutionTest.apply(imageFile);
        }
        return null;
    }

    public void onLeftLoadButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("zoom.fxml"));
            ZoomController zoomController = new ZoomController(imgPicture);
            fxmlLoader.setController(zoomController);
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Zoom Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void onRightLoadButtonClick(ActionEvent actionEvent) {
    }
}
