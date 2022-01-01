package model.data;

//todo: Klaeren, ob noch gameWins and roundWins dazukommen sollen DS= ja ; Line: roundwinds ja, gamewins ergibt sich aus winner
public class GameStatistic {
    private User user;
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

    public GameStatistic(long gameNumber, User  user, boolean winner, int roundWins, int gamePoints, int kills, int deaths, int shots, int hitPoints) {
        this.gameNumber = gameNumber;
        this.user = user;
        this.winner = winner;
        this.roundWins = roundWins;
        this.gamePoints = gamePoints;
        this.kills = kills;
        this.deaths = deaths;
        this.shots = shots;
        this.hitPoints = hitPoints;
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
        return "{\"deaths\":"+ this.deaths +",\"game_number\":"+ this.gameNumber +",\"game_Points\":"+this.gamePoints+
                "\"hitPoints\":"+this.hitPoints+"\",\"kills\":\""+this.kills+",\"round_wins\":"+this.roundWins+"," +
                "\",\"shots\":\""+this.shots+"\"," +
                "\"winner\":\""+this.winner+"\"}";
    }

}