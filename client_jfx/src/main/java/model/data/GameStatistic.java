package model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameStatistic {

    private long userId;
    private String userName;
    private String gameNumber;
    private boolean winner;
    private int roundWins;
    private int gamePoints;
    private int kills;
    private int deaths;
    private int shots;
    private int hitPoints;

    /**
     * Constructor of the class GameStatistic
     *
     * @param gameNumber    the game number
     * @param userId        the id of the user
     * @param winner        if the user was winner of the game
     * @param roundWins     how many rounds the user won
     * @param gamePoints    total gamePoints
     * @param kills         kills of the user
     * @param deaths        deaths of the user
     * @param shots         shots of the user
     * @param hitPoints     total hitpoints
     * @param userName      public name of the user
     */
    public GameStatistic(String gameNumber, long userId, boolean winner, int roundWins, int gamePoints, int kills, int deaths, int shots, int hitPoints, String userName) {
        this.gameNumber = gameNumber;
        this.userId = userId;
        this.winner = winner;
        this.roundWins = roundWins;
        this.gamePoints = gamePoints;
        this.kills = kills;
        this.deaths = deaths;
        this.shots = shots;
        this.hitPoints = hitPoints;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getRoundWins() {
        return roundWins;
    }

    public void setRoundWins(int roundWins) {
        this.roundWins = roundWins;
    }

    public int getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public IntegerProperty getGamePointsProperty(){
        return new SimpleIntegerProperty(gamePoints);
    }

    public StringProperty getUserNameProperty(){
        return new SimpleStringProperty(userName);
    }
}