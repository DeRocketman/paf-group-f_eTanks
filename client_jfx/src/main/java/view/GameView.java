package view;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.ETankApplication;
import model.game.elements.*;
import model.game.logic.GamePhysics;
import viewmodel.GameViewModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameView implements FxmlView<GameViewModel>, Initializable {

    @InjectViewModel
    private GameViewModel gameViewModel;

    private ETankApplication eTankApplication;

    private ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    private ObservableList<ImageView> bulletList = FXCollections.observableArrayList();

    @FXML
    private GridPane ground;
    @FXML
    private Pane elementPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLevel();

        setElementListEventListener();
        gameViewModel.setGameView(this);
        gameViewModel.setElementList(elementList);
        gameViewModel.setETankApplication(eTankApplication);
        gameViewModel.setGamePlay(elementList);

        initTanks(gameViewModel.getGamePlay().getPlayerListSize());

       // initBorderElements();
        initElements();
        initWorldBorder();

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

    public void createMainBullet(LevelElement myTank, double[] bsp) {
        LevelElement bullet = new BulletMainWeapon(bsp[0], bsp[1], 6, 12, myTank.getRotate(), (Tank) myTank);
        gameViewModel.moveBullet((BulletMainWeapon) bullet);
        elementList.add(bullet);
    }

    private void initTanks(int playerCount) {
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
            elementList.add(new Tank(new Image(imgTank[i]), positionsX[i], positionsY[i], 27.0, GamePhysics.ELEMENT_SIZE, rotate[i], i));
        }
    }
/*
    private void initBorderElements() {
        int posX = 0;
        int posY = 0;
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 30; col++) {
                if( row == 0 ){
                    LevelElement block = new Block(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../img/images/buildings/crateMetal.png"))), "blockMetal", posX, posY, 40, 40, 0.0, true, 1000000);
                    block.setFitHeight(40.0);
                    block.setFitWidth(40.0);
                    elementList.add(block);
                    posX += 40;
                    //System.out.println(posX);
                    if (col == 29) {
                        posX = 0;
                    }
                } else if(row > 0 && row < 19){
                   if (col == 0){
                       LevelElement block = new Block(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../img/images/buildings/crateMetal.png"))), "blockMetal", 0, posY, 40, 40, 0.0, true, 1000000);
                       block.setFitHeight(40.0);
                       block.setFitWidth(40.0);
                       elementList.add(block);
                     //  System.out.println(posX + " "+  posY);
                   } else if(col == 19){
                       LevelElement block = new Block(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../img/images/buildings/crateMetal.png"))), "blockMetal", 1160, posY, 40, 40, 0.0, true, 1000000);
                       block.setFitHeight(40.0);
                       block.setFitWidth(40.0);
                       elementList.add(block);
                   }
                }else if(row == 19){
                    posY = 760;
                    LevelElement block = new Block(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../img/images/buildings/crateMetal.png"))), "blockMetal", posX, posY, 40, 40, 0.0, true, 1000000);
                    block.setFitHeight(40.0);
                    block.setFitWidth(40.0);
                    elementList.add(block);
                  //  System.out.println(posX + " " + posY);
                    posX += 40;
                }
            }       posY += 40;
        }

    }*/

    //eine kürzere Alternative für die Border Blocks?
    public void initWorldBorder() {
        //Breite
        for (double i = 0; i < GamePhysics.GAME_WIDTH-80; i+=40 ){
            createMetalBlock( i,0);
            createMetalBlock( i,GamePhysics.GAME_HEIGHT-40);
        }
        //Höhe
        for (double i = 40; i < GamePhysics.GAME_HEIGHT-40; i+=40 ){
            createMetalBlock(0,i);
            createMetalBlock( GamePhysics.GAME_WIDTH-120, i);
        }
    }

    public void createStoneBlock(double x, double y, double rotation){
        String blockImg = "img/images/blocks/Block_A_01.png";
        LevelElement block = new Block(new Image(blockImg), LevelElementType.BLOCK_STONE, x, y, 64, 32, rotation);
        block.setVisible(true);
        elementList.add(block);
    }

    public void createMetalBlock(double x, double y){
        String blockImg = "img/images/buildings/crateMetal.png";
        LevelElement block = new Block(new Image(blockImg), LevelElementType.BLOCK_METAL, x, y, GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE);
        block.setVisible(true);
        elementList.add(block);
    }

    public void createWoodBlock(double x, double y){
        String blockImg = "img/images/buildings/crateWood.png";
        LevelElement block = new Block(new Image(blockImg), LevelElementType.BLOCK_WOOD, x, y, GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE);
        block.setVisible(true);
        elementList.add(block);
    }


    private void initElements(){

        //MetalBlocks

        // WoodenBlocks

    }

    private void setElementListEventListener() {
        elementList.addListener((ListChangeListener<LevelElement>) change -> {
            if (change.next() && change.wasAdded()) {
                elementPane.getChildren().addAll(elementList.get(change.getFrom()));
               // System.out.println("elementListNew: " + change.getFrom());
            }
            if (change.wasRemoved()) {
                elementPane.getChildren().remove(change.getRemoved().get(0));
              //  System.out.println("elementListDeleted: " + change.getFrom());
            }
        });
    }

    /*public void seteTankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }*/
}
