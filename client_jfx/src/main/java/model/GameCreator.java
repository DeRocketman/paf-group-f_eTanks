package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GameCreator {

    private ObservableList<Game> gameList = FXCollections.observableArrayList();
    User signedUser;

    public GameCreator() {
        UserSettings testUserSetting = new UserSettings();
        UserStatistic testUserStatistic = new UserStatistic();
        User testUser1 = new User(1000, "Line-Maxi", "Rocket wo man",
                    "../img/images/default-user-image.png", "passwort", testUserSetting, testUserStatistic);
        User testUser = new User(10001, "Maxi-Line", "Wocketwan",
                "../img/images/default-user-image.png", "passwort", testUserSetting, testUserStatistic);
        this.signedUser = new User(10002, "Mine-Laxi", "Pocketpen",
                "../img/images/default-user-image.png", "passwort", testUserSetting, testUserStatistic);
        gameList.add(new Game(100000));
        gameList.add(new Game(100001));
        for (Game game: gameList) {
            game.addHost(testUser1);
            game.addParticipants(testUser);
        }
        gameList.get(1).addParticipants(testUser);
        gameList.get(1).addParticipants(testUser);
    }


    public ObservableList<Game> getGameList(){
        return gameList;
    }

    public User getSignedUser(){
        return signedUser;
    }

}
