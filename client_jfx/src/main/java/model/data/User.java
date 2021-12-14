package model.data;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String userName;
    private String publicName;
    private String userImage;
    private String password;
    private UserSettings userSettings;
    private List<GameStatistic> gameStatistics;

    public User () {
        this.setDefault();
    }

    public User(long id, String userName, String publicName, String image, String password,
                UserSettings userSettings, List<GameStatistic> userStatistic) {
        this.id = id;
        this.userName = userName;
        this.publicName = publicName;
        this.userImage = image;
        this.password = password;
        this.userSettings = userSettings;
        this.gameStatistics = userStatistic;
    }

    public User(long id, String userName, String publicName, String image, String password, UserSettings userSettings) {
        this.id = id;
        this.userName = userName;
        this.publicName = publicName;
        this.userImage = image;
        this.password = password;
        this.userSettings = userSettings;
    }

    public void setDefault(){

        this.userName = "default";
        this.publicName = "default";
        this.userImage = "Image";
        this.password = "default";
        this.userSettings = new UserSettings();
        this.userSettings.setDefaultSettings();
        this.gameStatistics = new ArrayList<>();
    }

    public String toJSON(){
        return "{\"user\":{\"id\":" + this.getId() + ",\"username\":\""+ this.getUserName() + "\",\"userImage\":\""+this.getImage()+"\",\"publicName\":\""+ this.getPublicName() +
                "\",\"password\":\"" + this.getPassword() + "\",\"userSettings\":"+ userSettings.toJSON()+"}}";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<GameStatistic> getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistics(List<GameStatistic> gameStatistics) {
        this.gameStatistics = gameStatistics;
    }
}
