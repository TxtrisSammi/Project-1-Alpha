package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.GrayScaleFilter;
import edu.okcu.imagefx.filters.RotationFilter;
import edu.okcu.imagefx.filters.SepiaFilter;
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
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    SepiaFilter sepiaFilter = new SepiaFilter();
    RotationFilter rotationFilter = new RotationFilter();

    Inverse inverseFilter = new Inverse();
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


        String selectedFilter = (String) cmbFilterSelect.getValue();

        switch (selectedFilter) {
            case "Sepia":
                imgPicture.setImage(image);
                imgNewPicture.setImage(sepiaFilter.apply(file));
                break;
            case "Grayscale":
                imgPicture.setImage(image);
                imgNewPicture.setImage(grayScaleFilter.apply(file));
                break;
            case "3rd Option":
                imgPicture.setImage(image);
                imgNewPicture.setImage(rotationFilter.apply(file));
                break;
            default:
                imgPicture.setImage(image);
        }
        imgPicture.setImage(image);
        imgNewPicture.setImage(grayScaleFilter.apply(file));

        imgPicture.setImage(image);
        imgNewPicture.setImage(inverseFilter.apply(file));

    }




    @FXML
    private ComboBox cmbFilterSelect;

    String [] filters = {"Grayscale", "Sepia", "3rd Option", "Inverse"};

    public void initialize() {
        ObservableList<String> list =
                FXCollections.observableArrayList(filters);
        cmbFilterSelect.setItems(list);
    }

}