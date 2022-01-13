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
import model.game.logic.GamePlay;
import viewmodel.GameViewModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameView implements FxmlView<GameViewModel>, Initializable {

    @InjectViewModel
    private GameViewModel gameViewModel;
    private GamePlay gamePlay;

    private ObservableList<StackPane> elementList = FXCollections.observableArrayList();
    private ObservableList<ImageView> objectList = FXCollections.observableArrayList();
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
        initTank(4);
        gameViewModel.setElementList(elementList);
        gameViewModel.setBulletList(bulletList);
        setEventListener();
        setBulletEventListener();
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

    private void initTank(int playerCount) {

        String[] imgHull = {
                "../img/images/tanks/Hulls_Color_A/Hull_01.png",
                "../img/images/tanks/Hulls_Color_B/Hull_01.png",
                "../img/images/tanks/Hulls_Color_C/Hull_01.png",
                "../img/images/tanks/Hulls_Color_D/Hull_01.png"
        };

        String[] imgWeapon = {
                "../img/images/tanks/Weapon_Color_A/Gun_01.png",
                "../img/images/tanks/Weapon_Color_B/Gun_01.png",
                "../img/images/tanks/Weapon_Color_C/Gun_01.png",
                "../img/images/tanks/Weapon_Color_D/Gun_01.png"
        };

        double[] positionsX = {100.0, 1060.0, 100.0, 1060.0};
        double[] positionsY = {700.0, 700.0, 60.0, 60.0};

        double[] rotate = {360.0, 360.0, 180.0, 180.0};

        for(int i = 0; i < playerCount; i++){
            StackPane tank = new StackPane();
            tank.setLayoutX(positionsX[i]);
            tank.setLayoutY(positionsY[i]);
            tank.setPrefHeight(40.0);
            tank.setPrefWidth(40.0);

            ImageView tankHull = new ImageView();
            tankHull.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgHull[i]))));
            tankHull.setFitWidth(40.0);
            tankHull.setFitHeight(40.0);
            tankHull.setPickOnBounds(true);
            tankHull.setPreserveRatio(true);
            tankHull.setRotate(rotate[i]);

            ImageView tankWeapon = new ImageView();
            tankWeapon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgWeapon[i]))));
            tankWeapon.setFitWidth(40.0);
            tankWeapon.setFitHeight(40.0);
            tankWeapon.setPickOnBounds(true);
            tankWeapon.setPreserveRatio(true);
            tankWeapon.setRotate(rotate[i]);

            tank.getChildren().addAll(tankHull, tankWeapon);
            tank.setRotate(rotate[i]);

            elementPane.getChildren().add(tank);
            elementList.add(tank);
        }
    }

    private void setEventListener(){
        elementList.addListener((ListChangeListener<StackPane>) change -> {
            if(change.next()){
                elementPane.getChildren().add(elementList.get(change.getFrom()));
                System.out.println("elementList: " + change.getFrom());
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

    private void initElements() {
        //Hier sollen die übrigen starren Items an die jeweiligen Level angepasst werden
    }
}
