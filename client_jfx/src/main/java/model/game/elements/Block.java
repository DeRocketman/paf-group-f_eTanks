package model.game.elements;

import javafx.scene.image.Image;

public class Block extends LevelElement {

    private int lives;

    /**
     * Constructor of the class Block
     *
     * @param image         Image of bock
     * @param type          LevelElementType of block
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     * @param rotation      rotation of the ImageView
     */
    public Block(Image image, LevelElementType type, double positionX, double positionY, double width, double height, double rotation) {
        super(image, type, positionX, positionY, width, height, rotation);
        if(type == LevelElementType.BLOCK_METAL){
            lives = 10000;
        } else if(type == LevelElementType.BLOCK_STONE){
            lives = 3;
        } else if(type == LevelElementType.BLOCK_WOOD){
            lives = 1;
        }
    }

    /**
     * Constructor of the class Block without rotation
     *
     * @param image         Image of bock
     * @param type          LevelElementType of block
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     */
    public Block(Image image, LevelElementType type, double positionX, double positionY, double width, double height) {
        super(image, type, positionX, positionY, width, height);
        if(type == LevelElementType.BLOCK_METAL){
            lives = 10000;
        } else if(type == LevelElementType.BLOCK_STONE){
            lives = 3;
        } else if(type == LevelElementType.BLOCK_WOOD){
            lives = 1;
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
}