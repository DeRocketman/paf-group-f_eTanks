package model.game.elements;

import javafx.scene.image.Image;
import java.util.Objects;

public class BulletMainWeapon extends LevelElement{

    private final Tank FIRINGTANK;
    private boolean hit = false;

    /**
     * Constructor of the class BulletMainWeapon
     *
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     * @param rotation      rotation of the ImageView
     * @param firingTank    Tank who fired the bullet
     */
    public BulletMainWeapon(double positionX, double positionY, double width, double height, double rotation, Tank firingTank){
        super(LevelElementType.BULLETMAINWEAPON, positionX, positionY, width, height, rotation);
        this.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../../img/images/bullets/Medium_Shell_resized.png"))));
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
