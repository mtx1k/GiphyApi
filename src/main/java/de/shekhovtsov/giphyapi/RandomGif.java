package de.shekhovtsov.giphyapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomGif {
    private static final String API_URL = "https://api.giphy.com/v1/gifs/random";
    private static final String API_KEY = "Your Secret Key"; // Replace with your Giphy API key
    private static final String TAG = "funny";

    public RandomGif() {

    }

    public String getGifURL() {

        return fetchRandomGifUrl();
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
