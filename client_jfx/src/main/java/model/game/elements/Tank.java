package model.game.elements;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.game.logic.GamePhysics;

public class Tank extends LevelElement{

    private long playerId;
    private int livePoints;
    boolean isActive;

    public Tank(Image image, double positionX, double positionY, double width, double height, double rotation, int playerId) {
        super(image, LevelElementType.TANK ,positionX, positionY, width, height, rotation);
        this.livePoints = 3;
        this.isActive = true;
        this.playerId = playerId;
    }

    /**
     * Moves the tank by the speed factor
     * restricted by the playing field border
     *
     * @param newCourse     the driving direction
     */
    public void moveTank(double newCourse) {
        double speed = GamePhysics.TANK_SPEED;

        if (this.getRotate() == newCourse) {
            if (newCourse == 360.0 || newCourse == 0.0) {
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

    /**
     * Decrements the livePoints of the tank
     */
    public void reduceLivePoints(){
        livePoints--;
    }

    public double[] setCorrectBulletPosition(LevelElement myTank) {
        double[] bulletStartPosition = new double[2];
        if (myTank.getRotate() == 360.0 || myTank.getRotate() == 0.0) {
            bulletStartPosition[0] = myTank.getLayoutX() + 17.0;
            bulletStartPosition[1] = myTank.getLayoutY();
        } else if (myTank.getRotate() == 90.0) {
            bulletStartPosition[0] = myTank.getLayoutX() + 22.0;
            bulletStartPosition[1] = myTank.getLayoutY() + 14.0;
        } else if (myTank.getRotate() == 180.0) {
            bulletStartPosition[0] = myTank.getLayoutX() + 15.0;
            bulletStartPosition[1] = myTank.getLayoutY() + 22.0;
        } else if (myTank.getRotate() == 270.0) {
            bulletStartPosition[0] = myTank.getLayoutX() + 14.0;
            bulletStartPosition[1] = myTank.getLayoutY() + 15.0;
            }
        return bulletStartPosition;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getLivePoints(){
        return livePoints;
    }

    public boolean getActive(){
        return isActive;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }
}
