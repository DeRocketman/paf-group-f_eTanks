package model.game;

public class BulletMainWeapon extends LevelElement{

    private final Tank tankFired;
    double rotations;
    private boolean hit;

    public BulletMainWeapon(double positionX, double positionY, String[] shapePaths, double rotations, boolean isVisible, Tank tankFired) {
        super(positionX, positionY, shapePaths, isVisible);
        this.tankFired = tankFired;
        this.rotations = rotations;
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
