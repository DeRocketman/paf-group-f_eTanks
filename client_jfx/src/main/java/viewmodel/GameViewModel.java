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

    ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    ArrayList<Tank> tankList = new ArrayList<>();

    int whichTank;
    boolean isMovingUp;
    boolean isMovingDown;
    boolean isMovingLeft;
    boolean isMovingRight;
    boolean isFiringMainWeapon;
    boolean canShoot = true;
    boolean gameIsRunning = false;
    double shootDelay = GamePhysics.DELAY_SECOND;
    double roundTime = GamePhysics.ROUND_TIME;
    int roundCounter = 1;
    private List<GameStatistic> gameStatistics;

    public void startGame() {
        gameView.initTanks(gameLobby.getPlayers().size());
        gameView.setPlayerText(gameLobby.getPlayers());
        gameView.setPlayerWins(gameStatistics);
        gameView.initElements();
        gameView.initWorldBorder();
        gameView.initDisplay();

        eTankApplication.getPrimaryStage().getScene().setOnKeyPressed(this::handleKeyPressed);
        eTankApplication.getPrimaryStage().getScene().setOnKeyReleased(this::handleKeyReleased);

        initGameLoop();
        startTimer();
    }

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
        AnimationTimer gameActionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerMovementDetection();
                bulletCollisionDetection();
                shootDelayer();
                shootCollector();
                gameView.updateStatisticText(gameStatistics);
            }
        };
        gameActionTimer.start();
    }

    public void startTimer() {
        System.out.println(roundCounter);
        if (roundCounter < 3) {

            Timeline gameTimeline = new Timeline();
            KeyFrame kf = new KeyFrame(Duration.seconds(1), event -> {
                if (roundTime > 0) {
                    gameView.updateTimer(roundTime);
                    roundTime--;
                } else if (roundTime == 0 && roundCounter != 3) {
                    gameIsRunning = false;
                    setRoundWinner();
                    elementList.clear();
                    gameView.initNextLevel(roundCounter);
                    gameTimeline.stop();
                    roundCounter++;
                    roundTime = GamePhysics.ROUND_TIME;
                } else if (roundTime == 0 && roundCounter == 3) {
                    //TODO WAS PASSIERT WENN DAS GAME ZUENDE IST
                    gameIsRunning = false;
                    setGameWinner();
                    saveStatistics();
                    try {
                        eTankApplication.showMenuView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    gameTimeline.stop();
                    System.out.println("SPIEL ZUENDE");
                }
                System.out.println(roundTime);
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

    public void handleKeyPressed(KeyEvent keyEvent) {
        if (gameIsRunning) {
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveUpKey()) || isMovingUp && isFiringMainWeapon) {
                this.isMovingUp = true;
                //((Tank) elementList.get(whichTank)).moveTank(360.0);
                sendMoveTankMsg("360.0");
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveDownKey()) || isMovingDown && isFiringMainWeapon) {
                this.isMovingDown = true;
                //((Tank) elementList.get(whichTank)).moveTank(180.0);
                sendMoveTankMsg("180.0");
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveRightKey()) || isMovingRight && isFiringMainWeapon) {
                this.isMovingRight = true;
                //((Tank) elementList.get(whichTank)).moveTank(90.0);
                sendMoveTankMsg("90.0");
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getMoveLeftKey()) || isMovingLeft && isFiringMainWeapon) {
                this.isMovingLeft = true;
                //((Tank) elementList.get(whichTank)).moveTank(270.0);
                sendMoveTankMsg("270.0");
            }
            if (keyEvent.getCode().toString().equals(eTankApplication.getSignedUser().getUserSettings().getFireMainWeaponKey()) && canShoot || isFiringMainWeapon && canShoot) {
                this.isFiringMainWeapon = true;
                canShoot = false;
                //fireMainWeapon(elementList.get(whichTank));
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

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (msg.getMessageType() == MessageType.TANK_MOVE) {
                    processMoveTankMsg(msg);
                } else if (msg.getMessageType() == MessageType.FIRE_MAIN) {
                    processFireMainMsg(msg);
                }
            }
        });
    }

    public void processMoveTankMsg(Message msg) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (LevelElement tank : elementList) {
                    if (tank.getType() == LevelElementType.TANK) {
                        Tank temp = (Tank) tank;
                        if (temp.getPlayerId() == msg.getPlayerId()) {
                            temp.moveTank(Double.parseDouble(msg.getPayload()));
                        }
                    }
                }
            }
        });
    }

    /**
     * Processes the fireMain message and updates the statistic
     * runs through the elementList to find out which tank has the same id as the player wo sent the message
     *
     * @param msg
     */
    public void processFireMainMsg(Message msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // runs through the elementList to find out which tank has the same id as the player wo sent the message
                for (LevelElement tank : elementList) {
                    if (tank.getType() == LevelElementType.TANK) {
                        Tank temp = (Tank) tank;
                        if (temp.getPlayerId() == msg.getPlayerId()) {
                            gameStatistics.get(elementList.indexOf(tank)).setShots(gameStatistics.get(elementList.indexOf(tank)).getShots() + 1);
                            //  System.out.println(gameStatistics.get(elementList.indexOf(tank)).getUserName() + " - Shots: " + gameStatistics.get(elementList.indexOf(tank)).getShots());
                            fireMainWeapon(temp);
                        }
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
        shootDelay += 0.05;
        if (shootDelay >= 2) {
            canShoot = true;
            shootDelay = 0;
        }
    }

    private void fireMainWeapon(LevelElement myTank) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Tank tank = (Tank) myTank;
                double[] position = tank.setCorrectBulletPosition(myTank);
                gameView.createMainBullet(myTank, position);
            }
        });
        //gamePlay.getGameStatistic().setShots(gamePlay.getGameStatistic().getShots() + 1);
    }

    public void moveBullet(BulletMainWeapon bullet) {
        double rotation = bullet.getRotate();
        if (rotation == 90.0) {
            bullet.setX(bullet.getX() + 5);
        } else if (rotation == 360 || rotation == 0) {
            bullet.setY(bullet.getY() - 5);
        } else if (rotation == 270) {
            bullet.setX(bullet.getX() - 5);
        } else if (rotation == 180) {
            bullet.setY(bullet.getY() + 5);
        }
    }

    private void bulletCollisionDetection() {

        boolean isHit = false;
        LevelElement toRemove = null;
        LevelElement toRemoveTwo = null;
        boolean myBullet = false;
        Tank myTankTemp = (Tank) elementList.get(whichTank);

        for (LevelElement element : elementList) {
            if (element.getType() == LevelElementType.BULLETMAINWEAPON) {

                BulletMainWeapon tempBullet = ((BulletMainWeapon) element);
                long playerId = tempBullet.getTankFired().getPlayerId();

                if (playerId == myTankTemp.getPlayerId()) {
                    myBullet = true;
                } else {
                    myBullet = false;
                }

                for (int i = 0; i < elementList.size(); i++) {
                    if (elementList.get(i).getType() == LevelElementType.TANK) {
                        if (element.getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                            Tank tank = (Tank) elementList.get(i);
                            if (playerId != tank.getPlayerId()) {
                                //Bullet ausblenden
                                toRemove = element;
                                element.setDisable(true);
                                isHit = true;
                                if (!myBullet) {
                                    //Player was hit
                                    //System.out.println("Du wurdest von: " + tank.getPlayerId() + " getroffen!");

                                    //reduces own live and updates own death statistic
                                    ((Tank) elementList.get(whichTank)).reduceLivePoints();
                                    gameStatistics.get(whichTank).setDeaths(gameStatistics.get(whichTank).getDeaths() + 1);
                                    //System.out.println(gameStatistics.get(whichTank).getUserName() + " - Livepoints: " + ((Tank) elementList.get(whichTank)).getLivePoints());
                                    //System.out.println(gameStatistics.get(whichTank).getUserName() + " - Deaths: " + gameStatistics.get(whichTank).getDeaths());

                                    //updates kill & hitpoints statistic of other player
                                    gameStatistics.get(elementList.indexOf(tank)).setKills(gameStatistics.get(elementList.indexOf(tank)).getKills() + 1);
                                    gameStatistics.get(elementList.indexOf(tank)).setHitPoints(gameStatistics.get(elementList.indexOf(tank)).getHitPoints() + GamePhysics.KILL_POINTS);
                                    gameStatistics.get(elementList.indexOf(tank)).setGamePoints(gameStatistics.get(elementList.indexOf(tank)).getGamePoints() + GamePhysics.KILL_POINTS);
                                } else {
                                    //other player was hit
                                    //System.out.println("Player: " + playerId + " Du hast Player: " + tank.getPlayerId() + " getroffen!");

                                    //updates other player death statstic
                                    tank.reduceLivePoints();
                                    gameStatistics.get(elementList.indexOf(tank)).setDeaths(gameStatistics.get(elementList.indexOf(tank)).getDeaths() + 1);

                                    //updates own kill & hitpoints statistic
                                    gameStatistics.get(whichTank).setKills(gameStatistics.get(whichTank).getKills() + 1);
                                    gameStatistics.get(whichTank).setHitPoints(gameStatistics.get(whichTank).getHitPoints() + GamePhysics.KILL_POINTS);
                                    gameStatistics.get(whichTank).setGamePoints(gameStatistics.get(whichTank).getGamePoints() + GamePhysics.KILL_POINTS);

                                    //System.out.println(gameStatistics.get(whichTank).getUserName() + " - Kills: " + gameStatistics.get(whichTank).getKills());
                                    //System.out.println(gameStatistics.get(whichTank).getUserName() + " - Hitpoints: " + gameStatistics.get(whichTank).getHitPoints());
                                }
                            }
                        }
                    } else if (elementList.get(i).getType() == LevelElementType.BLOCK_METAL) {
                        if (element.getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                            toRemove = element;
                            element.setDisable(true);
                            isHit = true;
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

 /*   private void playerMovementDetection() {

        ArrayList<LevelElement> filteredList = new ArrayList<>();

        for (LevelElement element : elementList) {
            if (element.getType() == LevelElementType.TANK || element.getType() == LevelElementType.BLOCK_WOOD || element.getType() == LevelElementType.BLOCK_METAL) {
                filteredList.add(element);
            }
        }

        for (int i = 0; i < filteredList.size(); i++) {
            Tank myTankTemp = (Tank) elementList.get(whichTank);
            Tank tankTemp;
            Boolean myTank = false;

            if (filteredList.get(i).getType() == LevelElementType.TANK) {
                tankTemp = (Tank) filteredList.get(i);
                if (tankTemp.getPlayerId() == myTankTemp.getPlayerId()) {
                    myTank = true;
                } else {
                    myTank = false;
                }
            }
            if (!myTank) {
                if (elementList.get(whichTank).getBoundsInParent().intersects(filteredList.get(i).getBoundsInParent())) {
                    if (elementList.get(whichTank).getRotate() == 360.0) {
                        elementList.get(whichTank).setLayoutY(filteredList.get(whichTank).getLayoutY() + 5);
                    } else if (elementList.get(whichTank).getRotate() == 90.0) {
                        elementList.get(whichTank).setLayoutX(filteredList.get(whichTank).getLayoutX() - 5);
                    } else if (elementList.get(whichTank).getRotate() == 180.0) {
                        elementList.get(whichTank).setLayoutY(filteredList.get(whichTank).getLayoutY() - 5);
                    } else if (elementList.get(whichTank).getRotate() == 270.0) {
                        elementList.get(whichTank).setLayoutX(filteredList.get(whichTank).getLayoutX() + 5);
                    }
                }
            }
        }
    }*/

    private void playerMovementDetection() {

        ArrayList<LevelElement> filteredList = new ArrayList<>();
        boolean check = true;

        for (LevelElement element : elementList) {
            if (element.getType() == LevelElementType.TANK || element.getType() == LevelElementType.BLOCK_WOOD || element.getType() == LevelElementType.BLOCK_METAL) {
                filteredList.add(element);
            }
        }

        for (Tank tank : tankList) {
            System.out.println("Tank:" + tank.getPlayerId());
            for (LevelElement element : filteredList) {
                if (element.getType() == LevelElementType.TANK && tank.getPlayerId() == ((Tank) element).getPlayerId()) {
                    check = false;
                    System.out.println("Listen Tank: " + tank.getPlayerId() + " ElementList Tank: " + ((Tank) element).getPlayerId());
                } else {
                    System.out.println("hier muss getestet werden");
                }
                if (check) {
                    System.out.println("hier wird getestet");
                    if (tank.getBoundsInParent().intersects(element.getBoundsInParent())) {
                        System.out.println("Collision zwischen: " + tank.getPlayerId() + "&&" + (element.getType()));
                        if (tank.getRotate() == 360.0) {
                            tank.setLayoutY(tank.getLayoutY() + 5);
                        } else if (tank.getRotate() == 90.0) {
                            tank.setLayoutX(tank.getLayoutX() - 5);
                        } else if (tank.getRotate() == 180.0) {
                            tank.setLayoutY(tank.getLayoutY() - 5);
                        } else if (tank.getRotate() == 270.0) {
                            tank.setLayoutX(tank.getLayoutX() + 5);
                        }
                    }
                }
                check = true;
            }
        }
    }

    public void createGameStatistic() {
        gameStatistics = new ArrayList<>();
        int playerCount = gameLobby.getPlayers().size();
        for (int i = 0; i < playerCount; i++) {
            GameStatistic userGameStatistic = new GameStatistic(gameLobby.getGameLobbyID(), gameLobby.getPlayers().get(i).getId(), false, 0, 0, 0, 0, 0, 0, gameLobby.getPlayers().get(i).getPublicName());
            gameStatistics.add(userGameStatistic);
        }
    }

    public void setElementList(ObservableList<LevelElement> elementList) {
        this.elementList = elementList;
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void setGameView(GameView gameView) {
        /*Default Player, später über Lobby übergeben*/
        //Sets Player -> tank
        this.gameView = gameView;
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

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void setLobby(GameLobby selectedLobby) {
        this.gameLobby = selectedLobby;
    }

    /**
     * Sets the round winner
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
        gameStatistics.get(winner).setWinner(true);
    }

    /**
     * Sends a http request to save the game statistics
     */
    private void saveStatistics() {
        if (whichTank == 0) {
            setHttpRequestETankapplication();

            for (int i = 0; i < gameStatistics.size(); i++) {
                httpRequest.saveGameStatistic(gameStatistics.get(i), gameStatistics.get(i).getUserId());
            }
        }
    }

    public void setHttpRequestETankapplication() {
        httpRequest.setETankApplication(eTankApplication);
    }
}