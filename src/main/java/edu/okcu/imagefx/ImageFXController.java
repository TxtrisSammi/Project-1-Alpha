package edu.okcu.imagefx;
import edu.okcu.imagefx.filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.okcu.imagefx.filters.Inverse;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

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
    private ImageView imgNewPicture;

    private File selectedFile;



    public void initialize() {
        //Set the list of filters as the options for the ComboBox
        filterComboBox.setValue(" ");
        ObservableList<String> items =
                FXCollections.observableArrayList(filterChoices);
        filterComboBox.setItems(items);
    }

    public Image applyFilter(String filterName) throws IOException {
        //Apply Filter based on the filterName parameter
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
        } catch (Exception e) {
            System.out.println("No File Selected");
            imgPicture.setImage(oldImage);
            imageFile = oldImageFile; 
        }  
    }

    @FXML
    protected void onApplyButtonClick() throws IOException {
        //Gets selected filter in ComboBox
        String filterChoice = (String) filterComboBox.getValue();
        if (imageFile != null) {
            //Sets imgNewPicture as applyFiler(filterChoice)
            imgNewPicture.setImage(applyFilter(filterChoice));
        }
    }
}
