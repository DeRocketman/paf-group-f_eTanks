package model.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;
import model.game.logic.GameLobby;

public class GameCreator {

    private ObservableList<GameLobby> gameLobbyList = FXCollections.observableArrayList();
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
        gameLobbyList.add(new GameLobby());
        gameLobbyList.add(new GameLobby());
        for (GameLobby gameLobby : gameLobbyList) {
            gameLobby.addHost(testUser1);
            gameLobby.addParticipants(testUser);
        }
        gameLobbyList.get(1).addParticipants(testUser);
        gameLobbyList.get(1).addParticipants(testUser);
    }


    public ObservableList<GameLobby> getGameList(){
        return gameLobbyList;
    }

    public User getSignedUser(){
        return signedUser;
    }
}
