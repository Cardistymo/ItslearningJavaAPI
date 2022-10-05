package de.cardistymo.itslearningapi.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ItslearningRequests {

    public static JSONObject execute(String urlString, Map<String, String> params) throws IOException {
        StringBuilder urlStringBuilder = new StringBuilder(urlString);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlStringBuilder.append(urlStringBuilder.toString().contains("?") ? "&" : "?").append(entry.getKey()).append("=").append(entry.getValue());
        }

        URL url = new URL(urlStringBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Request failure!");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }

        reader.close();

        return new JSONObject(output.toString());
    }

    public static String getAccessToken(String urlString, String clientID, String username, String password) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "response_type=token&client_id=" + clientID + "&username=" + username + "&password=" + password + "&scope=process&grant_type=password");

        Request request = new Request.Builder()
                .url(urlString)
                .method("POST", body)
                .addHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode("ClientId:Clientaccesskey".getBytes(StandardCharsets.UTF_8))))
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();

        HashMap responseMap = new ObjectMapper().readValue(response.body().byteStream(), HashMap.class);
        return (String) responseMap.get("access_token");
    }
}
