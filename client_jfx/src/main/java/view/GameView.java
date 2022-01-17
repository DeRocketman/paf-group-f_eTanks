package view;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.internal.viewloader.View;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.ETankApplication;
import model.game.elements.LevelElement;
import model.game.elements.Tank;
import model.game.logic.GamePhysics;
import model.game.logic.GamePlay;
import viewmodel.GameViewModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameView implements FxmlView<GameViewModel>, Initializable {

    @InjectViewModel
    private GameViewModel gameViewModel;

    private ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    private ObservableList<ImageView> bulletList = FXCollections.observableArrayList();

    @FXML
    private GridPane ground;
    @FXML
    private Pane elementPane;
    @FXML
    private StackPane tank1;
    @FXML
    private StackPane tank2;
    @FXML
    private StackPane tank3;
    @FXML
    private StackPane tank4;
    @FXML
    private ImageView tank1Hull;
    @FXML
    private ImageView tank1Weapon;
    @FXML
    private ImageView tank2Hull;
    @FXML
    private ImageView tank2Weapon;
    @FXML
    private ImageView tank3Hull;
    @FXML
    private ImageView tank3Weapon;
    @FXML
    private ImageView tank4Hull;
    @FXML
    private ImageView tank4Weapon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLevel();

        setElementListEventListener();
        setBulletEventListener();
        gameViewModel.setElementList(elementList);
        gameViewModel.setGamePlay(elementList);
        gameViewModel.setBulletList(bulletList);

        initTanks(gameViewModel.getGamePlay().getPlayerListSize());
        gameViewModel.startTimer();
    }

    private void initLevel() {
        //Here you can build the 3 different levels!
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 30; col++) {
                ImageView cell = new ImageView();
                cell.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../img/images/levelBackground/Ground_Tile_02_A.png"))));
                cell.setFitHeight(40.0);
                cell.setFitWidth(40.0);
                ground.add(cell, col, row);
            }
        }
    }

    public void initTanks(int playerCount) {
        // int playerCount = players.size();




        String[] imgTank = new String[4];
        imgTank[0] = "img/images/tanks/tank_01.png";
        imgTank[1] = "img/images/tanks/tank_02.png";
        imgTank[2] = "img/images/tanks/tank_03.png";
        imgTank[3] = "img/images/tanks/tank_04.png";
        double[] positionsX = {100.0, 1060.0, 100.0, 1060.0};
        double[] positionsY = {700.0, 700.0, 60.0, 60.0};

        double[] rotate = {360.0, 360.0, 180.0, 180.0};

        for (int i = 0; i < playerCount; i++) {
            LevelElement tank = new Tank(new Image(imgTank[i]), "tank", positionsX[i], positionsY[i], GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE, rotate[i], 3, i);
            tank.setVisible(true);
            elementList.add(tank);
        }
    }

    private void setElementListEventListener(){
        elementList.addListener((ListChangeListener<LevelElement>) change -> {
            if(change.next() && change.wasAdded()){
                elementPane.getChildren().add(elementList.get(change.getFrom()));
                System.out.println("elementListNew: " + change.getFrom());
            }
            if(change.wasRemoved()){
                elementPane.getChildren().remove(change.getRemoved());
                System.out.println("elementListDeleted: " + change.getFrom());
            }
        });
    }

    private void setBulletEventListener(){
        bulletList.addListener((ListChangeListener<ImageView>) change -> {
            if(change.next()){
                elementPane.getChildren().add(bulletList.get(change.getFrom()));
                System.out.println("BulletList: " + change.getFrom());
            }
        });
    }


}
