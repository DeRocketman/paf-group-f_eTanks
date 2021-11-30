package model.game.elements;

public class BulletMainWeapon extends LevelElement{

    private final Tank firingTank;
    double rotations;
    private boolean hit = false;

    public BulletMainWeapon(double positionX, double positionY, String[] shapePaths, boolean isVisible,
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
