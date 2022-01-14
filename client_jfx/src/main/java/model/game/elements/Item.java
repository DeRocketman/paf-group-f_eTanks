package model.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.game.elements.LevelElement;
import model.game.logic.GamePhysics;

public class Item extends LevelElement {
    private int points;

    public Item(Image image, String type, double positionX, double positionY, double width, double height, double rotation, boolean isVisible) {
        super(image, type,positionX, positionY, width, height, rotation);
        this.points = GamePhysics.ITEM_POINTS;
        this.setVisible(isVisible);
    }


    public void showEffect() {
        //Do effects
    }
    public void showAnimation() {
        //Do effects
    }
}
