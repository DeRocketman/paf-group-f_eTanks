package viewmodel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.ETankApplication;
import model.game.logic.GameLobby;
import model.game.logic.GamePhysics;
import view.GameView;

public class GameViewModel implements ViewModel {
    ETankApplication eTankApplication;
    GameLobby gameLobby;

    ObservableList<StackPane> elementList = FXCollections.observableArrayList();


    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            System.out.println("Up: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(0).getRotate());
            moveTank(elementList.get(0), 360.0);
        }
        if (keyEvent.getCode() == KeyCode.S) {
            System.out.println("Down: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(0).getRotate());
            moveTank(elementList.get(0), 180.0);
        }
        if (keyEvent.getCode() == KeyCode.D) {
            System.out.println("Right: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(0).getRotate());
            moveTank(elementList.get(0), 90.0);
        }
        if (keyEvent.getCode() == KeyCode.A) {
            System.out.println("Left: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(0).getRotate());
            moveTank(elementList.get(0), 270.0);
        }
        if (keyEvent.getCode() == KeyCode.SPACE) {
            System.out.println("FEUERTASTE: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(0).getRotate());
        }
    }

    public void moveTank(StackPane myTank, double newCourse) {
        double speed = GamePhysics.TANK_SPEED;
        if (myTank.getRotate() == newCourse) {
            if (newCourse == 360.0) {
                myTank.setLayoutY(myTank.getLayoutY() - speed);
            } else if (newCourse == 180.0) {
                myTank.setLayoutY(myTank.getLayoutY() + speed);
            } else if (newCourse == 90.0) {
                myTank.setLayoutX(myTank.getLayoutX() + speed);
            } else if (newCourse == 270.0) {
                myTank.setLayoutX(myTank.getLayoutX() - speed);
            }
        } else {
            rotateTransition(myTank, newCourse);
        }
    }


    public void rotateTransition(StackPane myTank, double newCourse) {

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

    public void setElementList(ObservableList<StackPane> elementList) {
        this.elementList = elementList;
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void setGame(GameLobby gameLobby) {
        this.gameLobby = gameLobby;
    }
}
