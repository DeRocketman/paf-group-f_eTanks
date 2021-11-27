package model.game;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.data.User;
import model.data.UserGameStatistic;

import java.sql.Timestamp;

public class Game {

    private final LongProperty gameId;
    private final StringProperty gameTimestamp;
    private IntegerProperty seatCounter;
    private ObservableList<User> host;
    private ObservableList<User> participants;
    private ObservableList<UserGameStatistic> userGameStatistics;

    public Game() {
       Timestamp gts = new Timestamp(System.currentTimeMillis());
       this.gameId = new SimpleLongProperty(gts.getTime());
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



    public LongProperty gameIdProperty() {
        return gameId;
    }


    public StringProperty gameTimestampProperty() {
        return gameTimestamp;
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

}
