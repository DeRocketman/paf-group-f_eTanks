package model.data;

public class Authorisation {
    private String username;
    private String publicName;
    private String password;

    public Authorisation(String username, String publicName, String password) {
        this.username = username;
        this.publicName = publicName;
        this.password = password;
    }

    public Authorisation(String userName, String password) {
        this.username = userName;
        this.password = password;
        this.publicName = "default";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
