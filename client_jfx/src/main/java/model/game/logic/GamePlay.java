package model.game.logic;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.data.GameStatistic;
import model.game.elements.LevelElement;
import org.boon.core.Sys;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GamePlay {
    private ObservableList<Player> players;
   // private ObservableList<GameStatistic> gameStatistics;
    private ObservableList<LevelElement> elements;
    //private ObservableList<StackPane> elementList;
    static int timerCounter = 0;

    public GamePlay(ObservableList<Player> players) {
        this.players = players;

        for (Player player : players) {
           System.out.println(player.getPublicName());
        }
       // this.gameStatistics = gameStatistics;
       //  runTimer();
        //  runThreads();
    }
    public GamePlay(){
        System.out.println(
                "Test"
        );
    }

    public void createGameStatistic() {
        int playerCount = players.size();
       /* for (int i = 0; i < playerCount; i++) {
            GameStatistic userGameStatistic = new GameStatistic();
            this.gameStatistics.add(userGameStatistic);
        }*/
    }

    public void detectCollision() {

    }
/*
    public void setElementList(ObservableList<StackPane> elementList) {
        this.elementList = elementList;
    }*/
}
