package model.data;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private List <GameStatistic> gameStatistics;

    //TODO: Refactor constructor after all is fine

    public User () {
        this.username = "default";
        this.publicName = "default";
        this.userImage = "default";
        this.password = "default";
        this.userSettings = new UserSettings();
        this.userSettings.setDefaultSettings();
        //this.userStatistic = new UserStatistic();
        //this.userStatistic.setDefaultStatistic();
        //this.gameStatistics = new ArrayList<>();
        this.gameStatistics = new ArrayList<>();
    }

    public User(long id, String username, String publicName, String imagePath, String password,
                UserSettings userSettings, List<GameStatistic> gameStatistics)  {
        this.id = id;
        this.username = username;
        this.publicName = publicName;
        this.userImage = encodeImage(imagePath);
        this.password = password;
        this.userSettings = userSettings;
        this.gameStatistics = gameStatistics;
    }

    public User(long id, String username, String publicName, String imagePath, String password, UserSettings userSettings, UserStatistic userStatistic) {
        this.id = id;
        this.username = username;
        this.publicName = publicName;
        this.userImage = encodeImage(imagePath);
        this.password = password;
        this.userSettings = userSettings;
        this.userStatistic = userStatistic;
    }

    //Image File to String
    public String encodeImage(String imagePath) {
        String base64 = null;
        try {
            base64 = DatatypeConverter.printBase64Binary(Files.readAllBytes(
                    Paths.get(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
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

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    public void setGameStatistics(GameStatistic gameStatistic){
        this.gameStatistics.add(gameStatistic);
    }

    public List<GameStatistic> getGameStatistics(){
        return gameStatistics;
    }

   /* public List<GameStatistic> getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistics(List<GameStatistic> gameStatistics) {
        this.gameStatistics = gameStatistics;
    }*/
}
