package model.game;

import model.data.User;

public class Tank extends LevelElement{
    private final User TANK_COMMANDER;
    private double course;
    private double weaponCourse;

    public Tank(double positionX, double positionY, String[] imagePaths, boolean isVisible, User TANK_COMMANDER, double course) {
        super(positionX, positionY, imagePaths, isVisible);
        this.TANK_COMMANDER = TANK_COMMANDER;
        this.course = course;
        this.weaponCourse = course;
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

    public User getTankCommander() {
        return TANK_COMMANDER;
    }
}
