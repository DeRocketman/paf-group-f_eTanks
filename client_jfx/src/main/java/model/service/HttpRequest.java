package model.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;
import main.ETankApplication;
import model.data.*;

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
import java.util.List;

public class HttpRequest {

    ETankApplication eTankApplication;

    /**
     * Logs in the user and sets the BearerToken in eTankApplication
     *
     * @param authorisation Authorisation Object with username, publicname and password
     * @return              boolean if the login was successful
     */
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

    /**
     * Gets user image by user id
     *
     * @param id    the ID of the user
     * @return      the user image as string
     */
    public String getImageById(long id){
        String userImage;

        URL url;
        HttpURLConnection con = null;
        try {
            url = new URL("http://127.0.0.1:8080/user/id");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Authorization", "Bearer " + eTankApplication.getBearerToken());
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonInputString = String.valueOf(id);


        try {
            assert con != null;
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder response = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;

            response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        User tempUser = gson.fromJson(String.valueOf(response), User.class);

        userImage = tempUser.getUserImage();

        con.disconnect();

        return userImage;
    }

    /**
     * Finds user in database by username
     * and sets the signedUser in eTankApplication
     *
     * @param authorisation     Authorisation Object with username, publicname and password
     */
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

        StringBuilder response = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;

            response = new StringBuilder();
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

    /**
     * Registers a new user in the database
     *
     * @param authorisation     Authorisation Object with username, publicname and password
     * @return                  boolean if registration was successful
     */
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

    /**
     * saves the changed user to the database
     *
     * @param user
     * @return      boolean if saving was successful
     */
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

    /**
     * Saves the changes settings in the database
     *
     * @param usersettings  the setting to save
     * @return              boolean if saving was successful
     */
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

    /**
     * Saves a gameStatistic in the database
     *
     * @param gameStatistic     the statistic to save
     * @param userId
     * @return                  boolean if saving was successful
     */
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

    /**
     * Gets the list of all gameStatistics of the requested user
     * and passes the list to the eTankApplication
     *
     * @param userId    userID of the requested user
     * @return          the list of gameStatistics
     */
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
        System.out.println(responseContent);
        eTankApplication.setGameStatistic(list);

        con.disconnect();
        return true;
    }

    /**
     * Gets the highscore list of gameStatistics
     *
     * @param size      size of the highscorelist
     * @return          highscore list of gameStatistics
     */
    public List<GameStatistic> getHighscoreList(int size) {
        String line;
        StringBuilder responseContent = new StringBuilder();
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/user_game_statistic/highscorelist/" + size);
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
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        //Response to gameStatistic List
        Gson gson = new Gson();
        Type gameStatisticType = new TypeToken<ArrayList<GameStatistic>>(){}.getType();
        ArrayList<GameStatistic> list = gson.fromJson(String.valueOf(responseContent), gameStatisticType);

        con.disconnect();
        return list;
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}
