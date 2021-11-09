package model;

public class Item extends LevelElement {
    private int points;

    public Item(int points, int id, int  positionX, int positionY, String shapePath, boolean isVisible) {
        super(id, positionX, positionY, shapePath, isVisible);
        this.points = points;
    }

    public void showEffect() {
        //Do effects
    }
    public void showAnimation() {
        //Do effects
    }
}
