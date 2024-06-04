package de.shekhovtsov.giphyapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class GifApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Welcome to Gif-World!");

        FXMLLoader fxmlLoader = new FXMLLoader(GifApplication.class.getResource("gif-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}