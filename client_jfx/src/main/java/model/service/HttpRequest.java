package model.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import main.ETankApplication;
import model.data.*;
import org.boon.core.Sys;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpRequest {

    ETankApplication eTankApplication;

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public boolean login(Authorisation authorisation) {

        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/auth/login");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Gson gson = new Gson();
        String jsonInputString = gson.toJson(authorisation);

        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            StringBuilder response = new StringBuilder();
            String responseLine = null;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println(response.toString());
            eTankApplication.setBearerToken(response.toString());
            findUserByUsername(authorisation);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achtung");
            alert.setHeaderText("Username && || Passwort falsch!");
            alert.setContentText("Bitte versuchen Sie es erneut");
            alert.showAndWait();
            return false;
        }
        con.disconnect();
        return true;
    }

    public void findUserByUsername(Authorisation authorisation) {

        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://127.0.0.1:8080/user/username");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Authorization", "Bearer " + eTankApplication.getBearerToken());
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonInputString = authorisation.getUsername();


        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer response = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;

            response = new StringBuffer();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        eTankApplication.setSignedUser(gson.fromJson(String.valueOf(response), User.class));

        con.disconnect();
    }

    public boolean registerUser(Authorisation authorisation){

        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/auth/register");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Gson gson = new Gson();
        String jsonInputString = gson.toJson(authorisation);

        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            StringBuilder response = new StringBuilder();
            String responseLine = null;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achtung");
            alert.setHeaderText("Username bereits vergeben!");
            alert.setContentText("Bitte versuchen Sie es erneut");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean saveUser(User user){

        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/user/save");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + eTankApplication.getBearerToken());
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonInputString = gson.toJson(user);
        System.out.println(jsonInputString);

        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer response = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            response = new StringBuffer();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response);
        con.disconnect();
        return true;
    }

    public boolean saveSettings(UserSettings usersettings){

        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://127.0.0.1:8080/user_settings/update_settings");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Authorization", "Bearer " + eTankApplication.getBearerToken());
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonInputString = gson.toJson(usersettings);

        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer response = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;

            response = new StringBuffer();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        eTankApplication.getSignedUser().setUserSettings(gson.fromJson(String.valueOf(response), UserSettings.class));

        con.disconnect();

        return true;
    }

    public boolean saveGameStatistic(GameStatistic gameStatistic, Long userId){

        System.out.println(gameStatistic);

        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/user_game_statistic/new/" + userId);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + eTankApplication.getBearerToken());
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Gson gson = new Gson();
        String jsonInputString = gson.toJson(gameStatistic);
        System.out.println(jsonInputString);

        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        StringBuffer response = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            response = new StringBuffer();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
        } catch (IOException e) {
            System.out.println("Blöder Response!");
            e.printStackTrace();
            return false;
        }

        System.out.println(response);
        con.disconnect();
        return true;
    }

    public boolean getGameStatisticList(Long userId) {
        String line;
        StringBuilder responseContent = new StringBuilder();
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/user_game_statistic/" + userId);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + eTankApplication.getBearerToken());
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            BufferedReader reader;
            int status = con.getResponseCode();
            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //Response to gameStatistic List
        Gson gson = new Gson();
        Type gameStatisticType = new TypeToken<ArrayList<GameStatistic>>(){}.getType();
        ArrayList<GameStatistic> list = gson.fromJson(String.valueOf(responseContent), gameStatisticType);
        eTankApplication.setGameStatistic(list);

        con.disconnect();
        return true;
    }
}
