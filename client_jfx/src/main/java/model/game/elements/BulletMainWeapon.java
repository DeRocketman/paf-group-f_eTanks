package model.game.elements;
import javafx.scene.image.Image;

import java.util.Objects;

public class BulletMainWeapon extends LevelElement{

    private final Tank FIRINGTANK;
    private boolean hit = false;

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
