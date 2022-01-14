package model.game.elements;

public class BulletSecondaryWeapon extends LevelElement {
    private final Tank FIRINGTANK;
    double rotations;
    private boolean hit = false;

    public BulletSecondaryWeapon(String imagePath, String type, double positionX, double positionY, double width, double height, double rotation, Tank firingTank) {
        super(imagePath, type, positionX, positionY, width, height, rotation);
        this.FIRINGTANK = firingTank;
        this.rotations = rotations;
    }

    public Tank getTankFired() {
        return FIRINGTANK;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
