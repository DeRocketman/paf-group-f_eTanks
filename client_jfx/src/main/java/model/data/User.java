package model.data;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class User {
    private long id;
    private String username;
    private String publicName;
    private String userImage;
    private String password;
    private UserSettings userSettings;
    private UserStatistic userStatistic;
    private List<GameStatistic> gameStatistics;

    //TODO: Refactor constructor after all is fine

    public User () {
        this.username = "default";
        this.publicName = "default";
        this.userImage = "default";
        this.password = "default";
        this.userSettings = new UserSettings();
        this.userSettings.setDefaultSettings();
        this.userStatistic = new UserStatistic();
        this.userStatistic.setDefaultStatistic();
        this.gameStatistics = new ArrayList<>();
    }

    public User(long id, String username, String publicName, String image, String password,
                UserSettings userSettings, List<GameStatistic> userStatistic) throws IOException {
        this.id = id;
        this.username = username;
        this.publicName = publicName;
        this.userImage = decodeImage(image);
        this.password = password;
        this.userSettings = userSettings;
        this.gameStatistics = userStatistic;
    }

    public User(long id, String username, String publicName, String image, String password, UserSettings userSettings, UserStatistic userStatistic) throws IOException {
        this.id = id;
        this.username = username;
        this.publicName = publicName;
        this.userImage = decodeImage(image);
        this.password = password;
        this.userSettings = userSettings;
        this.userStatistic = userStatistic;
    }

    public String decodeImage(String imagePath) {
        try {
            System.out.println(new File(".").getAbsoluteFile());
            byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            return encodedString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "default";
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(UserSettings userSettings) {
        this.userStatistic = userStatistic;
    }


   /* public List<GameStatistic> getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistics(List<GameStatistic> gameStatistics) {
        this.gameStatistics = gameStatistics;
    }*/
}
