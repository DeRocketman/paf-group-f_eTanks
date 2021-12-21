package model.data;

import java.util.ArrayList;

public class UserStatistic {

    private ArrayList<GameStatistic> gameStatistics;

    private long id;

    private int deaths;
    private int gamePoints;
    private int gameWins;
    private int hitPoints;
    private int hitRate;
    private int killDeathRate;
    private int kills;
    private int playedGames;
    private int roundWins;
    private int shots;

    public UserStatistic() {
    }

    /**
     * Default
     */
    public void setDefaultStatistic() {
        this.id = 0;
        this.deaths = 0;
        this.gamePoints= 0;
        this.gameWins = 0;
        this.roundWins = 0;
        this.hitPoints = 0;
        this.hitRate = 0;
        this.killDeathRate = 0;
        this.shots = 0;
        this.kills = 0;
        this.playedGames = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    public int getGameWins() {
        return gameWins;
    }

    public void setGameWins(int gameWins) {
        this.gameWins = gameWins;
    }

    public int getRoundWins() {
        return roundWins;
    }

    public void setRoundWins(int roundWins) {
        this.roundWins = roundWins;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getHitRate() {
        return hitRate;
    }

    public void setHitRate(int hitRate) {
        this.hitRate = hitRate;
    }

    public int getKillDeathRate() {
        return killDeathRate;
    }

    public void setKillDeathRate(int killDeathRate) {
        this.killDeathRate = killDeathRate;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public String toJSON() {
        return "{\"id\":"+ this.id +",\"deaths\":"+ this.deaths +",\"gamePoints\":"+this.gamePoints+",\"gameWins\":"+this.gameWins+"," +
                "\"hitPoints\":"+this.hitPoints+",\"hitRate\":\""+this.hitRate+"\",\"killDeathRate\":\""+this.killDeathRate+
                "\",\"kills\":\""+this.kills+"\",\"playedGames\":\""+this.playedGames+"\"," +
                "\"roundWins\":\""+this.roundWins+"\",\"shots\":\""+this.shots+"\"}";
    }
}
