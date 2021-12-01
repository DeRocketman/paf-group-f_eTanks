package main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class HttpRequest {
    //Method 1: java.net.http.HttpClient -> use if java version >= 11
    //Method 2: java.net.HttpURLConnection -> use if java version < 11

    private static HttpURLConnection connection;
    public static void main(String[] args) {

    /**
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("http://localhost:8080/user");
            connection = (HttpURLConnection) url.openConnection();

            // Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        **/

        HttpClient client = HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/user")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenAccept(HttpRequest::parseJSON)
                .join();

    }

    //parse jsonData
    public static String parseJSON(String responseBody) {
        JSONArray users = new JSONArray(responseBody);
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            //JSONObject userSettings = user.getJSONObject("userSettings"); do something with it
            //JSONObject userStatistic = user.getJSONObject("gameStatistics"); do something with it too
            long id = user.getLong("id");
            String username = user.getString("username");
            String publicName = user.getString("publicName");
            String password = user.getString("password");
            System.out.println(id + " " + username +  " " + publicName + " " + password);
        }
        return null;
    }
}
