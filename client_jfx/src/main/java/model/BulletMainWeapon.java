package model;

import javafx.scene.Group;

public class BulletMainWeapon extends LevelElement{

    private final Tank tankFired;
    private boolean hit;

    public BulletMainWeapon(double positionX, double positionY, String[] shapePaths, double rotations, boolean isVisible, Tank tankFired) {
        super(positionX, positionY, shapePaths, rotations, isVisible);
        this.tankFired = tankFired;
    }

    public Tank getTankFired() {
        return tankFired;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
