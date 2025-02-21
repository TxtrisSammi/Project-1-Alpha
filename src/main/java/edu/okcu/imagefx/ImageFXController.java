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
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    MeanFilter meanFilter = new MeanFilter();
    ConvolutionTest convolutionTest = new ConvolutionTest();

    private String[] filterChoices = {"Sepia", "Grayscale", "Bryan", "Sammy", "Michael", "ConvolutionTest"};
    private File imageFile;

    @FXML
    private ChoiceBox filterChoiceBox;

    @FXML
    private ImageView imgPicture;
    @FXML
    private ImageView imgNewPicture;
    @FXML
    private ComboBox cmbFilterSelect;

    private File selectedFile;
    String [] filters = {"Grayscale", "Sepia", "Flip", "Inverse"}; //List of filters

    //Set the list of filters as the options for the ComboBox
    public void initialize() { 
        ObservableList<String> list =
                FXCollections.observableArrayList(filters);
        cmbFilterSelect.setItems(list);
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
        //Set selectedFilter as the option selected in the ComboBox
        String selectedFilter = (String) cmbFilterSelect.getValue(); 
        
        //Run the filter selected on the ComboBox
        switch (selectedFilter) {
            case "Sepia":
                imgPicture.setImage(image);
                imgNewPicture.setImage(sepiaFilter.apply(file));
                break;
            case "Grayscale":
                imgPicture.setImage(image);
                imgNewPicture.setImage(grayScaleFilter.apply(file));
                break;
            case "Flip":
                imgPicture.setImage(image);
                imgNewPicture.setImage(rotationFilter.apply(file));
                break;
            default:
                //If no filter is selected, set imgPicture but don't set imgNewPicture
                imgPicture.setImage(image);
        }
    }

    
   

    

}
