package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import main.ETankApplication;
import model.data.GameStatistic;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;
import model.service.UserDataCreator;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;


public class LoginViewController {

    ETankApplication eTankApplication;
    UserDataCreator eudc = new UserDataCreator();

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label forgotPwLabel;
    @FXML
    Label newUserLabel;
    @FXML
    Button loginBtn;

    public void initialize() {
        loginBtn.setDisable(true);
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void keyRelased() {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        boolean isDisabled = (userName.isEmpty() || password.isEmpty());

        loginBtn.setDisable(isDisabled);
    }

    public void createUserView() throws IOException {
        eTankApplication.showCreateUserView();
    }


    //BearerToken wird angelegt und in der eTankapplication gespeichert
    public void changeView() throws IOException {

        URL url = new URL("http://127.0.0.1:8080/auth/login");
        //OpenConnection
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String username = usernameField.getText();
        String password = passwordField.getText();

        //Bearbeiten
        String jsonInputString = "{\"username\" : \"" +username+ "\",\"password\" :\"" +password+ "\" }";


        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            eTankApplication.setBearerToken(response.toString());
            eTankApplication.showMenuView();

            findUserByUsername(username);

        } catch (Exception e){
            e.printStackTrace();
        }
        con.disconnect();
    }

    public void findUserByUsername(String username) throws IOException {
        URL url = new URL("http://127.0.0.1:8080/user/username");
        //OpenConnection
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Authorization", "Bearer " + eTankApplication.getBearerToken());
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonInputString = username;


        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        JSONObject jsonObject = new JSONObject(response.toString());
        System.out.println(response);

        //TODO UserSettings UserStatistics ordentlich Einbauen, auch in der API
        String publicName = jsonObject.getString("publicName");
        long id = jsonObject.getLong("id");
        UserSettings userSettings = null;
        UserStatistic userStatistic = new UserStatistic();

        eTankApplication.setSignedUser(new User(id,username,publicName,userSettings, userStatistic));

        in.close();
        con.disconnect();
    }


}
