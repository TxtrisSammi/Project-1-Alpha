package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.GrayScaleFilter;
import edu.okcu.imagefx.filters.RotationFilter;
import edu.okcu.imagefx.filters.SepiaFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ImageFXController {
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    SepiaFilter sepiaFilter = new SepiaFilter();
    RotationFilter rotationFilter = new RotationFilter();

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
    protected void onHelloButtonClick() throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);

        Image image = new Image(file.toURI().toString());

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
