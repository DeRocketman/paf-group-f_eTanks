package model.game.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;

import java.io.IOException;

public class Player extends User {

    private boolean isReady;

    public Player(long id, String userName, String publicName, String image, String password, UserSettings userSettings) throws IOException {
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
