package model;

public class UserGameStatistic {
    private int gamePoints;
    private int kills;
    private int deaths;
    private int shots;
    private int hitPoints;
    private float killDeathRate;
    private float hitRate;

    public UserGameStatistic(int gamePoints, int kills, int deaths, int shots, int hitPoints) {
        this.gamePoints = gamePoints;
        this.kills = kills;
        this.deaths = deaths;
        this.shots = shots;
        this.hitPoints = hitPoints;
    }

    public void killDeathRate() {
        if (this.deaths > 0) {
            this.killDeathRate = this.kills/this.deaths;
        } else {
            this.killDeathRate= this.kills;
        }
    }

    public void hitRate() {
        this.hitRate = hitPoints/shots;
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

    public float getKillDeathRate() {
        return killDeathRate;
    }

    public float getHitRate() {
        return hitRate;
    }
}
