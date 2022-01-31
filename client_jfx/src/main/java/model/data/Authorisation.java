package model.data;

public class Authorisation {
    private String username;
    private String publicName;
    private String password;

    /**
     * constructor of the class
     *
     * @param username      Username of the user
     * @param publicName    public name of the user
     * @param password      password of the user
     */
    public Authorisation(String username, String publicName, String password) {
        this.username = username;
        this.publicName = publicName;
        this.password = password;
    }

    /**
     * constructor of the class without publicName
     *
     * @param username      Username of the user
     * @param password      password of the user
     */
    public Authorisation(String username, String password) {
        this.username = username;
        this.password = password;
        this.publicName = "default";
    }

    public String getUsername() {
        return username;
    }
}
