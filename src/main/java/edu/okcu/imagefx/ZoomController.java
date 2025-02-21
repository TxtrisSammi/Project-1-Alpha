package edu.okcu.imagefx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ZoomController {
    @FXML
    private ImageView img;

    public ZoomController(ImageView image) {
        img.setImage(image.getImage());
    }

}
