package model.game.logic;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            System.out.println("Up: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            moveTank(elementList.get(whichTank), 360.0);
        }
        if (keyEvent.getCode() == KeyCode.S) {
            System.out.println("Down: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            moveTank(elementList.get(whichTank), 180.0);
        }
        if (keyEvent.getCode() == KeyCode.D) {
            System.out.println("Right: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            moveTank(elementList.get(whichTank), 90.0);
        }
        if (keyEvent.getCode() == KeyCode.A) {
            System.out.println("Left: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            moveTank(elementList.get(whichTank), 270.0);
        }
        if (keyEvent.getCode() == KeyCode.SPACE) {
            fireMainWeapon(elementList.get(whichTank));
            System.out.println("FEUERTASTE: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
        }
    }

    /*
     * Bewegt den Tank bis an die Spielfeldgrenze
     * */
    public void moveTank(LevelElement myTank, double newCourse) {
        double speed = GamePhysics.TANK_SPEED;

        if (myTank.getRotate() == newCourse) {
            if (newCourse == 360.0) {
                if (myTank.getLayoutY() >= 5 && canMove[0]) {
                    myTank.setLayoutY(myTank.getLayoutY() - speed);
                }
            } else if (newCourse == 90.0 && canMove[1]) {
                if (myTank.getLayoutX() <= GamePhysics.GAME_WIDTH - 45) {
                    myTank.setLayoutX(myTank.getLayoutX() + speed);
                }
            } else if (newCourse == 180.0 && canMove[2]) {
                if (myTank.getLayoutY() <= GamePhysics.GAME_HEIGHT - 45) {
                    myTank.setLayoutY(myTank.getLayoutY() + speed);
                }
            } else if (newCourse == 270.0 && canMove[3]) {
                if (myTank.getLayoutX() >= 10) {
                    myTank.setLayoutX(myTank.getLayoutX() - speed);
                }
            }
        } else {
            rotateTransition(myTank, newCourse);
        }
    }

    /*
     * Sorgt für die Rotationsanimation
     * */
    public void rotateTransition(LevelElement myTank, double newCourse) {

        RotateTransition rt;

        if (Math.abs(myTank.getRotate() - newCourse) == 180.0) {
            rt = new RotateTransition(Duration.seconds(GamePhysics.TANK_QUARTER_ROTATION_DURATION * 2), myTank);
        } else {
            rt = new RotateTransition(Duration.seconds(GamePhysics.TANK_QUARTER_ROTATION_DURATION), myTank);
        }
        if (rt.getStatus() != Animation.Status.RUNNING) {
            if (myTank.getRotate() == 360.0 && newCourse == 90.0) {
                myTank.setRotate(0.0);
                rt.setFromAngle(myTank.getRotate());
                rt.setToAngle(newCourse);
            } else if (myTank.getRotate() == 0.0 && newCourse == 360.0) {
                myTank.setRotate(newCourse);
                rt.setFromAngle(myTank.getRotate());
                rt.setToAngle(newCourse);
            } else if (myTank.getRotate() == 90.0 && newCourse == 360.0) {
                rt.setFromAngle(myTank.getRotate());
                rt.setToAngle(0.0);
            } else {
                rt.setFromAngle(myTank.getRotate());
                rt.setToAngle(newCourse);
            }
            if (myTank.getRotate() == 0.0) {
                myTank.setRotate(360.0);
            }
        }
        rt.play();
    }

    private void fireMainWeapon(LevelElement myTank) {
        double[] bsp = setCorrectPosition(myTank);
        BulletMainWeapon mainBullet = new BulletMainWeapon("Bullet", bsp[0], bsp[1], GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE, myTank.getRotate(), (Tank) myTank);
        mainBullet.setDisable(false);
        mainBullet.setVisible(true);
        mainBullet.setRotate(myTank.getRotate());
        translateTransition(mainBullet, myTank);
        elementList.add(mainBullet);
    }

    //TODO auslagern nach Tank? Gibt ein Array mit den Werten 0,0 zurück, wenn der Panzer noch in der Rotation ist.
    private double[] setCorrectPosition(LevelElement myTank) {
        double[] bulletStartPosition = new double[2];
        if (myTank.getRotate() == 360.0 || myTank.getRotate() == 0.0) {
            bulletStartPosition[0] = myTank.getLayoutX();
            bulletStartPosition[1] = myTank.getLayoutY() - 26.0;
        } else if (myTank.getRotate() == 90.0) {
            bulletStartPosition[0] = myTank.getLayoutX() + 26.0;
            bulletStartPosition[1] = myTank.getLayoutY();
        } else if (myTank.getRotate() == 180.0) {
            bulletStartPosition[0] = myTank.getLayoutX();
            bulletStartPosition[1] = myTank.getLayoutY() + 26.0;
        } else if (myTank.getRotate() == 270.0) {
            bulletStartPosition[0] = myTank.getLayoutX() - 26.0;
            bulletStartPosition[1] = myTank.getLayoutY();
        }
        System.out.println("X: " + bulletStartPosition[0] + " Y:" + bulletStartPosition[1]);
        return bulletStartPosition;
    }

    /*
     * Transition für die Animation der Bullets
     * */
    private void translateTransition(ImageView imageView, LevelElement myTank) {
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(imageView);

        if (myTank.getRotate() == 90.0) {
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByX(GamePhysics.SHOOT_LENGTH);
        } else if (myTank.getRotate() == 360 || myTank.getRotate() == 0) {
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByY(-GamePhysics.SHOOT_LENGTH);
        } else if (myTank.getRotate() == 270) {
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByX(-GamePhysics.SHOOT_LENGTH);
        } else if (myTank.getRotate() == 180) {
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByY(GamePhysics.SHOOT_LENGTH);
        }
        tr.play();
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

    public void setElementList(ObservableList<LevelElement> elementList) {
        this.elementList = elementList;
    }
}
