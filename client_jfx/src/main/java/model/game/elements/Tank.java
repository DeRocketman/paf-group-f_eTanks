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
    public void moveTank(double direction) {
        double MOVE_SPEED = GamePhysics.TANK_SPEED;
        if (direction == 360.0) {
            super.setPositions(0.0, getPositions()[1] + MOVE_SPEED);
        } else if (direction == 90.0) {
            super.setPositions(getPositions()[0] + MOVE_SPEED, 0.0);
        } else if (direction == 270.0) {
            super.setPositions(getPositions()[0] - MOVE_SPEED, 0.0);
        } else if (direction == 90.0) {
            super.setPositions(0.0,getPositions()[1] - MOVE_SPEED);
        }
    }

    public double getTotalTurnSpeed(double directionNew) {
        double TURN_SPEED = GamePhysics.TANK_QUARTER_ROTATION_DURATION;
        if (this.course - directionNew == 180.0 ) {
            return TURN_SPEED * 2;
        } else if (this.course - directionNew == 90.0) {
            return TURN_SPEED;
        } else
            return 0.0;
    }

    public void setTankCommander(User commander) {
        this.tankCommander = commander;
    }
    public User getTankCommander() {
        return tankCommander;
    }*/
}
