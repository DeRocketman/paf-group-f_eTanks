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

    int whichTank = 0;
    double shootDelay = GamePhysics.DELAY_SECOND;

    ObservableList<LevelElement> elementList = FXCollections.observableArrayList();
    ObservableList<ImageView> bulletList = FXCollections.observableArrayList();

    public void startTimer() {
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerMovementDetection();
                bulletCollisionDetection();
            }
        };
        gameTimer.start();
    }

    public void bulletCollisionDetection() {
       /* shootDelay += 0.016;
        if (shootDelay >= 2) {
            shootDelay = 0;
        }*/

        int index = 0;
        for (LevelElement bullet: elementList) {
            if (bullet.getType().equals("bullet")) {
                index++;
                for (int i = 1; i < elementList.size(); i++) {
                    if(elementList.get(i).getType().equals("tank")){
                        if (elementList.get(index).getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                            System.out.println("Test");
                        }
                    }
                    if(elementList.get(i).getType().equals("block")){
                        if (elementList.get(index).getBoundsInParent().intersects(elementList.get(i).getBoundsInParent())) {
                        }
                    }
                }
                moveBullet((BulletMainWeapon) bullet);
            }
        }
    }

    public void playerMovementDetection() {

        ArrayList<LevelElement> filteredList = new ArrayList<>();

        for (LevelElement element : elementList) {
            if (element.getType().equals("tank") || element.getType().equals("block")) {
                filteredList.add(element);
            }
        }

        for (int i = 1; i < filteredList.size(); i++) {
            if (elementList.get(0).getBoundsInParent().intersects(filteredList.get(i).getBoundsInParent())) {
                if (elementList.get(0).getRotate() == 360.0) {
                    elementList.get(0).setLayoutY(filteredList.get(0).getLayoutY() + 5);
                } else if (elementList.get(0).getRotate() == 90.0) {
                    elementList.get(0).setLayoutX(filteredList.get(0).getLayoutX() - 5);
                } else if (elementList.get(0).getRotate() == 180.0) {
                    elementList.get(0).setLayoutY(filteredList.get(0).getLayoutY() - 5);
                } else if (elementList.get(0).getRotate() == 270.0) {
                    elementList.get(0).setLayoutX(filteredList.get(0).getLayoutX() + 5);
                }
            }
        }
    }

    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            System.out.println("Up: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            ((Tank) elementList.get(0)).moveTank(360.0);
        }
        if (keyEvent.getCode() == KeyCode.S) {
            System.out.println("Down: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            elementList.get(0).setType("test");
            ((Tank) elementList.get(0)).moveTank(180.0);
        }
        if (keyEvent.getCode() == KeyCode.D) {
            System.out.println("Right: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            ((Tank) elementList.get(0)).moveTank(90.0);
        }
        if (keyEvent.getCode() == KeyCode.A) {
            System.out.println("Left: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            ((Tank) elementList.get(0)).moveTank(270.0);
        }
        if (keyEvent.getCode() == KeyCode.SPACE) {
            System.out.println("FEUERTASTE: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(whichTank).getRotate());
            fireMainWeapon(elementList.get(0));
        }
    }

    private void fireMainWeapon(LevelElement myTank) {
        double[] bsp = setCorrectPosition(myTank);
        BulletMainWeapon bullet = new BulletMainWeapon("bullet", bsp[0], bsp[1], GamePhysics.ELEMENT_SIZE, GamePhysics.ELEMENT_SIZE, myTank.getRotate(), (Tank) myTank);
        bullet.setDisable(false);
        bullet.setVisible(true);
        bullet.setRotate(myTank.getRotate());
        moveBullet(bullet);
        //translateTransition(mainBullet, myTank);
        elementList.add(bullet);
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


    /* Transition für die Animation der Bullets */
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

    public void setGamePlay(ObservableList<LevelElement> elementList) {
        this.gamePlay = new GamePlay();
        gamePlay.setElementList(elementList);
    }

    public GamePlay getGamePlay() {
        return this.gamePlay;
    }

    public void setBulletList(ObservableList<ImageView> bulletList) {
        this.bulletList = bulletList;
    }
}
