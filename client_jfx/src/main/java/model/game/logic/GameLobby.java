package model.game.logic;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.data.User;
import model.data.GameStatistic;

import java.sql.Timestamp;

public class GameLobby {
    private IntegerProperty gameCounterID;
    private IntegerProperty seatCounter;
    private ObservableList<Player> players;

    public GameLobby() {
       this.players = FXCollections.observableArrayList();
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
    public int getSeatCounter() {
        return seatCounter.get();
    }

    public IntegerProperty seatCounterProperty() {
        return seatCounter;
    }

    public void setSeatCounter(int seatCounter) {
        this.seatCounter.set(seatCounter);
    }

}
