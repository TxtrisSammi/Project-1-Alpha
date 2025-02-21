package edu.okcu.imagefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


    /*
    There's a weird thing that happens sometimes when you hover over the "Apply" button where the text on it shifts for some reason
    I can't figure out why this is happening or where the error is coming from. I'll keep trying to work on it, but if someone else could
    take a look too, that would be great.
    */
public class ImageFXApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ImageFXApplication.class.getResource("image-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("ImageFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}