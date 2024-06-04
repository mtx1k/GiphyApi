package de.shekhovtsov.giphyapi;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GifController {
    @FXML
    VBox vbox;
    @FXML
    protected void onRandomButtonClick() {
        vbox.getChildren().clear();
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);

        String gifURL = new RandomGif().getGifURL();

        if(gifURL != null) {
            Image image = new Image(gifURL, true);
            imageView.setImage(image);
        }

        vbox.getChildren().add(imageView);
    }
}