package model.game.logic;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.game.elements.*;
import model.service.Message;
import model.service.MessageType;
import model.service.SocketClient;

public class GamePlay {
    private SocketClient socketClient;
    private ObservableList<Player> players;
    private ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    // private ObservableList<GameStatistic> gameStatistics;

    public GamePlay(ObservableList<Player> players) {
        this.players = players;

        for (Player player : players) {
            System.out.println(player.getPublicName());
        }
    }

    public GamePlay () {

    }
    public GamePlay(SocketClient socketClient) {
        this.socketClient = socketClient;

    }
    public void createGameStatistic() {
        int playerCount = players.size();
       /* for (int i = 0; i < playerCount; i++) {
            GameStatistic userGameStatistic = new GameStatistic();
            this.gameStatistics.add(userGameStatistic);
        }*/
    }

    public void setElementList(ObservableList<LevelElement> elementList) {
        this.elementList = elementList;
    }

    public int getPlayerListSize(){
        return 4;
    }

    public void receiveGameMessage(Message message) {
        if (message.getMessageType() == MessageType.GAME_ACTION) {
            System.out.println("Message in GamePlay empfangen");

        }
    }
}
