package model.data;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class User {
    private long id;
    private String username;
    private String publicName;
    private String userImage;
    private String password;
    private UserSettings userSettings;

    public User () {
        this.username = "default";
        this.publicName = "default";
        this.userImage = "default";
        this.password = "default";
        this.userSettings = new UserSettings();
        this.userSettings.setDefaultSettings();
    }

    public User(long id, String username, String publicName, String imagePath, String password, UserSettings userSettings)  {
        this.id = id;
        this.username = username;
        this.publicName = publicName;
        this.userImage = encodeImage(imagePath);
        this.password = password;
        this.userSettings = userSettings;
    }

    /**
     * Image file to String
     *
     * @param imagePath     the local path of the image
     * @return              Image file as String
     */
    public String encodeImage(String imagePath) {
        if (!imagePath.equals("default")) {
            String base64 = null;
            try {
                base64 = DatatypeConverter.printBase64Binary(Files.readAllBytes(
                        Paths.get(imagePath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return base64;
        } else {
            return "default";
        }
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
}
