package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.okcu.imagefx.filters.Inverse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    SepiaFilter sepiaFilter = new SepiaFilter();
    RotationFilter rotationFilter = new RotationFilter();

    private final String[] filterChoices = {"Sepia", "Grayscale", "Bryan", "Sammy", "Michael", "ConvolutionTest"}; //List of Filters
    private File imageFile;

    @FXML
    private ComboBox filterComboBox;
    @FXML
    private ImageView imgPicture;
    @FXML
    private ImageView imgNewPicture;

    private File selectedFile;

    //Set the list of filters as the options for the ComboBox
    public void initialize() {
        filterComboBox.setValue(" ");
        ObservableList<String> items =
                FXCollections.observableArrayList(filterChoices);
        filterComboBox.setItems(items);
    }

    public Image applyFilter(String filterName) throws IOException {
        return switch (filterName) {
            case "Sepia" -> sepiaFilter.apply(imageFile);
            case "Grayscale" -> grayScaleFilter.apply(imageFile);
            case "Bryan" -> convolutionTest.apply(imageFile); //Replace with Inverse
            case "Sammy" -> rotationFilter.apply(imageFile);
            case "Michael" -> meanFilter.apply(imageFile);
            case "ConvolutionTest" -> convolutionTest.apply(imageFile);
            default -> null;
        };
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
        String filterChoice = (String) filterComboBox.getValue();
        if (imageFile != null) {
            imgNewPicture.setImage(applyFilter(filterChoice));
        }
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
