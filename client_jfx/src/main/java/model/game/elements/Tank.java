package model.game.elements;

import javafx.scene.image.Image;
import model.data.User;
import model.game.logic.GamePhysics;

public class Tank extends LevelElement{
    //private User tankCommander;
    //private double course;
    private int livePoints;

    public Tank(Image image, String type, double positionX, double positionY, double width, double height, double rotation, int livePoints) {
        super(image, "tank",positionX, positionY, width, height, rotation);
        this.livePoints = livePoints;
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

/*
    public void setTankCommander(User commander) {
        this.tankCommander = commander;
    }
    public User getTankCommander() {
        return tankCommander;
    }*/
}
