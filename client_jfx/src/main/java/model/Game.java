package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Game {
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

    public void drawLevel() {
        //todo: ausformulieren des Methodenrumpfes drawLevel (wirklich hier? oder in view?
    }

    public void createUserGameStatistic() {
        int playerCount = participants.size() + 1;
        for (int i = 0; i < playerCount; i++) {
            UserGameStatistic userGameStatistic = new UserGameStatistic();
            this.userGameStatistics.add(userGameStatistic);
        }
    }
}
