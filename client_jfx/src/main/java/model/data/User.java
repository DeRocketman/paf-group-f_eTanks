package model.data;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String username;
    private String publicName;
    private String userImage;
    private String password;
    private UserSettings userSettings;
    private UserStatistic userStatistic;
    //private List<GameStatistic> gameStatistics;

    public User () {
        this.setDefault();
    }

    public User(long id, String username, String publicName, String image, String password,
                UserSettings userSettings, List<GameStatistic> userStatistic) {
        this.id = id;
        this.username = username;
        this.publicName = publicName;
        this.userImage = image;
        this.password = password;
        this.userSettings = userSettings;
       // this.gameStatistics = userStatistic;
    }

    public User(long id, String username, String publicName, String image, String password, UserSettings userSettings, UserStatistic userStatistic) {
        this.id = id;
        this.username = username;
        this.publicName = publicName;
        this.userImage = image;
        this.password = password;
        this.userSettings = userSettings;
        this.userStatistic = userStatistic;
    }

    public void setDefault(){

        this.username = "default";
        this.publicName = "default";
        this.userImage = "Image";
        this.password = "default";
        this.userSettings = new UserSettings();
        this.userSettings.setDefaultSettings();
        this.userStatistic = new UserStatistic();
        this.userStatistic.setDefaultStatistic();
      //  this.gameStatistics = new ArrayList<>();
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

    public String getImage() {
        return userImage;
    }

    public void setImage(String image) {
        this.userImage = image;
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
