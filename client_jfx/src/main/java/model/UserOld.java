package model;

import javafx.scene.image.Image;

public class UserOld {
    private final int id;
    private String userName;
    private String publicName;
    private String image;
    private String password;
    private UserSettings userSettings;
    private UserStatistic userStatistic;

    public UserOld(int id, String userName, String publicName, String image, String password,
                   UserSettings userSettings, UserStatistic userStatistic) {
        this.id = id;
        this.userName = userName;
        this.publicName = publicName;
        this.image = image;
        this.password = password;
        this.userSettings = userSettings;
        this.userStatistic = userStatistic;
    }

    public int getId() {
        return id;
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
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }
}
