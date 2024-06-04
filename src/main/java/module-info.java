module de.shekhovtsov.giphyapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens de.shekhovtsov.giphyapi to javafx.fxml;
    exports de.shekhovtsov.giphyapi;
}