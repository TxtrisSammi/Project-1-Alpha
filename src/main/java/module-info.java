module edu.okcu.imagefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens edu.okcu.imagefx to javafx.fxml;
    exports edu.okcu.imagefx;
}