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
import model.game.logic.GameLobby;
import model.game.logic.GamePhysics;
import view.GameView;

import java.util.Objects;

public class GameViewModel implements ViewModel {
    ETankApplication eTankApplication;
    GameLobby gameLobby;

    ObservableList<StackPane> elementList = FXCollections.observableArrayList();
    ObservableList<ImageView> bulletList = FXCollections.observableArrayList();


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
            fireMainWeapon(elementList.get(0));
            System.out.println("FEUERTASTE: " + keyEvent.getCode() + "Aktueller Kurs: " + elementList.get(0).getRotate());
        }
    }

    /*
    * Bewegt den Tank
    * */
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

    /*
    * Sorgt für die Rotationsanimation
    * */
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

    private void fireMainWeapon(StackPane myTank) {
        double[] bsp = setCorrectPosition(myTank);
        //BulletMainWeapon bmw = new BulletMainWeapon(bsp[0], bsp[1], setMainBulletPath(), myTank.getRotate(), true,);
        ImageView mainBullet = new ImageView();
        mainBullet.setFitHeight(40);
        mainBullet.setFitWidth(40);
        mainBullet.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(setMainBulletPath()[5]))));
        mainBullet.setLayoutX(bsp[0]);
        mainBullet.setLayoutY(bsp[1]);
        mainBullet.setDisable(false);
        mainBullet.setVisible(true);
        mainBullet.setRotate(myTank.getRotate());
        translateTransition(mainBullet, myTank);
        bulletList.add(mainBullet);
        System.out.println(mainBullet.getFitHeight() +" "+ mainBullet.getFitWidth());
    }

    private void translateTransition(ImageView imageView, StackPane myTank){
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(imageView);

        if(myTank.getRotate() == 90.0){
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByX(GamePhysics.SHOOT_LENGTH);
        } else if (myTank.getRotate() == 360 || myTank.getRotate() == 0) {
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByY(- GamePhysics.SHOOT_LENGTH);
        } else if (myTank.getRotate() == 270) {
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByX(- GamePhysics.SHOOT_LENGTH);
        } else if (myTank.getRotate() == 180) {
            tr.setDuration(Duration.millis(GamePhysics.BULLET_SPEED));
            tr.setByY(GamePhysics.SHOOT_LENGTH);
        }
        tr.play();
    }

    //TODO auslagern nach Tank?
    private double[] setCorrectPosition(StackPane myTank) {
        double[] bulletStartPosition = new double[2];
        if(myTank.getRotate() == 360.0) {
            bulletStartPosition[0] = myTank.getLayoutX();
            bulletStartPosition[1] = myTank.getLayoutY()-26.0;
        } else if (myTank.getRotate() == 90.0) {
            bulletStartPosition[0] = myTank.getLayoutX()+26.0;
            bulletStartPosition[1] = myTank.getLayoutY();
        } else if (myTank.getRotate() == 180.0) {
            bulletStartPosition[0] = myTank.getLayoutX();
            bulletStartPosition[1] = myTank.getLayoutY()+26.0;
        } else if (myTank.getRotate() == 270.0) {
            bulletStartPosition[0] = myTank.getLayoutX()-26.0;
            bulletStartPosition[1] = myTank.getLayoutY();
        }
        return bulletStartPosition;
    }

    //TODO Auslagern nach BulletMainWeapon!
    private String[] setMainBulletPath() {
        String[] bulletColours = new String[6];
        bulletColours[0] = "../img/images/bullets/Flash_A_01.png";
        bulletColours[1] = "../img/images/bullets/Flash_A_02.png";
        bulletColours[2] = "../img/images/bullets/Flash_A_03.png";
        bulletColours[3] = "../img/images/bullets/Flash_A_04.png";
        bulletColours[4] = "../img/images/bullets/Flash_A_05.png";
        bulletColours[5] = "../img/images/bullets/Medium_Shell.png";
        return  bulletColours;
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

    public void setBulletList(ObservableList<ImageView> bulletList) {
        this.bulletList = bulletList;
    }
}
