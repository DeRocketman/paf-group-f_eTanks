package model.game.logic;

import controller.GameLobbyViewController;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.data.User;
import model.data.GameStatistic;

import java.sql.Timestamp;

public class GameLobby {

    private static final int PORT = 9001;
    private GameLobbyViewController controller;

    private final LongProperty gameLobbyID;
    private IntegerProperty seatCounter;
    private ObservableList<Player> players;

    public GameLobby() {
       this.players = FXCollections.observableArrayList();
       this.gameLobbyID = new SimpleLongProperty(buildLobbyID());
       this.seatCounter = new SimpleIntegerProperty(players.size());
    }

    public void addPlayer(Player player) {
        players.add(player);
        setSeatCounter(getSeatCounter()+1);
    }

    public void removePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == player.getId()) {
                players.remove(i);
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

    public long getGameLobbyID() {
        return gameLobbyID.get();
    }

    public LongProperty gameLobbyIDProperty() {
        return gameLobbyID;
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }
}
