package model.game.elements;

import javafx.scene.image.Image;
import model.game.logic.GamePhysics;

public class Block extends LevelElement {
    private int lives;

    public Block(Image image, LevelElementType type, double positionX, double positionY, double width, double height, double rotation) {
        super(image, type, positionX, positionY, width, height, rotation);
        if(type == LevelElementType.BLOCK_METAL){
            lives = 10000;
        } else if(type == LevelElementType.BLOCK_STONE){
            lives = 3;
        } else if(type == LevelElementType.BLOCK_WOOD){
            lives = 2;
        }
    }

    public Block(Image image, LevelElementType type, double positionX, double positionY, double width, double height) {
        super(image, type, positionX, positionY, width, height);
        if(type == LevelElementType.BLOCK_METAL){
            lives = 10000;
        } else if(type == LevelElementType.BLOCK_STONE){
            lives = 3;
        } else if(type == LevelElementType.BLOCK_WOOD){
            lives = 2;
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
}
