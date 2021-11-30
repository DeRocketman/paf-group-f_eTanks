package model.game.elements;

import model.data.User;
import model.game.logic.GamePhysics;

public class Tank extends LevelElement{
    private User tankCommander;
    private double course;
    private double weaponCourse;

    public Tank(double positionX, double positionY, String[] imagePaths, boolean isVisible, double course, User tankCommander) {
        super(positionX, positionY, imagePaths, isVisible);
        this.weaponCourse = course;
        this.course = course;
        this.tankCommander = tankCommander;

    }

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

    //TODO: Klären ob Waffe und Hull getrennt voneinander oder immer zusammen drehen
    public void turnTankHull(double directionNew) {
        this.course = directionNew;
        turnTankWeapon(directionNew);
    }
    public void turnTankWeapon(double directionNew) {
        this.weaponCourse = directionNew;
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
    }
}
