package viewmodel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.ETankApplication;
import model.game.elements.LevelElement;
import model.game.elements.LevelElementImage;
import model.game.logic.GameLobby;
import model.game.logic.GamePhysics;
import model.game.logic.GamePlay;
import view.GameView;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Objects;

public class GameViewModel implements ViewModel {
    ETankApplication eTankApplication;
    GameLobby gameLobby;
    GamePlay gamePlay;

    int wichTank = 3;

    ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    ObservableList<ImageView> bulletList = FXCollections.observableArrayList();

    public void setElementList(ObservableList<StackPane> elementList) {
        this.elementList = elementList;
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void setGame(GameLobby gameLobby) {
        this.gameLobby = gameLobby;
    }

    public void setGamePlay(ObservableList<LevelElement> elementList){
        this.gamePlay = new GamePlay();
        //this.gamePlay = new GamePlay(eTankApplication.getPlayerlist());
        //this.gamePlay = eTankApplication.getGamePlay();
    }

    public GamePlay getGamePlay(){
        return this.gamePlay;
    }

    public void setBulletList(ObservableList<ImageView> bulletList) {
        this.bulletList = bulletList;
    }

    public void createElement (){
        LevelElementImage test = new LevelElementImage( new Image(getClass().getResourceAsStream("../img/images/tanks/Hulls_Color_A/Hull_01.png")) , "tank", 200.0, 200.0, 40.0,40.0 );
        StackPane tank = new StackPane();
        tank.setLayoutX(200.0);
        tank.setLayoutY(200.0);
        tank.setPrefHeight(40.0);
        tank.setPrefWidth(40.0);

        tank.getChildren().add(test);

        System.out.println(test.getType());

        elementList.add(tank);
    }
}
