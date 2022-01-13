package model.game.logic;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import model.data.GameStatistic;
import model.game.elements.LevelElement;

import java.sql.Timestamp;

public class GamePlay {
    private ObservableList<Player> players;
    private ObservableList<GameStatistic> gameStatistics;
    private ObservableList<LevelElement> elements;
    private ObservableList<StackPane> elementList;

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


    public void setElementList(ObservableList<StackPane> elementList) {
        this.elementList = elementList;
    }
}
