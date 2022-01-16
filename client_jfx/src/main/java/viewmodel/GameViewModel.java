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
import model.game.elements.*;
import model.game.logic.GameLobby;
import model.game.logic.GamePhysics;
import model.game.logic.GamePlay;
import view.GameView;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameViewModel implements ViewModel {
    ETankApplication eTankApplication;
    GameLobby gameLobby;
    GamePlay gamePlay;

    int wichTank = 3;

    ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    ObservableList<ImageView> bulletList = FXCollections.observableArrayList();

    public void startTimer() {
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                collisionDetectionMovement();
            }
        };
        gameTimer.start();
    }

    public void collisionDetectionMovement() {
        for (int i = 1; i < elementList.size(); i++) {
            if (elementList.get(0).getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                if (elementList.get(0).getRotate() == 360.0) {
                    elementList.get(0).setLayoutY(elementList.get(0).getLayoutY() + 5);
                } else if (elementList.get(0).getRotate() == 90.0) {
                    elementList.get(0).setLayoutX(elementList.get(0).getLayoutX() - 5);
                } else if (elementList.get(0).getRotate() == 180.0) {
                    elementList.get(0).setLayoutY(elementList.get(0).getLayoutY() - 5);
                } else if (elementList.get(0).getRotate() == 270.0) {
                    elementList.get(0).setLayoutX(elementList.get(0).getLayoutX() + 5);
                }
            }
        }
    }

    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            System.out.println("Up: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            ((Tank) elementList.get(0)).moveTank( 360.0);
        }
        if (keyEvent.getCode() == KeyCode.S) {
            System.out.println("Down: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            elementList.get(0).setType("test");
            ((Tank) elementList.get(0)).moveTank( 180.0);
        }
        if (keyEvent.getCode() == KeyCode.D) {
            System.out.println("Right: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            ((Tank) elementList.get(0)).moveTank( 90.0);
        }
        if (keyEvent.getCode() == KeyCode.A) {
            System.out.println("Left: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            ((Tank) elementList.get(0)).moveTank( 270.0);
        }
        if (keyEvent.getCode() == KeyCode.SPACE) {
            ((Tank) elementList.get(0)).moveTank( 90.0);
            System.out.println("FEUERTASTE: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
        }
    }

    /*
    private void fireMainWeapon(LevelElement myTank) {
        double[] bsp = (Tank) myTank.setCorrectPosition(myTank);
        BulletMainWeapon mainBullet = new BulletMainWeapon("Bullet", bsp[0], bsp[1], GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE, myTank.getRotate(), (Tank) myTank);
        mainBullet.setDisable(false);
        mainBullet.setVisible(true);
        mainBullet.setRotate(myTank.getRotate());
        translateTransition(mainBullet, myTank);
        //elementList.add(mainBullet);
    }
*/


    /*
     * Transition für die Animation der Bullets */
    /*private void translateTransition(ImageView imageView, LevelElement myTank) {
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
    }*/

    public void detectCollision() {
    }

    private void initElements() {

    }

    public void setElementList(ObservableList<LevelElement> elementList) {
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
        gamePlay.setElementList(elementList);
    }

    public GamePlay getGamePlay(){
        return this.gamePlay;
    }

    public void setBulletList(ObservableList<ImageView> bulletList) {
        this.bulletList = bulletList;
    }
}
