package model.game.logic;
import model.data.User;
import model.data.UserSettings;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player extends User {

    private boolean isReady;

    public Player(long id, String userName, String publicName, String image, String password, UserSettings userSettings) {
        super(id, userName, publicName, image, password, userSettings);
        this.isReady = false;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    //TODO: Maybe Build service class for change to property types
    public StringProperty getPublicNameAsProperty() {
        return new SimpleStringProperty(super.getPublicName());
    }
}
