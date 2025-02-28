package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.okcu.imagefx.filters.Inverse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageFXController {
    SepiaFilter sepiaFilter = new SepiaFilter();
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    Inverse inverse = new Inverse();
    RotationFilter rotationFilter = new RotationFilter();
    MeanFilter meanFilter = new MeanFilter();
    SobelOperatorFilter sobelFilter = new SobelOperatorFilter();
    GaussianBlurFilter gaussianBlurFilter = new GaussianBlurFilter();
    SharpenFilter sharpenFilter = new SharpenFilter();

    private final String[] filterChoices = {"Sepia", "Grayscale", "Inverse", "Rotate", "Mean", "Sobel", "Gaussian Blur", "Sharpen"}; //List of Filters
    private File imageFile;

    @FXML
    private ComboBox filterComboBox;
    @FXML
    private ImageView imgPicture;
    @FXML
    private VBox imgPictureVbox;
    @FXML
    private ImageView imgNewPicture;
    @FXML
    private VBox imgNewPictureVbox;

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
            case "Inverse" -> inverse.apply(imageFile);
            case "Rotate" -> rotationFilter.apply(imageFile);
            case "Mean" -> meanFilter.apply(imageFile);
            case "Sobel" -> sobelFilter.apply(imageFile);
            case "Gaussian Blur" -> gaussianBlurFilter.apply(imageFile);
            case "Sharpen" -> sharpenFilter.apply(imageFile);
            default -> null;
        };
    }

    @FXML
    protected void onLoadButtonClick() {
        /*
        Stores previous Image and File to revert back to in case of a null selection
        So if the user hits the load button then changes their mind, the image they had 
        selected previously is the image the filters get applied to.
         */
        File oldImageFile = imageFile;
        Image oldImage =  imgPicture.getImage();
        
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        imageFile = file;
        
        /*
        If user successfully selects a file, set the file as the image.
        If it runs into an error return to the previously selected image.
        */
        try {
            Image image = new Image(file.toURI().toString());
            imgPicture.setImage(image);
            imgPictureVbox.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-border-insets: -5 -5 -5 -5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 25, 0, 0, 0);");
        } catch (Exception e) {
            System.out.println("No File Selected");
            imgPicture.setImage(oldImage);
            imageFile = oldImageFile; 
        }  
    }

    @FXML
    protected void onApplyButtonClick() throws IOException {
        String filterChoice = (String) filterComboBox.getValue();
        if (imageFile != null) {
            imgNewPicture.setImage(applyFilter(filterChoice));
            imgNewPictureVbox.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-border-insets: -5 -5 -5 -5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 25, 0, 0, 0);");
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
