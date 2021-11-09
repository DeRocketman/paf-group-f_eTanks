package model;

public abstract class LevelElement {
    private int id;
    private int positionX;
    private int positionY;
    private String shapePath;
    private boolean isVisible;

    public int[] getPosition() {
        return new int[]{this.positionX, this.positionY};
    }

}
