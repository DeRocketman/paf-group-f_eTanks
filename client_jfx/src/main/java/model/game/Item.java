package model.game;

public class Item extends LevelElement {
    private int points;

    public Item(double positionX, double positionY, String[] shapePaths, double rotations, boolean isVisible) {
        super(positionX, positionY, shapePaths, rotations, isVisible);
    }


    public void showEffect() {
        //Do effects
    }
    public void showAnimation() {
        //Do effects
    }
}
