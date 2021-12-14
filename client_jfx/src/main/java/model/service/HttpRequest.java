package model.service;

import javafx.scene.control.Alert;
import main.ETankApplication;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;
import org.apache.commons.httpclient.HttpConnection;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    ETankApplication eTankApplication;

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public boolean login() {

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

        String jsonInputString = eTankApplication.getSignedUser().toJSON();

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

            eTankApplication.setBearerToken(response.toString());
            findUserByUsername();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achtung");
            alert.setHeaderText("Username && || Passwort falsch!");
            alert.setContentText("Bitte versuchen Sie es erneut");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void findUserByUsername() {

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

        String jsonInputString = eTankApplication.getSignedUser().getUserName();

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


        //TODO kann sicherlich vereinfacht werden
        JSONObject jsonObject = new JSONObject(response.toString());

        //User aus JSON
        String username = jsonObject.getString("username");
        String publicName = jsonObject.getString("publicName");
        long id = jsonObject.getLong("id");
        String userImage = jsonObject.getString("userImage");
        //UserSettings aus JSON holen
        JSONObject userSettingsJSON = (JSONObject) jsonObject.get("userSettings");

        long userSettingsId = userSettingsJSON.getLong("id");
        int gameMusicVolume = userSettingsJSON.getInt("gameMusicVolume");
        int gameSoundVolume = userSettingsJSON.getInt("gameSoundVolume");
        boolean gameMusicOn = userSettingsJSON.getBoolean("gameSoundOn");
        boolean gameSoundOn = userSettingsJSON.getBoolean("gameSoundOn");
        String moveUpKey = userSettingsJSON.getString("moveUpKey");
        String moveDownKey = userSettingsJSON.getString("moveDownKey");
        String moveLeftKey = userSettingsJSON.getString("moveLeftKey");
        String moveRightKey = userSettingsJSON.getString("moveRightKey");
        String showStatisticKey = userSettingsJSON.getString("showStatisticKey");
        String fireMainWeaponKey = userSettingsJSON.getString("fireMainWeaponKey");
        String fireSecondWeaponKey = userSettingsJSON.getString("fireSecondWeaponKey");


        //UserSettings erstellen
        UserSettings userSettings = new UserSettings();
        userSettings.setId(userSettingsId);
        userSettings.setGameMusicVolume(gameMusicVolume);
        userSettings.setGameSoundVolume(gameSoundVolume);
        userSettings.setGameMusicOn(gameMusicOn);
        userSettings.setGameSoundOn(gameSoundOn);
        userSettings.setMoveUpKey(moveUpKey);
        userSettings.setMoveDownKey(moveDownKey);
        userSettings.setMoveLeftKey(moveLeftKey);
        userSettings.setMoveRightKey(moveRightKey);
        userSettings.setShowStatisticKey(showStatisticKey);
        userSettings.setFireMainWeaponKey(fireMainWeaponKey);
        userSettings.setFireSecondaryWeaponKey(fireSecondWeaponKey);

        eTankApplication.getSignedUser().setUserSettings(userSettings);

        eTankApplication.getSignedUser().setId(id);
        eTankApplication.getSignedUser().setUserName(username);
        eTankApplication.getSignedUser().setPublicName(publicName);
        eTankApplication.getSignedUser().setImage(userImage);

        con.disconnect();

        System.out.println(eTankApplication.getSignedUser().toJSON());
        System.out.println(response);
    }

    public boolean registerUser(String username, String pubName, String password){

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
            System.out.println("HIER NICHT");
            return false;
        } catch (IOException e) {
            System.out.println("HIER");
            e.printStackTrace();
            return false;
        }

        String jsonInputString = "{\"username\" : \"" + username + "\",\"publicName\":\""+ pubName +"\",\"password\" :\"" + password + "\" }";

        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("HIER AUCH NICHT");
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
            return false;
        }
        return true;
    }

    public boolean registerUserByUser(User user){

        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/auth/register/user");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("HIER NICHT");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        String jsonInputString = user.toJSON();

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
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean loginByUser() {

        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/auth/login/user");

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

        String jsonInputString = eTankApplication.getSignedUser().toJSON();

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

            eTankApplication.setBearerToken(response.toString());
            findUserByUsername();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achtung");
            alert.setHeaderText("Username && || Passwort falsch!");
            alert.setContentText("Bitte versuchen Sie es erneut");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
