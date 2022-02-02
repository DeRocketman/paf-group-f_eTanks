package viewmodel;

import main.ETankApplication;
import model.data.GameStatistic;
import model.game.elements.*;
import model.game.logic.GameLobby;
import model.game.logic.GamePhysics;
import model.service.HttpRequest;
import model.service.Message;
import model.service.MessageType;
import model.service.SocketClient;
import view.GameView;

import de.saxsys.mvvmfx.ViewModel;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameViewModel implements ViewModel {

    ETankApplication eTankApplication;
    SocketClient socketClient;
    HttpRequest httpRequest = new HttpRequest();

    GameLobby gameLobby;
    GameView gameView;

    AnimationTimer gameActionTimer;
    Timeline gameTimeline = new Timeline();
    ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    ArrayList<Tank> tankList = new ArrayList<>();

    int whichTank;
    boolean playerIsActive = true;
    boolean isMovingUp;
    boolean isMovingDown;
    boolean isMovingLeft;
    boolean isMovingRight;
    boolean isFiringMainWeapon;
    boolean canShoot = true;
    boolean gameIsRunning = false;
    boolean endOfGame = false;
    double shootDelay = GamePhysics.BULLET_DELAY;
    double roundTime = GamePhysics.ROUND_TIME;
    int roundCounter = 1;
    private List<GameStatistic> gameStatistics;

    /**
     * Starts the Game
     */
    public void startGame() {
        gameView.initTanks(gameLobby.getPlayers().size());
        gameView.setPlayerText(gameLobby.getPlayers());
        gameView.setPlayerWins(gameStatistics);
        gameView.initElements();
        gameView.initWorldBorder();
        gameView.initDisplay();

        httpRequest.setETankApplication(eTankApplication);

        eTankApplication.getPrimaryStage().getScene().setOnKeyPressed(this::handleKeyPressed);
        eTankApplication.getPrimaryStage().getScene().setOnKeyReleased(this::handleKeyReleased);

        initGameLoop();
        startTimer();
    }

    /**
     * Initialized the list of tanks from the elementlist
     */
    public void initTankList() {
        if (gameLobby.getPlayers().size() == 1) {
            this.tankList.add((Tank) elementList.get(0));
        } else if (gameLobby.getPlayers().size() == 2) {
            this.tankList.add((Tank) elementList.get(0));
            this.tankList.add((Tank) elementList.get(1));
        } else if (gameLobby.getPlayers().size() == 3) {
            this.tankList.add((Tank) elementList.get(0));
            this.tankList.add((Tank) elementList.get(1));
            this.tankList.add((Tank) elementList.get(2));
        } else if (gameLobby.getPlayers().size() == 4) {
            this.tankList.add((Tank) elementList.get(0));
            this.tankList.add((Tank) elementList.get(1));
            this.tankList.add((Tank) elementList.get(2));
            this.tankList.add((Tank) elementList.get(3));
        }
    }

    public void initGameLoop() {
        gameActionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkLeftPlayers();
                playerMovementDetection();
                bulletCollisionDetection();
                shootDelayer();
                shootCollector();
                gameView.updateStatisticText(gameStatistics);
                setPlayerLives();
                if (endOfGame) {
                    System.out.println("ENDEGELÄNDE");
                    this.stop();
                }
            }
        };
        gameActionTimer.start();
    }

    public void startTimer() {
        if (roundCounter < GamePhysics.ROUNDS) {
            KeyFrame kf = new KeyFrame(Duration.seconds(1), event -> {
                if ((roundTime > GamePhysics.END_TIME && !gameIsRunning) || (roundTime > 0 && gameIsRunning)) {
                    gameView.updateTimer(roundTime);
                    roundTime--;
                    System.out.println(roundTime);
                } else if (roundTime == 0 && roundCounter != GamePhysics.ROUNDS) {
                    gameView.updateTimer(roundTime);
                    nextLevel();
                } else if (roundTime == 0) {
                    setRoundWinner();
                    setGameWinner();
                    gameIsRunning = false;
                    endOfGame = true;
                } else if(roundTime == GamePhysics.END_TIME || endOfGame){
                    saveStatistics();
                    gameTimeline.stop();
                    System.out.println("SPIEL ZU ENDE");
                    try {
                        eTankApplication.showMenuView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            gameTimeline.setCycleCount(Animation.INDEFINITE);
            gameTimeline.getKeyFrames().add(kf);

            Timer gameCountdown = new Timer();
            TimerTask startGameCountdown = new TimerTask() {
                int counter = 3;

                @Override
                public void run() {
                    Platform.runLater(() -> {
                        if (counter > 0) {
                            gameView.display.setVisible(true);
                            gameView.display.setImage(new Image("img/images/countdown/Countdown_0" + counter + ".png"));
                            counter--;
                        } else if (counter == 0) {
                            gameView.display.setImage(new Image("img/images/countdown/Countdown_0" + counter + ".png"));
                            gameIsRunning = true;
                            gameTimeline.play();
                            counter--;
                        } else {
                            gameView.display.setVisible(false);
                            gameCountdown.cancel();
                        }
                    });
                }
            };
            gameCountdown.schedule(startGameCountdown, 1000, 1000);
        }
    }

    /**
     * Starts the next level
     */
    public void nextLevel(){
        gameIsRunning = false;
        setRoundWinner();
        elementList.clear();
        playerIsActive = true;
        gameView.initNextLevel(roundCounter);
        gameActionTimer.stop();
        gameTimeline.stop();
        roundCounter++;
        roundTime = GamePhysics.ROUND_TIME;
    }

    /**
     * Ends the game
     */
    public void endGame(){
        setRoundWinner();
        setGameWinner();
        gameIsRunning = false;
        endOfGame = true;
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        if (gameIsRunning && playerIsActive) {
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveUpKey()) || isMovingUp && isFiringMainWeapon) {
                this.isMovingUp = true;
                sendMoveTankMsg(GamePhysics.TANK_MOVE_UP_COURSE);
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveDownKey()) || isMovingDown && isFiringMainWeapon) {
                this.isMovingDown = true;
                sendMoveTankMsg(GamePhysics.TANK_MOVE_DOWN_COURSE);
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveRightKey()) || isMovingRight && isFiringMainWeapon) {
                this.isMovingRight = true;
                sendMoveTankMsg(GamePhysics.TANK_MOVE_RIGHT_COURSE);
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveLeftKey()) || isMovingLeft && isFiringMainWeapon) {
                this.isMovingLeft = true;
                sendMoveTankMsg(GamePhysics.TANK_MOVE_LEFT_COURSE);
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getFireMainWeaponKey()) && canShoot || isFiringMainWeapon && canShoot) {
                this.isFiringMainWeapon = true;
                canShoot = false;
                sendFireMAinMsg();
            }
        }
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveUpKey())) {
            this.isMovingUp = false;
        }
        if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveDownKey())) {
            this.isMovingDown = false;
        }
        if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveRightKey())) {
            this.isMovingRight = false;
        }
        if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveLeftKey())) {
            this.isMovingLeft = false;
        }
        if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getFireMainWeaponKey())) {
            this.isFiringMainWeapon = false;
        }
    }

    public void receiveMessage(Message msg) {
        Platform.runLater(() -> {
            if (msg.getMessageType() == MessageType.TANK_MOVE) {
                processMoveTankMsg(msg);
            } else if (msg.getMessageType() == MessageType.FIRE_MAIN) {
                processFireMainMsg(msg);
            }
        });
    }

    public void processMoveTankMsg(Message msg) {
        for (LevelElement tank : elementList) {
            if (tank.getType() == LevelElementType.TANK) {
                Tank temp = (Tank) tank;
                if (temp.getPlayerId() == msg.getPlayerId()) {
                    temp.moveTank(Double.parseDouble(msg.getPayload()));
                }
            }
        }
    }

    /**
     * Processes the fireMain message and updates the statistic
     * runs through the elementList to find out which tank has the same id as the player wo sent the message
     *
     * @param msg incoming message from the socket server
     */
    public void processFireMainMsg(Message msg) {
        Platform.runLater(() -> {
            // runs through the elementList to find out which tank has the same id as the player wo sent the message
            for (LevelElement tank : elementList) {
                if (tank.getType() == LevelElementType.TANK) {
                    Tank temp = (Tank) tank;
                    if (temp.getPlayerId() == msg.getPlayerId()) {
                        gameStatistics.get(elementList.indexOf(tank)).setShots(gameStatistics.get(elementList.indexOf(tank)).getShots() + 1);
                        fireMainWeapon(temp);
                    }
                }
            }
        });
    }

    public void sendMoveTankMsg(String course) {
        Message msg = new Message();
        msg.setMessageType(MessageType.TANK_MOVE);
        msg.setPlayerId(eTankApplication.getSignedUser().getId());
        msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
        msg.setPlayerImage("default");
        msg.setGameLobbyNumber(gameLobby.getGameLobbyID());
        msg.setPayload(course);
        socketClient.sendMsg(msg);
    }

    public void sendFireMAinMsg() {
        Message msg = new Message();
        msg.setMessageType(MessageType.FIRE_MAIN);
        msg.setPlayerId(eTankApplication.getSignedUser().getId());
        msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
        msg.setPlayerImage("default");
        msg.setGameLobbyNumber(gameLobby.getGameLobbyID());
        msg.setPayload("BOOM");
        socketClient.sendMsg(msg);
    }

    /**
     * Checks if there are two players still alive
     */
    public void checkLeftPlayers(){
        int playersAlive = 0;
        for (Tank tank : tankList){
            if (tank.getLivePoints() > 0){
                playersAlive++;
            }
        }
        if(playersAlive < 2){
            if(roundCounter == GamePhysics.ROUNDS ){
                endGame();
            } else {
                nextLevel();
            }

        }

    }

    private  void setPlayerLives(){
        int [] playerLives = new int[tankList.size()];
        int index = 0;
        for (Tank tank : tankList){
            playerLives[index] = tank.getLivePoints();
            index++;
        }
        gameView.setPlayerLives(playerLives);
    }

    private void shootCollector() {
        ArrayList<LevelElement> bulletsToRemove = new ArrayList<>();
        for (LevelElement element : elementList) {
            if (element.getType() == LevelElementType.BULLETMAINWEAPON) {
                if (element.getX() == 0 && element.getY() == 0) {
                    bulletsToRemove.add(element);
                }
            }
        }
        elementList.removeAll(bulletsToRemove);
        bulletsToRemove.clear();
    }

    private void shootDelayer() {
        shootDelay += 0.005;
        if (shootDelay >= GamePhysics.BULLET_DELAY) {
            canShoot = true;
            shootDelay = 0;
        }
    }

    private void fireMainWeapon(LevelElement myTank) {
        Platform.runLater(() -> {
            Tank tank = (Tank) myTank;
            double[] position = tank.setCorrectBulletPosition();
            gameView.createMainBullet(myTank, position);
        });
    }

    public void moveBullet(BulletMainWeapon bullet) {
        double rotation = bullet.getRotate();
        if (rotation == GamePhysics.BULLET_ROTATION_RIGHT) {
            bullet.setX(bullet.getX() + GamePhysics.BULLET_SPEED);
        } else if (rotation == GamePhysics.BULLET_ROTATION_UP || rotation == 0.0) {
            bullet.setY(bullet.getY() - GamePhysics.BULLET_SPEED);
        } else if (rotation == GamePhysics.BULLET_ROTATION_LEFT) {
            bullet.setX(bullet.getX() - GamePhysics.BULLET_SPEED);
        } else if (rotation == GamePhysics.BULLET_ROTATION_DOWN) {
            bullet.setY(bullet.getY() + GamePhysics.BULLET_SPEED);
        }
    }

    /**
     * checks if bullet hit an element
     * and reacts depending on the type
     */
    private void bulletCollisionDetection() {
        boolean isHit = false;
        LevelElement toRemove = null;
        LevelElement toRemoveTwo = null;

        for (LevelElement element : elementList) {
            if (element.getType() == LevelElementType.BULLETMAINWEAPON) {

                BulletMainWeapon tempBullet = ((BulletMainWeapon) element);
                long tankFiredPlayerId = tempBullet.getTankFired().getPlayerId();

                for (int i = 0; i < elementList.size(); i++) {
                    if (elementList.get(i).getType() == LevelElementType.TANK) {
                        Tank tank = (Tank) elementList.get(i);
                        if (tank.getActive()) {
                            if (element.getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                                //Bullet trifft auf tank von dem sie nicht abgeschossen wurde
                                if (tankFiredPlayerId != tank.getPlayerId()) {
                                    //hide bullet
                                    toRemove = element;
                                    element.setDisable(true);
                                    isHit = true;

                                    tank.reduceLivePoints();

                                    if (tank.getLivePoints() == 0) {
                                        Tank myTank = (Tank) elementList.get(whichTank);
                                        if (myTank.getPlayerId() == tank.getPlayerId()) {
                                            playerIsActive = false;
                                        }

                                        // Tank who was hit
                                        tank.setActive(false);
                                        tank.setVisible(false);
                                        tank.setDisable(true);
                                        gameStatistics.get(elementList.indexOf(tank)).setDeaths(gameStatistics.get(elementList.indexOf(tank)).getDeaths() + 1);

                                        // Shooting Tank
                                        gameStatistics.get(elementList.indexOf(tempBullet.getTankFired())).setKills(gameStatistics.get(elementList.indexOf(tempBullet.getTankFired())).getKills() + 1);
                                        gameStatistics.get(elementList.indexOf(tempBullet.getTankFired())).setHitPoints(gameStatistics.get(elementList.indexOf(tempBullet.getTankFired())).getHitPoints() + GamePhysics.KILL_POINTS);
                                        gameStatistics.get(elementList.indexOf(tempBullet.getTankFired())).setGamePoints(gameStatistics.get(elementList.indexOf(tempBullet.getTankFired())).getGamePoints() + GamePhysics.KILL_POINTS);
                                    }
                                }
                            }
                        }
                    } else if (elementList.get(i).getType() == LevelElementType.BLOCK_METAL) {
                        if (element.getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                            toRemove = element;
                            element.setDisable(true);
                            isHit = true;
                        }
                    } else if (elementList.get(i).getType() == LevelElementType.BLOCK_STONE) {
                        Block stoneblock = (Block) elementList.get(i);
                        if (element.getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                            if (stoneblock.getLives() == 2) {
                                stoneblock.setOpacity(.50);
                                stoneblock.setLives(1);
                                toRemove = element;
                                isHit = true;
                            } else if (stoneblock.getLives() == 1) {
                                stoneblock.setOpacity(.0);
                                toRemove = element;
                                toRemoveTwo = stoneblock;
                                element.setDisable(true);
                                elementList.get(i).setDisable(true);
                                stoneblock.setDisable(true);
                                isHit = true;
                            }
                        }
                    } else if (elementList.get(i).getType() == LevelElementType.BLOCK_WOOD) {
                        Block woodenBlock = (Block) elementList.get(i);
                        if (element.getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                            if (woodenBlock.getLives() == 3) {
                                woodenBlock.setOpacity(.75);
                                woodenBlock.setLives(2);
                                toRemove = element;
                                isHit = true;
                            } else if (woodenBlock.getLives() == 2) {
                                woodenBlock.setOpacity(.50);
                                woodenBlock.setLives(1);
                                toRemove = element;
                                isHit = true;
                            } else if (woodenBlock.getLives() == 1) {
                                woodenBlock.setOpacity(.25);
                                woodenBlock.setLives(0);
                                toRemove = element;
                                isHit = true;
                            } else if (woodenBlock.getLives() == 0) {
                                woodenBlock.setOpacity(.0);
                                toRemove = element;
                                toRemoveTwo = woodenBlock;
                                element.setDisable(true);
                                elementList.get(i).setDisable(true);
                                woodenBlock.setDisable(true);
                                isHit = true;
                            }
                        }
                    }
                }
                if (!isHit) {
                    moveBullet((BulletMainWeapon) element);
                }
            }
        }
        if (toRemove != null) {
            elementList.remove(toRemove);
        }
        if (toRemoveTwo != null) {
            elementList.remove(toRemoveTwo);
        }
    }

    private void playerMovementDetection() {
        ArrayList<LevelElement> filteredList = new ArrayList<>();
        boolean check = true;

        for (LevelElement element : elementList) {
            if (element.getType() == LevelElementType.TANK || element.getType() == LevelElementType.BLOCK_WOOD || element.getType() == LevelElementType.BLOCK_METAL || element.getType() == LevelElementType.BLOCK_STONE) {
                filteredList.add(element);
            }
        }

        for (Tank tank : tankList) {
            for (LevelElement element : filteredList) {
                if (element.getType() == LevelElementType.TANK && tank.getPlayerId() == ((Tank) element).getPlayerId()) {
                    check = false;
                }
                if (check) {
                    if (tank.getBoundsInParent().intersects(element.getBoundsInParent())) {
                        if (tank.getRotate() == 360.0) {
                            isMovingUp = false;
                            tank.setLayoutY(tank.getLayoutY() + GamePhysics.TANK_SPEED);
                        } else if (tank.getRotate() == 90.0) {
                            isMovingRight = false;
                            tank.setLayoutX(tank.getLayoutX() - GamePhysics.TANK_SPEED);
                        } else if (tank.getRotate() == 180.0) {
                            isMovingDown = false;
                            tank.setLayoutY(tank.getLayoutY() - GamePhysics.TANK_SPEED);
                        } else if (tank.getRotate() == 270.0) {
                            isMovingRight = false;
                            tank.setLayoutX(tank.getLayoutX() + GamePhysics.TANK_SPEED);
                        }
                    }
                }
                check = true;
            }
        }
    }

    /**
     * Creates a new List of GameStatistics
     * one Statistic for every player
     */
    public void createGameStatistic() {
        gameStatistics = new ArrayList<>();
        int playerCount = gameLobby.getPlayers().size();
        for (int i = 0; i < playerCount; i++) {
            GameStatistic userGameStatistic = new GameStatistic(gameLobby.getGameLobbyID(), gameLobby.getPlayers().get(i).getId(), false, 0, 0, 0, 0, 0, 0, gameLobby.getPlayers().get(i).getPublicName());
            gameStatistics.add(userGameStatistic);
        }
    }

    /**
     * Sets the list position of the player
     */
    public void setWhichTank() {
        for (int i = 0; i < gameLobby.getPlayers().size(); i++) {

            Tank temp = (Tank) elementList.get(i);
            temp.setPlayerId(gameLobby.getPlayers().get(i).getId());

            if (eTankApplication.getSignedUser().getId() == gameLobby.getPlayers().get(i).getId()) {
                whichTank = i;
            }
        }
    }

    /**
     * Sets the round winner
     * first who has more livePoints and second who has more gamepoints
     */
    private void setRoundWinner() {
        int winner = 0;
        for (int i = 0; i < gameStatistics.size(); i++) {
            if (i < gameStatistics.size() - 1) {
                if (((Tank) elementList.get(i)).getLivePoints() > 0) {
                    if (gameStatistics.get(i).getGamePoints() > gameStatistics.get(i + 1).getGamePoints()) {
                        winner = i;
                    }
                }
            } else {
                if (((Tank) elementList.get(i)).getLivePoints() > 0) {
                    if (gameStatistics.get(i).getGamePoints() > gameStatistics.get(0).getGamePoints()) {
                        winner = i;
                    }
                }
            }
        }
        gameStatistics.get(winner).setRoundWins(gameStatistics.get(winner).getRoundWins() + 1);
    }

    /**
     * Sets the game winner
     * first who won more rounds and second who has more gamePoints
     */
    private void setGameWinner() {

        int winner = 0;
        for (int i = 0; i < gameStatistics.size(); i++) {
            if (i < gameStatistics.size() - 1) {
                if (gameStatistics.get(i).getRoundWins() > gameStatistics.get(i + 1).getRoundWins()) {
                    if (gameStatistics.get(i).getGamePoints() > gameStatistics.get(i + 1).getGamePoints()) {
                        winner = i;
                    }
                }
            } else {
                if (gameStatistics.get(i).getRoundWins() > gameStatistics.get(0).getRoundWins()) {
                    if (gameStatistics.get(i).getGamePoints() > gameStatistics.get(0).getGamePoints()) {
                        winner = i;
                    }
                }
            }
        }

        String winnerName = gameStatistics.get(winner).getUserName();
        gameView.setGameMessage("Commander " + winnerName + " hat gewonnen!", winner);
    }

    /**
     * Sends a http request to save the game statistics
     */
    private void saveStatistics() {
        if (whichTank == 0) {
            setHttpRequestETankapplication();

            for (GameStatistic gameStatistic : gameStatistics) {
                httpRequest.saveGameStatistic(gameStatistic, gameStatistic.getUserId());
            }
        }
    }

    /**
     * Sets the eTankApplication in the class httpRequest
     */
    public void setHttpRequestETankapplication() {
        httpRequest.setETankApplication(eTankApplication);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void setLobby(GameLobby selectedLobby) {
        this.gameLobby = selectedLobby;
    }

    public void setElementList(ObservableList<LevelElement> elementList) {
        this.elementList = elementList;
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}