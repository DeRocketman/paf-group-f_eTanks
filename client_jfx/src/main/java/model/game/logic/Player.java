package model.game.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;

public class Player extends User {

    private boolean isReady;

    public Player(long id, String userName, String publicName, String image, String password, UserSettings userSettings, UserStatistic userStatistic) {
        super(id, userName, publicName, image, password, userSettings, userStatistic);
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
