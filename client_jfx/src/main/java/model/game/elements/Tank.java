package model.game.elements;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.game.logic.GamePhysics;
import model.game.logic.Player;
import org.boon.primitive.Int;

public class Tank extends LevelElement{
    // private Player tankCommander;
    private int playerId;
    private int livePoints;

    public Tank(Image image, String type, double positionX, double positionY, double width, double height, double rotation, int livePoints) {
        super(image, "tank",positionX, positionY, width, height, rotation);
        this.livePoints = livePoints;
    }

    public Tank(Image image, String type, double positionX, double positionY, double width, double height, double rotation, int livePoints, int playerId) {
        super(image, "tank",positionX, positionY, width, height, rotation);
        this.livePoints = livePoints;
        this.playerId = playerId;
    }
    /*
     * Bewegt den Tank bis an die Spielfeldgrenze
     * */
    public void moveTank(double newCourse) {
        double speed = GamePhysics.TANK_SPEED;

        if (this.getRotate() == newCourse) {
            if (newCourse == 360.0) {
                if (this.getLayoutY() >= 5) {
                    this.setLayoutY(this.getLayoutY() - speed);
                }
            } else if (newCourse == 90.0) {
                if (this.getLayoutX() <= GamePhysics.GAME_WIDTH - 45) {
                    this.setLayoutX(this.getLayoutX() + speed);
                }
            } else if (newCourse == 180.0) {
                if (this.getLayoutY() <= GamePhysics.GAME_HEIGHT - 45) {
                    this.setLayoutY(this.getLayoutY() + speed);
                }
            } else if (newCourse == 270.0) {
                if (this.getLayoutX() >= 10) {
                    this.setLayoutX(this.getLayoutX() - speed);
                }
            }
        } else {
            rotateTransition(this, newCourse);
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

    public double[] setCorrectBulletPosition(LevelElement myTank) {
        double[] bulletStartPosition = new double[2];
        if (myTank.getRotate() == 360.0 || myTank.getRotate() == 0.0) {
            bulletStartPosition[0] = myTank.getLayoutX();
            bulletStartPosition[1] = myTank.getLayoutY() - 10.0;
        } else if (myTank.getRotate() == 90.0) {
            bulletStartPosition[0] = myTank.getLayoutX() + 10.0;
            bulletStartPosition[1] = myTank.getLayoutY();
        } else if (myTank.getRotate() == 180.0) {
            bulletStartPosition[0] = myTank.getLayoutX();
            bulletStartPosition[1] = myTank.getLayoutY() + 10.0;
        } else if (myTank.getRotate() == 270.0) {
            bulletStartPosition[0] = myTank.getLayoutX() - 10.0;
            bulletStartPosition[1] = myTank.getLayoutY();
        }
        System.out.println("X: " + bulletStartPosition[0] + " Y:" + bulletStartPosition[1]);
        return bulletStartPosition;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void reduceLivePoints(){
        livePoints = livePoints--;
    }


/*
    public void setTankCommander(User commander) {
        this.tankCommander = commander;
    }
    public User getTankCommander() {
        return tankCommander;
    }*/
}
