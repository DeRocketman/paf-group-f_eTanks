package model.game.logic;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import model.data.GameStatistic;
import model.game.elements.LevelElement;

import java.sql.Timestamp;

public class GamePlay {
    private ObservableList<Player> players;
    private ObservableList<GameStatistic> gameStatistics;
    private ObservableList<LevelElement> elements;

    public GamePlay(ObservableList<Player> players, ObservableList<GameStatistic> gameStatistics) {
        this.players = players;
        this.gameStatistics = gameStatistics;

    }

    public void createGameStatistic() {

        int playerCount = players.size();
        for (int i = 0; i < playerCount; i++) {
            GameStatistic userGameStatistic = new GameStatistic();
            this.gameStatistics.add(userGameStatistic);
        }
    }

    public void detectCollision() {

    }
}
