package model.game;

public class BulletSecondaryWeapon extends LevelElement {
    private final Tank firingTank;
    double rotations;
    private boolean hit = false;

    public BulletSecondaryWeapon(double positionX, double positionY, String[] shapePaths, boolean isVisible,
                            double rotations, Tank firingTank) {
        super(positionX, positionY, shapePaths, isVisible);
        this.firingTank = firingTank;
        this.rotations = rotations;
    }

    public Tank getTankFired() {
        return firingTank;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
