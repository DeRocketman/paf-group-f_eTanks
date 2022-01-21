package model.game.logic;

import controller.GameLobbyViewController;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.data.User;
import model.data.GameStatistic;

import java.io.Serializable;
import java.sql.Timestamp;

public class GameLobby implements Serializable {

    private GameLobbyViewController controller;

    private final StringProperty gameLobbyID;
    private IntegerProperty seatCounter;
    private ObservableList<Player> playerList;

    //public static ObservableList<GameLobby> lobbyList;

    public GameLobby() {
       this.playerList = FXCollections.observableArrayList();
       this.gameLobbyID = new SimpleStringProperty(String.valueOf(buildLobbyID()));
       this.seatCounter = new SimpleIntegerProperty(playerList.size());
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        setSeatCounter(getSeatCounter()+1);
    }

    public void removePlayer(Player player) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getId() == player.getId()) {
                playerList.remove(i);
                break;
            }
        }
    }

    public long buildLobbyID() {
        long time = System.currentTimeMillis();
        long random = (long) (Math.random()*8001+1000);
        return time*random;
    }

    public int getSeatCounter() {
        return seatCounter.get();
    }

    public IntegerProperty seatCounterProperty() {
        return seatCounter;
    }

    public void setSeatCounter(int seatCounter) {
        this.seatCounter.set(seatCounter);
    }

    public String getGameLobbyID() {
        return gameLobbyID.get();
    }

    public StringProperty gameLobbyIDProperty() {
        return gameLobbyID;
    }

    public ObservableList<Player> getPlayers() {
        return playerList;
    }

}
