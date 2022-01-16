package model.game.elements;

import javafx.scene.image.Image;
import model.game.logic.GamePhysics;

public class Block extends LevelElement {
    private int lives;

    public Block(Image image, String type, double positionX, double positionY, double width, double height, double rotation, boolean isVisible, int lives) {
        super(image, type,positionX, positionY, width, height, rotation);
        this.lives = lives;
        this.setVisible(isVisible);
    }

    public Block(Image image, String type, double positionX, double positionY, double width, double height, double rotation, int lives) {
        super(image, type,positionX, positionY, width, height, rotation);
        this.lives = lives;
    }

    public Block(Image image, String type, double positionX, double positionY, double width, double height, int lives) {
        super(image, type,positionX, positionY, width, height);
        this.lives = lives;
    }


    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
}
