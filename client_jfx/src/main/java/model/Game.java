package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Game {
    /* todo: Uncomment, wenn StringPropertys nicht den gewünschten Effekt bringen! (Observable)
    private final int id;
    private final Timestamp gameTimestamp;
    private final User host;
    private final ArrayList<User> participants;
    private ArrayList<UserGameStatistic> userGameStatistics;

    public Game(int id, User host, ArrayList<User> participants) {
        this.id = id;
        this.host = host;
        this.participants = participants;
        this.gameTimestamp = new Timestamp(System.currentTimeMillis());
        drawLevel();
        createUserGameStatistic();
    }
     */

    private final IntegerProperty gameId;
    private final StringProperty gameTimestamp;
    private IntegerProperty seatCounter;
    private ObservableList<User> host;
    private ObservableList<User> participants;
    private ObservableList<UserGameStatistic> userGameStatistics;


    public Game(int gameId) {
       this.gameId = new SimpleIntegerProperty(gameId);
       Timestamp gts = new Timestamp(System.currentTimeMillis());
       this.gameTimestamp = new SimpleStringProperty(gts.toString());
       this.host = FXCollections.observableArrayList();
       this.participants = FXCollections.observableArrayList();
       this.userGameStatistics = FXCollections.observableArrayList();
       this.seatCounter = new SimpleIntegerProperty(host.size()+participants.size());
       createUserGameStatistic();
    }

    public void createUserGameStatistic() {
        int playerCount = participants.size() + 1;
        for (int i = 0; i < playerCount; i++) {
            UserGameStatistic userGameStatistic = new UserGameStatistic();
            this.userGameStatistics.add(userGameStatistic);
        }
    }

    public int getGameId() {
        return gameId.get();
    }

    public IntegerProperty gameIdProperty() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId.set(gameId);
    }

    public String getGameTimestamp() {
        return gameTimestamp.get();
    }

    public StringProperty gameTimestampProperty() {
        return gameTimestamp;
    }

    public void setGameTimestamp(String gameTimestamp) {
        this.gameTimestamp.set(gameTimestamp);
    }

    public ObservableList<User> getHost() {
        return host;
    }

    public void addHost(User user) {
        host.add(user);
        setSeatCounter(getSeatCounter()+1);
    }

    public ObservableList<User> getParticipants() {
        return participants;
    }

    public void addParticipants(User user) {
        participants.add(user);
        setSeatCounter(getSeatCounter()+1);
    }

    public ObservableList<UserGameStatistic> getUserGameStatistics() {
        return userGameStatistics;
    }

    public void setUserGameStatistics(ObservableList<UserGameStatistic> userGameStatistics) {
        this.userGameStatistics = userGameStatistics;
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

    public void drawLevel() {
        
    }

}
