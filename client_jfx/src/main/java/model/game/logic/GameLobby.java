package model.game.logic;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class GameLobby implements Serializable {

    private final StringProperty gameLobbyID;
    private IntegerProperty seatCounter;
    private ObservableList<Player> playerList;

    public GameLobby() {
       this.playerList = FXCollections.observableArrayList();
       this.gameLobbyID = new SimpleStringProperty(String.valueOf(buildLobbyID()));
       this.seatCounter = new SimpleIntegerProperty(playerList.size());
    }

    /**
     * Adds a player to the player list of the lobby
     *
     * @param player    new player
     */
    public void addPlayer(Player player) {
        playerList.add(player);
        setSeatCounter(getSeatCounter()+1);
    }

    /**
     * Removes player of the player list
     *
     * @param player    player to remove
     */
    public void removePlayer(Player player) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getId() == player.getId()) {
                playerList.remove(i);
                break;
            }
        }
    }

    /**
     * Builds the random lobbyID
     *
     * @return  lobbyID
     */
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

    public void setGameLobbyID(String gameLobbyID) {
        this.gameLobbyID.set(gameLobbyID);
    }
}
