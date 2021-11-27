package model.data;

import javafx.beans.property.*;

public class User {

    private final LongProperty id;
    private StringProperty userName;
    private StringProperty publicName;
    private StringProperty image;
    private StringProperty password;
    private UserSettings userSettings;
    private UserStatistic userStatistic;


    public User(long id, String userName, String publicName, String image, String password, UserSettings userSettings, UserStatistic userStatistic) {
        this.id = new SimpleLongProperty(id);
        this.userName = new SimpleStringProperty(userName);
        this.publicName = new SimpleStringProperty(publicName);
        this.image = new SimpleStringProperty(image);
        this.password = new SimpleStringProperty(password);
        this.userSettings = userSettings;
        this.userStatistic = userStatistic;
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPublicName() {
        return publicName.get();
    }

    public StringProperty publicNameProperty() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName.set(publicName);
    }

    public String getImage() {
        return image.get();
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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


/**
public class User {
    private final int id;
    private String userName;
    private String publicName;
    private String image;
    private String password;
    private UserSettings userSettings;
    private UserStatistic userStatistic;

    public User(int id, String userName, String publicName, String image, String password,
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
**/