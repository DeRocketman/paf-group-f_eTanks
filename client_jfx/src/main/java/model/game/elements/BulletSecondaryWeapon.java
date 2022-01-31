package model.game.elements;
import javafx.scene.image.Image;

public class BulletSecondaryWeapon extends LevelElement {
    private final Tank FIRINGTANK;
    private boolean hit = false;

    /**
     * Constructor of the class BulletSecondaryWeapon
     *
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     * @param rotation      rotation of the ImageView
     * @param firingTank    Tank who fired the bullet
     */
    public BulletSecondaryWeapon(Image image, LevelElementType type, double positionX, double positionY, double width, double height, double rotation, Tank firingTank) {
        super(image, type, positionX, positionY, width, height, rotation);
        this.FIRINGTANK = firingTank;
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
