package model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//todo: Klaeren, ob noch gameWins and roundWins dazukommen sollen DS= ja ; Line: roundwinds ja, gamewins ergibt sich aus winner
public class GameStatistic {

    private long userId;
    private String userName;
    private long gameNumber;
    private boolean winner;
    private int roundWins;
    private int gamePoints;
    private int kills;
    private int deaths;
    private int shots;
    private int hitPoints;

    public GameStatistic() {

    }

    public GameStatistic(long gameNumber, long userId, boolean winner, int roundWins, int gamePoints, int kills, int deaths, int shots, int hitPoints) {
        this.gameNumber = gameNumber;
        this.userId = userId;
        this.winner = winner;
        this.roundWins = roundWins;
        this.gamePoints = gamePoints;
        this.kills = kills;
        this.deaths = deaths;
        this.shots = shots;
        this.hitPoints = hitPoints;
    }

    public GameStatistic(long gameNumber, long userId, boolean winner, int roundWins, int gamePoints, int kills, int deaths, int shots, int hitPoints, String userName) {
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

    public void setDefaultStatistic() {
        this.gameNumber = 2;
        this.winner = true;
        this.roundWins= 20;
        this.gamePoints = 0;
        this.kills = 4;
        this.deaths = 0;
        this.shots = 20;
        this.hitPoints = 100;
    }


    public float killDeathRate() {
        if (this.deaths > 0) {
            return this.kills / this.deaths;
        } else {
            return this.kills;
        }
    }

    public float hitRate() {
        return this.hitPoints / this.shots;
    }

    public String toJSON() {
        return "{\"userId\":"+ this.userId + ",\"deaths\":"+ this.deaths +",\"game_number\":"+ this.gameNumber +",\"game_Points\":"+this.gamePoints+
                "\"hitPoints\":"+this.hitPoints+"\",\"kills\":\""+this.kills+",\"round_wins\":"+this.roundWins+"," +
                "\",\"shots\":\""+this.shots+"\"," +
                "\"winner\":\""+this.winner+"\"}";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(long gameNumber) {
        this.gameNumber = gameNumber;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public IntegerProperty getGamePointsProperty(){
        IntegerProperty  gamePointsP = new SimpleIntegerProperty(gamePoints);
        return gamePointsP;
    }

    public StringProperty getuserNameProperty(){
        StringProperty  userNameP = new SimpleStringProperty(userName);
        return userNameP;
    }
}