package model.data;

//todo: Klaeren, ob noch gameWins and roundWins dazukommen sollen DS= ja
public class GameStatistic {
    private User user;
    private long gameNumber;
    private int gamePoints;
    private int kills;
    private int deaths;
    private int shots;
    private int hitPoints;
    private float killDeathRate;
    private float hitRate;
    private boolean winner;

    public GameStatistic() {

    }

    public GameStatistic(int gamePoints, int playedGames, int gameWins, int roundWins, int kills, int deaths, int shots, int hitPoints) {
        this.gamePoints = gamePoints;
        this.kills = kills;
        this.deaths = deaths;
        this.shots = shots;
        this.hitPoints = hitPoints;
        this.killDeathRate = killDeathRate();
        this.hitRate = hitRate();
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


}
