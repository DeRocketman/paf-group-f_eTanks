package view;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.data.GameStatistic;
import model.game.elements.*;
import model.game.logic.GamePhysics;
import model.game.logic.Player;
import org.boon.core.Sys;
import viewmodel.GameViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameView implements FxmlView<GameViewModel>, Initializable {

    @InjectViewModel
    private GameViewModel gameViewModel;

    @FXML
    private GridPane ground;

    @FXML
    private Pane elementPane;

    @FXML
    private Label level_no;

    @FXML
    private Label time;

    @FXML
    private Label player_1;
    @FXML
    private Label player_2;
    @FXML
    private Label player_3;
    @FXML
    private Label player_4;

    @FXML
    private Label player_1_wins;
    @FXML
    private Label player_2_wins;
    @FXML
    private Label player_3_wins;
    @FXML
    private Label player_4_wins;

    @FXML
    private Label stat_player_1_name;
    @FXML
    private Label stat_player_2_name;
    @FXML
    private Label stat_player_3_name;
    @FXML
    private Label stat_player_4_name;

    @FXML
    private Label player_1_deaths;
    @FXML
    private Label player_2_deaths;
    @FXML
    private Label player_3_deaths;
    @FXML
    private Label player_4_deaths;

    @FXML
    private Label player_1_shots;
    @FXML
    private Label player_2_shots;
    @FXML
    private Label player_3_shots;
    @FXML
    private Label player_4_shots;

    @FXML
    private Label player_1_kills;
    @FXML
    private Label player_2_kills;
    @FXML
    private Label player_3_kills;
    @FXML
    private Label player_4_kills;

    @FXML
    private Label kills_3;
    @FXML
    private Label deaths_3;
    @FXML
    private Label shots_3;
    @FXML
    private Label kills_4;
    @FXML
    private Label deaths_4;
    @FXML
    private Label shots_4;

    @FXML
    private Label player_3_lives_text;
    @FXML
    private Label player_4_lives_text;

    @FXML
    private Label player_1_lives;
    @FXML
    private Label player_2_lives;
    @FXML
    private Label player_3_lives;
    @FXML
    private Label player_4_lives;

    private ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    public ImageView display;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initBackground(0);
        level_no.setText("1");
        setElementListEventListener();
        gameViewModel.setGameView(this);
        gameViewModel.setElementList(elementList);
    }

    /**
     * Initialize the next level
     */
    public void initNextLevel(int level){
        System.out.println("in initNextLevel");
        elementPane.getChildren().clear();
        elementPane.getChildren().add(ground);
        initBackground(level);
        initDisplay();
        level_no.setText(String.valueOf(level+1));
        gameViewModel.startGame();
    }

    /**
     * Creates the background for the game Pane
     */
    private void initBackground(int level) {

        String[] background = new String[4];
        background[0] = "../img/images/levelBackground/Ground_Tile_02_A.png";
        background[1] = "../img/images/levelBackground/Ground_Tile_01_C.png";
        background[2] = "../img/images/levelBackground/Ground_Tile_02_C.png";
        background[3] = "../img/images/levelBackground/Ground_Tile_01_B.png";

        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 30; col++) {
                ImageView cell = new ImageView();
                cell.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(background[level]))));
                cell.setFitHeight(40.0);
                cell.setFitWidth(40.0);
                ground.add(cell, col, row);
            }
        }
    }

    /**
     * Creates the ImageView of a Bullet and sets the firing Tank
     *
     * @param myTank    the firing Tank
     * @param position  of the ImageView
     */
    public void createMainBullet(LevelElement myTank, double[] position) {
        BulletMainWeapon bullet = new BulletMainWeapon(position[0], position[1], 6, 12, myTank.getRotate(), (Tank) myTank);
        gameViewModel.moveBullet(bullet);
        elementList.add(bullet);
    }

    /**
     * Creates a number of tanks by playerlist size
     *
     * @param playerCount   size of playerlist
     */
    public void initTanks(int playerCount) {
        String[] imgTank = new String[4];
        imgTank[0] = "img/images/tanks/tank_01.png";
        imgTank[1] = "img/images/tanks/tank_02.png";
        imgTank[2] = "img/images/tanks/tank_03.png";
        imgTank[3] = "img/images/tanks/tank_04.png";
        double[] positionsX = {100.0, 1060.0, 100.0, 1060.0};
        double[] positionsY = {700.0, 700.0, 60.0, 60.0};

        double[] rotate = {360.0, 360.0, 180.0, 180.0};

        for (int i = 0; i < playerCount; i++) {
            elementList.add(new Tank(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imgTank[i]))), positionsX[i], positionsY[i], GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE, rotate[i], i));
        }
        gameViewModel.setWhichTank();
        System.out.println("in initTanks.GameView");
        gameViewModel.initTankList();
    }

    /**
     * Creates the World Border
     */
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

    /**
     * Creates a stone block LevelElement
     *
     * @param x     x position
     * @param y     y position
     * @param rotation  of the Image
     */
    public void createStoneBlock(double x, double y, double rotation){
        String blockImg = "img/images/blocks/Block_A_01.png";
        LevelElement block = new Block(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(blockImg))), LevelElementType.BLOCK_STONE, x, y, 64, 32, rotation);
        block.setVisible(true);
        elementList.add(block);
    }

    /**
     * Creates a metal block LevelElement
     *
     * @param x     x position
     * @param y     y position
     */
    public void createMetalBlock(double x, double y){
        String blockImg = "img/images/buildings/crateMetal.png";
        LevelElement block = new Block(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(blockImg))), LevelElementType.BLOCK_METAL, x, y, GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE);
        block.setVisible(true);
        elementList.add(block);
    }

    /**
     * Creates a wooden block LevelElement
     *
     * @param x     x position
     * @param y     y position
     */
    public void createWoodBlock(double x, double y){
        String blockImg = "img/images/buildings/crateWood.png";
        LevelElement block = new Block(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(blockImg))), LevelElementType.BLOCK_WOOD, x, y, GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE);
        block.setVisible(true);
        elementList.add(block);
    }

    /**
     * Creates the Elements of the level
     */
    public void initElements(){

        //Metal Blocks
        createMetalBlock(80, 360);
        createMetalBlock(80, 400);
        createMetalBlock(1080, 360);
        createMetalBlock(1080, 400);
        for(double i = 40; i < 240; i += 40){
            createMetalBlock(320, i);
            createMetalBlock(GamePhysics.GAME_WIDTH-440, i);
        }
        for(double i = GamePhysics.GAME_HEIGHT+40; i > 520; i-=40){
            createMetalBlock(320,i);
            createMetalBlock(GamePhysics.GAME_WIDTH-440, i);
        }

        //Stone Blocks
        for(int i = 120; i < 480; i += 64){
            createStoneBlock(i,GamePhysics.GAME_HEIGHT*0.5-20, 0);
        }
        for(int i = (int) (GamePhysics.GAME_WIDTH - 260); i > 684; i-=64){
            createStoneBlock(i,GamePhysics.GAME_HEIGHT*0.5-16, 0);
        }

        //Wood Blocks
        for(double i = 120; i < 320; i += 40){
            createWoodBlock(480, i);
            createWoodBlock(GamePhysics.GAME_WIDTH-600, i);
        }
        for(double i = GamePhysics.GAME_HEIGHT-160; i > 440; i -= 40){
            createWoodBlock(480, i);
            createWoodBlock(GamePhysics.GAME_WIDTH-600 , i);
        }

        for(double i = 520; i < 680; i += 40){
            createWoodBlock(i, 280);
        }
        for(double i = 520; i < 680; i += 40){
            createWoodBlock(i, 480);
        }
    }

    public void initDisplay(){
        display = new ImageView();
        display.setFitHeight(400);
        display.setFitWidth(600);
        display.setLayoutX(300);
        display.setLayoutY(200);
        elementPane.getChildren().add(display);
    }

    /**
     * Sets the event listener for the elementList
     * and adds or removes elements
     */
    private void setElementListEventListener() {
        elementList.addListener((ListChangeListener<LevelElement>) change -> {
            if (change.next() && change.wasAdded()) {
                elementPane.getChildren().addAll(elementList.get(change.getFrom()));
            }
            if (change.wasRemoved()) {
                elementPane.getChildren().remove(change.getRemoved().get(0));
            }
        });
    }

    /**
     * Sets the text of the playerName and statistic Labels
     */
    public void setPlayerText(ObservableList<Player> playerList){
        int playerCount = playerList.size() ;

        if(playerCount >= 1){
            stat_player_1_name.setText(playerList.get(0).getPublicName());
            player_1.setText(playerList.get(0).getPublicName() + ": ");
            if(playerCount == 1){
                deaths_3.setVisible(false);
                kills_3.setVisible(false);
                shots_3.setVisible(false);
                player_3_lives_text.setVisible(false);

                deaths_4.setVisible(false);
                kills_4.setVisible(false);
                shots_4.setVisible(false);
                player_4_lives_text.setVisible(false);
            }
        }
        if(playerCount >= 2){
            stat_player_1_name.setText(playerList.get(0).getPublicName());
            player_1.setText(playerList.get(0).getPublicName() + ": ");

            stat_player_2_name.setText(playerList.get(1).getPublicName());
            player_2.setText(playerList.get(1).getPublicName() + ": ");
            if(playerCount == 2){
                deaths_3.setVisible(false);
                kills_3.setVisible(false);
                shots_3.setVisible(false);
                player_3_lives_text.setVisible(false);

                deaths_4.setVisible(false);
                kills_4.setVisible(false);
                shots_4.setVisible(false);
                player_4_lives_text.setVisible(false);
            }
        }
        if(playerCount >= 3){
            stat_player_3_name.setText(playerList.get(2).getPublicName());
            player_3.setText(playerList.get(2).getPublicName() + ": ");

            if(playerCount == 3){
                deaths_4.setVisible(false);
                kills_4.setVisible(false);
                shots_4.setVisible(false);
                player_4_lives_text.setVisible(false);
            }
        }
        if(playerCount == 4){
            stat_player_4_name.setText(playerList.get(3).getPublicName());
            player_4.setText(playerList.get(3).getPublicName() + ": ");
        }
    }

    /**
     * Sets the text of the player wins
     * @param gameStatistics    List of GameStatistic Objects
     */
    public void setPlayerWins(List<GameStatistic> gameStatistics){
        int playerCount = gameStatistics.size();

        switch (playerCount){
            case 4:
                player_4_wins.setText(String.valueOf(gameStatistics.get(3).getRoundWins()));
            case 3:
                player_3_wins.setText(String.valueOf(gameStatistics.get(2).getRoundWins()));
            case 2:
                player_2_wins.setText(String.valueOf(gameStatistics.get(1).getRoundWins()));
                player_1_wins.setText(String.valueOf(gameStatistics.get(0).getRoundWins()));

                //Case 1 später löschen
            case 1:
                player_1_wins.setText(String.valueOf(gameStatistics.get(0).getRoundWins()));
        }
    }

    /**
     * Updates the text of the shown game statistics
     *
     * @param gameStatistics    List of GameStatistic Objects
     */
    public void updateStatisticText(List<GameStatistic> gameStatistics){
        int playerCount = gameStatistics.size();

        if(playerCount >= 1){
            player_1_kills.setText(String.valueOf(gameStatistics.get(0).getKills()));
            player_1_shots.setText(String.valueOf(gameStatistics.get(0).getShots()));
            player_1_deaths.setText(String.valueOf(gameStatistics.get(0).getDeaths()));
        }
        if(playerCount >= 2){
            player_2_kills.setText(String.valueOf(gameStatistics.get(1).getKills()));
            player_2_shots.setText(String.valueOf(gameStatistics.get(1).getShots()));
            player_2_deaths.setText(String.valueOf(gameStatistics.get(1).getDeaths()));
        }
        if(playerCount >= 3){
            player_3_kills.setText(String.valueOf(gameStatistics.get(2).getKills()));
            player_3_shots.setText(String.valueOf(gameStatistics.get(2).getShots()));
            player_3_deaths.setText(String.valueOf(gameStatistics.get(2).getDeaths()));
        }
        if(playerCount == 4){
            player_4_kills.setText(String.valueOf(gameStatistics.get(3).getKills()));
            player_4_shots.setText(String.valueOf(gameStatistics.get(3).getShots()));
            player_4_deaths.setText(String.valueOf(gameStatistics.get(3).getDeaths()));
        }
    }

    /**
     * Sets the player lives text labels
     *
     * @param playerLives   array of the playerLives
     */
    public void setPlayerLives(int[] playerLives){
        int playerCount = playerLives.length;
        player_1_lives.setText(String.valueOf(playerLives[0]));
        player_2_lives.setText(String.valueOf(playerLives[1]));
        if(playerCount >= 3){
            player_3_lives.setText(String.valueOf(playerLives[2]));
        }
        if (playerCount == 4){
            player_4_lives.setText(String.valueOf(playerLives[3]));
        }
    }

    /**
     * Updates the timer text
     *
     * @param time  remaining roundtime
     */
    public void updateTimer(double time){
        this.time.setText(String.valueOf((int) time));
    }

    /**
     * Shows a message during the game
     *
     * @param text  message text
     */
    public void setGameMessage(String text, int winner){
        Text winnerText = new Text();
        winnerText.setText(text);
        winnerText.setFont(new Font("Stencil", 56));
        winnerText.setY(GamePhysics.GAME_HEIGHT / 2 - 40);
        winnerText.setX((GamePhysics.GAME_WIDTH / 2) - (winnerText.getLayoutBounds().getWidth() / 2));
        winnerText.setTextOrigin(VPos.CENTER);
        winnerText.toFront();

        ImageView winnerTank = new ImageView();
        winnerTank.setImage(elementList.get(winner).getImage());
        winnerTank.setFitHeight(100);
        winnerTank.setFitWidth(100);
        winnerTank.setLayoutX(GamePhysics.GAME_WIDTH / 2 - winnerTank.getFitWidth() / 2 );
        winnerTank.setLayoutY(GamePhysics.GAME_HEIGHT / 2);

        elementPane.getChildren().addAll(winnerText, winnerTank);
    }
}
