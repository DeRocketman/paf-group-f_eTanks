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

    /**
     * Constructor of the class Tank
     * @param image         Image of the tank
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     * @param rotation      rotation of the ImageView
     * @param playerId      id of the player who drives the tank
     */
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
            this.rotateTransition(newCourse);
        }
    }

    /**
     * Provides the rotation animation
     *
     * @param newCourse     new course of the tank
     */
    public void rotateTransition(double newCourse) {

        RotateTransition rt;

        if (Math.abs(this.getRotate() - newCourse) == 180.0) {
            rt = new RotateTransition(Duration.seconds(GamePhysics.TANK_QUARTER_ROTATION_DURATION * 2), this);
        } else {
            rt = new RotateTransition(Duration.seconds(GamePhysics.TANK_QUARTER_ROTATION_DURATION), this);
        }
        if (rt.getStatus() != Animation.Status.RUNNING) {
            if (this.getRotate() == 360.0 && newCourse == 90.0) {
                this.setRotate(0.0);
                rt.setFromAngle(this.getRotate());
                rt.setToAngle(newCourse);
            } else if (this.getRotate() == 0.0 && newCourse == 360.0) {
                this.setRotate(newCourse);
                rt.setFromAngle(this.getRotate());
                rt.setToAngle(newCourse);
            } else if (this.getRotate() == 90.0 && newCourse == 360.0) {
                rt.setFromAngle(this.getRotate());
                rt.setToAngle(0.0);
            } else {
                rt.setFromAngle(this.getRotate());
                rt.setToAngle(newCourse);
            }
            if (this.getRotate() == 0.0) {
                this.setRotate(360.0);
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

    /**
     * Sets the correct position for a bullet shot by the tank
     *
     * @return      x and y position for the bullet
     */
    public double[] setCorrectBulletPosition() {
        double[] bulletStartPosition = new double[2];
        if (this.getRotate() == 360.0 || this.getRotate() == 0.0) {
            bulletStartPosition[0] = this.getLayoutX() + 17.0;
            bulletStartPosition[1] = this.getLayoutY();
        } else if (this.getRotate() == 90.0) {
            bulletStartPosition[0] = this.getLayoutX() + 22.0;
            bulletStartPosition[1] = this.getLayoutY() + 14.0;
        } else if (this.getRotate() == 180.0) {
            bulletStartPosition[0] = this.getLayoutX() + 15.0;
            bulletStartPosition[1] = this.getLayoutY() + 22.0;
        } else if (this.getRotate() == 270.0) {
            bulletStartPosition[0] = this.getLayoutX() + 14.0;
            bulletStartPosition[1] = this.getLayoutY() + 15.0;
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
