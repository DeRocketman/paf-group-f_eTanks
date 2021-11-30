package model.game.elements;

public class Block extends LevelElement {
    private final int lives;

    public Block(double positionX, double positionY, String[] imagePaths, boolean isVisible, int lives) {
        super(positionX, positionY, imagePaths, isVisible);

        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
}
