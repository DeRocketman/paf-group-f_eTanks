package model.game;

public class Item extends LevelElement {
    private int points;

    public Item(double positionX, double positionY, String[] shapePaths, boolean isVisible) {
        super(positionX, positionY, shapePaths, isVisible);
        this.points = GamePhysics.ITEM_POINTS;
    }


    public void showEffect() {
        //Do effects
    }
    public void showAnimation() {
        //Do effects
    }
}
