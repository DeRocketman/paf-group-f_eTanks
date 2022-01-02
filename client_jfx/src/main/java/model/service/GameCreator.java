package model.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;
import model.game.logic.GameLobby;
import model.game.logic.Player;

import java.io.IOException;

public class GameCreator {

    private ObservableList<GameLobby> gameLobbyList = FXCollections.observableArrayList();
    User signedUser;

    public GameCreator() throws IOException {
        UserSettings testUserSetting = new UserSettings();
        UserStatistic testUserStatistic = new UserStatistic();

        Player testPlayer = new Player(1000, "Line-Maxi", "Rocket wo man",
                    "/Users/maximilianrupprecht/IdeaProjects/PaF_mit_Security/ETankapplicationRESTAPI/ETankRESTAPI/src/main/java/thl/gruppef/etankrest/etankrestapi/entities/default-user-image_nose.png", "passwort", testUserSetting);
        Player testPlayer2 = new Player(10001, "Maxi-Line", "Wocketwan",
                "/Users/maximilianrupprecht/IdeaProjects/PaF_mit_Security/ETankapplicationRESTAPI/ETankRESTAPI/src/main/java/thl/gruppef/etankrest/etankrestapi/entities/default-user-image_nose.png", "passwort", testUserSetting);
        this.signedUser = new Player(10002, "Mine-Laxi", "Pocketpen",
                "/Users/maximilianrupprecht/IdeaProjects/PaF_mit_Security/ETankapplicationRESTAPI/ETankRESTAPI/src/main/java/thl/gruppef/etankrest/etankrestapi/entities/default-user-image_nose.png", "passwort", testUserSetting);
        gameLobbyList.add(new GameLobby());
        gameLobbyList.add(new GameLobby());
        gameLobbyList.add(new GameLobby());
        gameLobbyList.add(new GameLobby());
        for (GameLobby gameLobby : gameLobbyList) {
           gameLobby.addPlayer(testPlayer);
        }
        gameLobbyList.get(1).addPlayer(testPlayer2);
        gameLobbyList.get(1).addPlayer(testPlayer2);
    }


    public ObservableList<GameLobby> getGameList(){
        return gameLobbyList;
    }


}
