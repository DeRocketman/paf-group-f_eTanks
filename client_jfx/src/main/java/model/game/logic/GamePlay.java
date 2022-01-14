package model.game.logic;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.data.GameStatistic;
import model.game.elements.LevelElement;
import model.game.elements.LevelElementImage;
import org.boon.core.Sys;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GamePlay {
    private ObservableList<Player> players;
   // private ObservableList<GameStatistic> gameStatistics;
    private ObservableList<LevelElement> elements;
    ObservableList<LevelElement> elementListNew = FXCollections.observableArrayList();
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
        initTanks();
    }

    private void initTanks() {

       // int playerCount = players.size();
        int playerCount = 4;

        String[] imgHull = {
                "../img/images/tanks/Hulls_Color_A/Hull_01.png",
                "../img/images/tanks/Hulls_Color_B/Hull_01.png",
                "../img/images/tanks/Hulls_Color_C/Hull_01.png",
                "../img/images/tanks/Hulls_Color_D/Hull_01.png"
        };

        double[] positionsX = {100.0, 1060.0, 100.0, 1060.0};
        double[] positionsY = {700.0, 700.0, 60.0, 60.0};

        double[] rotate = {360.0, 360.0, 180.0, 180.0};

        for(int i = 0; i < playerCount; i++){
            System.out.println(imgHull[i]);

            LevelElement tank = new LevelElement("tank", positionsX[i], positionsY[i], 40.0,40.0 );
            //tank.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgHull[i]))));
            tank.setPickOnBounds(true);
            tank.setPreserveRatio(true);
            tank.setRotate(rotate[i]);

            elementListNew.add(tank);

            System.out.println("Tank " + i + " angelegt.");
        }
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
}
