package de.shekhovtsov.giphyapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GifApplication extends Application {
    private static final String API_URL = "https://api.giphy.com/v1/gifs/random";
    private static final String API_KEY = "SAUAZzwRTKgFX1XCPZJJyA5o55zV4vmn"; // Replace with your Giphy API key
    private static final String TAG = "funny";

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Welcome to Gif-World!");

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);

        String gifURL = fetchRandomGifUrl();

        if(gifURL != null) {
            Image image = new Image(gifURL, true);
            imageView.setImage(image);
        }

       // FXMLLoader fxmlLoader = new FXMLLoader(GifApplication.class.getResource("gif-view.fxml"));
       // Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        VBox vBox = new VBox(imageView);
        Scene scene = new Scene(vBox, 800, 600);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    private String fetchRandomGifUrl() {
        try {
            URL url = new URL(API_URL + "?api_key=" + API_KEY + "&tag=" + TAG);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                GifObject gifObject = objectMapper.readValue(response.toString(), GifObject.class);
                return gifObject.data().images().original().url();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}